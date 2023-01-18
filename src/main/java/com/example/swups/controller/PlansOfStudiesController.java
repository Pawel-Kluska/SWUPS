package com.example.swups.controller;

import com.example.swups.entity.Plansofstudy;
import com.example.swups.entity.Planstatus;
import com.example.swups.service.PlansOfStudiesService;
import com.example.swups.service.PlanstatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlansOfStudiesController {

    private final PlansOfStudiesService plansOfStudiesService;
    private final PlanstatusService planstatusService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping
    public String getAllPlans(Model model) {
        model.addAttribute("plansList", plansOfStudiesService.getPlansOfStudies());
        return "plansOfStudies/plans";
    }
    @GetMapping("/{id}/details")
    public String getPlanDetails(Model model, @PathVariable String id) {
        model.addAttribute("plan", plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(id)));
        model.addAttribute("addOpinionUrl", "/plans/" + id + "/details/opinions/add");
        model.addAttribute("addOpinionsUrl", "/plans/" + id + "/details/opinions");
        model.addAttribute("url","/plans/"+id+"/details");
        return "plansOfStudies/details";
    }

    @PostMapping("/{id}/details")
    public String acceptAPlan(@ModelAttribute Plansofstudy plan){
        System.out.println("zatwierdzam");
        plan = plansOfStudiesService.getPlanOfStudiesById(plan.getId());
        Planstatus planStatus = planstatusService.getPlanStatusByName("Zatwierdzony");
        plan.setPlanstatusid(planStatus);
        plansOfStudiesService.savePlan(plan);
        return "redirect:/plans/" + plan.getId() + "/details";
    }
}
