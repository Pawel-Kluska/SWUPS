package com.example.swups.controller;

import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import com.example.swups.service.OpinionsService;
import com.example.swups.service.PlansOfStudiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping("/plans/{planId}/details/opinions")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionsService opinionsService;
    private final PlansOfStudiesService plansOfStudiesService;
    @GetMapping
    public String getAllOpinions(Model model , @PathVariable String planId){
        Plansofstudy plansofstudy = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        List<Opinion> opinions = opinionsService.getOpinionsByplanofstudiesid(plansofstudy);
        System.out.println("-------------"+opinions.size());
        model.addAttribute("opinions",opinions);
        System.out.println("aaa" +opinions.get(0).getId());

        return "opinions/opinions";
    }
    @GetMapping("/{opinionId}")
    public String getOpinionContent(Model model, @PathVariable String opinionId){
        Opinion opinion = opinionsService.getOpinionById(Integer.parseInt(opinionId));
        model.addAttribute("opinion",opinion);
        return "opinions/showOpinion";

    }
}
