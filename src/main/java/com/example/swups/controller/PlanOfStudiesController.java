package com.example.swups.controller;

import com.example.swups.Utils;
import com.example.swups.entity.PlanOfStudies;
import com.example.swups.entity.PlanStatus;
import com.example.swups.service.PlanOfStudiesService;
import com.example.swups.service.PlanStatusService;
import com.example.swups.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;

@Controller
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanOfStudiesController {

    private final PlanOfStudiesService planOfStudiesService;
    private final PlanStatusService planstatusService;
    private final UserService userService;

    @GetMapping
    public String getAllPlans(Model model) {
        model.addAttribute("plansList", planOfStudiesService.getPlansOfStudies());
        return "plansOfStudies/plans";
    }

    @GetMapping("/{id}/details")
    public String getPlanDetails(Model model, @PathVariable String id, Principal principal) {
        model.addAttribute("plan", planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(id)));
        model.addAttribute("addOpinionUrl", "/plans/" + id + "/details/opinions/add");
        model.addAttribute("addOpinionsUrl", "/plans/" + id + "/details/opinions");
        model.addAttribute("url", "/plans/" + id + "/details");
        model.addAttribute("user", userService.getUserByLogin(principal.getName()));
        if (Utils.currentUserHasRole("SENAT")) {
            model.addAttribute("isSenat", true);
        }
        return "plansOfStudies/details";
    }

    @PostMapping("/{id}/details")
    public String acceptAPlan(@ModelAttribute PlanOfStudies plan) {
        plan = planOfStudiesService.getPlanOfStudiesById(plan.getId());
        PlanStatus planStatus = planstatusService.getPlanStatusByName("Zatwierdzony");
        plan.setPlanStatus(planStatus);
        try {
            planOfStudiesService.savePlan(plan);
        } catch (UserPrincipalNotFoundException e) {
            return "redirect:/plans/" + plan.getId() + "/details";
        }
        return "redirect:/plans/" + plan.getId() + "/details";
    }
}
