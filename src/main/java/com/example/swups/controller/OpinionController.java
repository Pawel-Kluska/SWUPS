package com.example.swups.controller;

import com.example.swups.entity.Appuser;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.service.OpinionsService;
import com.example.swups.service.PlansOfStudiesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionsService opinionsService;
    private final PlansOfStudiesService plansOfStudiesService;

    @GetMapping("/plans/{id}/details/opinions")
    public String getAllOpinions(Model model, @PathVariable String id) {
        Plansofstudy plansofstudy = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(id));
        List<Opinion> opinions = opinionsService.getOpinionsByplanofstudiesid(plansofstudy);
        System.out.println("-------------" + opinions.size());
        model.addAttribute("opinions", opinions);
        opinions.get(0).getUserid().getAuthorityid().getName();
        return "opinions/opinions";
    }

    @GetMapping("/plans/{id}/details/opinions/add")
    public String getOpinionForm(Model model, @PathVariable String id, @RequestParam(required = false) String error) {
        Plansofstudy planOfStudiesById = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(id));
        model.addAttribute("opinion", Opinion.builder().planofstudiesid(planOfStudiesById).build());
        model.addAttribute("url", "/plans/" + id + "/details/opinions/add");
        model.addAttribute("error", error);
        return "opinions/add";
    }

    @PostMapping("/plans/{id}/details/opinions/add")
    public String saveOpinionForm(@ModelAttribute Opinion opinion, @PathVariable String id) {
        Plansofstudy planOfStudiesById = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(id));
        opinion.setPlanofstudiesid(planOfStudiesById);
        opinion.setUserid(new Appuser()); //TODO: jak bedzie logowanie to sie ogarnie current user
        opinion.setDateofopinion(Instant.now());
        opinion.setDateofmodification(Instant.now());
        try {
            opinionsService.saveOpinion(opinion);
        } catch (EmptyOpinionContentException e) {
            return "redirect:/plans/" + id + "/details/opinions/add?error=true";
        }
        return "redirect:/plans/" + id + "/details/opinions";
    }
}
