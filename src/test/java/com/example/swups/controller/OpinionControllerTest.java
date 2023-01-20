package com.example.swups.controller;

import com.example.swups.entity.Opinion;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.service.OpinionService;
import com.example.swups.service.PlanOfStudiesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(OpinionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OpinionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanOfStudiesService planOfStudiesService;

    @MockBean
    private OpinionService opinionService;


    @Test
    @WithMockUser(username = "USER", authorities = {"OTHER"})
    void saveOpinionFormTest() throws Exception {
        //given
        String url = "/plans/1/details/opinions/add";
        Opinion opinion = Opinion.builder()
                .content("Content")
                .isPositive(true)
                .build();

        doNothing().when(opinionService).saveOpinion(opinion, "1");


        //when
        ResultActions actions = this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("content", opinion.getContent())
                        .param("isPositive", opinion.getIsPositive() + "")
                );

        //then
        verify(opinionService, times(1)).saveOpinion(any(), any());

    }

    @Test
    @WithMockUser(username = "USER", authorities = {"OTHER"})
    void saveOpinionFormEmptyExceptionTest() throws Exception {
        //given
        String url = "/plans/1/details/opinions/add";
        Opinion opinion = Opinion.builder()
                .content("")
                .isPositive(true)
                .build();

        doThrow(new EmptyOpinionContentException())
                .when(opinionService).saveOpinion(any(), any());

        //when
        ResultActions actions = this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("content", "")
                        .param("isPositive", opinion.getIsPositive() + "")
                );

        //then
        actions.andExpect(redirectedUrl("/plans/1/details/opinions/add?error=true"));

    }
}
