package com.example.swups.controller;

import com.example.swups.entity.Opinion;
import com.example.swups.entity.PlanOfStudies;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.service.OpinionService;
import com.example.swups.service.PlanOfStudiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller

@RequestMapping("/plans/{planId}/details/opinions")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;
    private final PlanOfStudiesService planOfStudiesService;

    @GetMapping
    public String getAllOpinions(Model model, @PathVariable String planId) {
        PlanOfStudies planOfStudies = planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        List<Opinion> opinions = opinionService.getOpinionsByPlanOfStudies(planOfStudies);
        model.addAttribute("opinions", opinions);

        return "opinions/opinions";
    }

    @GetMapping("/{opinionId}")
    public String getOpinionContent(Model model, @PathVariable String opinionId) {
        Opinion opinion = opinionService.getOpinionById(Integer.parseInt(opinionId));
        model.addAttribute("opinion", opinion);
        return "opinions/showOpinion";

    }

    @GetMapping("/add")
    public String getOpinionForm(Model model, @PathVariable String planId, @RequestParam(required = false) String error) {
        PlanOfStudies planOfStudiesById = planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        model.addAttribute("opinion", Opinion.builder().planOfStudies(planOfStudiesById).build());
        model.addAttribute("url", "/plans/" + planId + "/details/opinions/add");
        model.addAttribute("error", error);
        return "opinions/add";
    }

    @PostMapping("/add")
    public String saveOpinionForm(@ModelAttribute Opinion opinion, @PathVariable String planId) throws UserPrincipalNotFoundException {
        try {
            opinionService.saveOpinion(opinion, planId);
        } catch (EmptyOpinionContentException e) {
            return "redirect:/plans/" + planId + "/details/opinions/add?error=true";
        }
        return "redirect:/plans/" + planId + "/details/opinions";
    }
}
