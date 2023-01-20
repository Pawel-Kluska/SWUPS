package com.example.swups.service;


import com.example.swups.TestUtils;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.PlanOfStudies;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.repository.OpinionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OpinionServiceTest {

    @Mock
    private OpinionRepository opinionRepository;
    @Mock
    private PlanOfStudiesService planOfStudiesService;
    @InjectMocks
    private OpinionService opinionService;

    @Test
    void getOpinionsByPlanOfStudiesSuccessTest() {
        //given
        PlanOfStudies planOfStudies1 = PlanOfStudies.builder()
                .identifier("id1")
                .build();

        PlanOfStudies planOfStudies2 = PlanOfStudies.builder()
                .identifier("id2")
                .build();

        Opinion opinion = Opinion.builder()
                .content("content1")
                .isPositive(true)
                .planOfStudies(planOfStudies1)
                .user(TestUtils.getTestUser("User1"))
                .build();


        List<Opinion> opinions = List.of(opinion);

        given(opinionRepository.findOpinionsByPlanOfStudies(planOfStudies1))
                .willReturn(opinions);
        //when
        List<Opinion> serviceOpinions1 = opinionService.getOpinionsByPlanOfStudies(planOfStudies1);
        List<Opinion> serviceOpinions2 = opinionService.getOpinionsByPlanOfStudies(planOfStudies2);

        //then
        assertThat(serviceOpinions1).hasSize(1);
        assertThat(serviceOpinions1.get(0).getPlanOfStudies()).isEqualTo(planOfStudies1);
        assertThat(serviceOpinions2).hasSize(0);
    }

    @Test
    void getOpinionsByPlanOfStudiesFailTest() {
        //given
        List<Opinion> opinions = List.of();
        PlanOfStudies planOfStudies1 = PlanOfStudies.builder()
                .identifier("id1")
                .build();

        given(opinionRepository.findOpinionsByPlanOfStudies(planOfStudies1))
                .willReturn(opinions);

        //when
        List<Opinion> serviceOpinions1 = opinionService.getOpinionsByPlanOfStudies(planOfStudies1);

        //then
        assertThat(serviceOpinions1).hasSize(0);
    }

    @Test
    void saveOpinionSuccessTest() throws UserPrincipalNotFoundException, EmptyOpinionContentException {
        //given

        TestUtils.logInUser("ROLE");

        String planOfStudiesId = "1";
        PlanOfStudies planOfStudies = PlanOfStudies.builder()
                .id(Integer.parseInt(planOfStudiesId))
                .identifier("id1")
                .build();

        Opinion opinion = Opinion.builder()
                .content("content1")
                .isPositive(true)
                .planOfStudies(planOfStudies)
                .user(TestUtils.getTestUser("User1"))
                .build();

        given(opinionRepository.save(opinion))
                .willReturn(opinion);
        given(planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planOfStudiesId)))
                .willReturn(planOfStudies);

        //when
        opinionService.saveOpinion(opinion, planOfStudiesId);

        //then
        verify(opinionRepository, times(1)).save(any());
    }

    @Test
    void saveOpinionUserLoggedOutTest() {
        //given
        TestUtils.logOutUser();

        String planOfStudiesId = "1";
        PlanOfStudies planOfStudies = PlanOfStudies.builder()
                .id(Integer.parseInt(planOfStudiesId))
                .identifier("id1")
                .build();

        Opinion opinion = Opinion.builder()
                .content("content1")
                .isPositive(true)
                .planOfStudies(planOfStudies)
                .user(TestUtils.getTestUser("User1"))
                .build();

        //when

        assertThatThrownBy(() -> opinionService.saveOpinion(opinion, planOfStudiesId)).
                isInstanceOf(UserPrincipalNotFoundException.class);
        //then

        verify(opinionRepository, times(0)).save(any());
    }

    @Test
    void saveOpinionEmptyTest() throws UserPrincipalNotFoundException, EmptyOpinionContentException {
        //given

        TestUtils.logInUser("ROLE");

        String planOfStudiesId = "1";
        PlanOfStudies planOfStudies = PlanOfStudies.builder()
                .id(Integer.parseInt(planOfStudiesId))
                .identifier("id1")
                .build();

        Opinion opinion = Opinion.builder()
                .content("")
                .isPositive(true)
                .planOfStudies(planOfStudies)
                .user(TestUtils.getTestUser("User1"))
                .build();

        given(planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planOfStudiesId)))
                .willReturn(planOfStudies);

        //when
        assertThatThrownBy(() -> opinionService.saveOpinion(opinion, planOfStudiesId)).
                isInstanceOf(EmptyOpinionContentException.class);
        //then
        verify(opinionRepository, times(0)).save(any());
    }

}
