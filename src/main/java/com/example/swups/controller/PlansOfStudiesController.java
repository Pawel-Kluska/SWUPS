package com.example.swups.controller;

import com.example.swups.entity.Faculty;
import com.example.swups.service.PlansOfStudiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlansOfStudiesController {

    private final PlansOfStudiesService plansOfStudiesService;
    @GetMapping
    public String getAllPlans(Model model) {
        model.addAttribute("plansList", plansOfStudiesService.getPlansOfStudies());
        return "plansOfStudies/plans";
    }

    @GetMapping("/details/{id}")
    public String getPlanDetails(Model model, @PathVariable String id) {
        model.addAttribute("plansList", plansOfStudiesService.getPlanOfStudiesById(Long.parseLong(id)));
        return "plansOfStudies/details";
    }
}