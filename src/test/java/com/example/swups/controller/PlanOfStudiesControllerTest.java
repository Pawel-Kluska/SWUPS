package com.example.swups.controller;

import com.example.swups.TestUtils;
import com.example.swups.entity.PlanOfStudies;
import com.example.swups.service.PlanOfStudiesService;
import com.example.swups.service.PlanStatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanOfStudiesController.class)
public class PlanOfStudiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanOfStudiesService planOfStudiesService;

    @MockBean
    private PlanStatusService planStatusService;

    @Test
    @WithMockUser(username = "USER", authorities = {"SENAT"})
    void getPlanDetailsTest() throws Exception {
        //given
        String url = "/plans/1/details";
        PlanOfStudies planOfStudies = TestUtils.getPlanOfStudies();

        given(planOfStudiesService.getPlanOfStudiesById(1))
                .willReturn(planOfStudies);


        //when
        ResultActions actions = this.mockMvc
                .perform(get(url));

        //then
        actions.andExpect(status().isOk())
                .andExpect(view().name("plansOfStudies/details"))
                .andExpect(model().attributeExists("addOpinionUrl"))
                .andExpect(model().attributeExists("addOpinionsUrl"))
                .andExpect(model().attributeExists("url"))
                .andExpect(model().attributeExists("isSenat"));

    }

    @Test
    @WithMockUser(username = "USER", authorities = {"OTHER"})
    void getPlanDetailsWIthoutSenatTest() throws Exception {
        //given
        String url = "/plans/1/details";
        PlanOfStudies planOfStudies = TestUtils.getPlanOfStudies();

        given(planOfStudiesService.getPlanOfStudiesById(1))
                .willReturn(planOfStudies);


        //when
        ResultActions actions = this.mockMvc
                .perform(get(url));

        //then
        actions.andExpect(status().isOk())
                .andExpect(view().name("plansOfStudies/details"))
                .andExpect(model().attributeExists("addOpinionUrl"))
                .andExpect(model().attributeExists("addOpinionsUrl"))
                .andExpect(model().attributeExists("url"));

    }
}
