package com.example.swups.controller;

import com.example.swups.config.Utils;
import com.example.swups.entity.Appuser;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.service.OpinionsService;
import com.example.swups.service.PlansOfStudiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/plans/{planId}/details/opinions")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionsService opinionsService;
    private final PlansOfStudiesService plansOfStudiesService;

    @GetMapping
    public String getAllOpinions(Model model, @PathVariable String planId) {
        Plansofstudy plansofstudy = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        List<Opinion> opinions = opinionsService.getOpinionsByplanofstudiesid(plansofstudy);
        model.addAttribute("opinions", opinions);

        return "opinions/opinions";
    }

    @GetMapping("/{opinionId}")
    public String getOpinionContent(Model model, @PathVariable String opinionId) {
        Opinion opinion = opinionsService.getOpinionById(Integer.parseInt(opinionId));
        model.addAttribute("opinion", opinion);
        return "opinions/showOpinion";

    }

    @GetMapping("/add")
    public String getOpinionForm(Model model, @PathVariable String planId, @RequestParam(required = false) String error) {
        Plansofstudy planOfStudiesById = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        model.addAttribute("opinion", Opinion.builder().planofstudiesid(planOfStudiesById).build());
        model.addAttribute("url", "/plans/" + planId + "/details/opinions/add");
        model.addAttribute("error", error);
        return "opinions/add";
    }

    @PostMapping("/add")
    public String saveOpinionForm(@ModelAttribute Opinion opinion, @PathVariable String planId) throws UserPrincipalNotFoundException {
        try {
            opinionsService.saveOpinion(opinion, planId);
        } catch (EmptyOpinionContentException e) {
            return "redirect:/plans/" + planId + "/details/opinions/add?error=true";
        } catch (UserPrincipalNotFoundException e) {
            return "redirect:/plans/" + planId + "/details/opinions/add?error=true"; //Trzeba dac komunikat ze nie zalogowany
        }
        return "redirect:/plans/" + planId + "/details/opinions";
    }
}
