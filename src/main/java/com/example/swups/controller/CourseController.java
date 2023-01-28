package com.example.swups.controller;

import com.example.swups.entity.*;
import com.example.swups.exceptions.NoUniqueCourseCodeException;
import com.example.swups.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseCharacterService courseCharacterService;
    private final CourseFormService courseFormService;
    private final CourseKindService courseKindService;
    private final CourseTypeService courseTypeService;
    private final WayOfCreditingService wayOfCreditingService;
    private final StudyEffectService studyEffectService;


    @GetMapping("/courses/add")
    public String showCourseForm(final Model model, @RequestParam(required = false) String error)
    {
        model.addAttribute("course", new CourseInfo());
        model.addAttribute("courseCharacters", courseCharacterService.getAllCourseCharacters());
        model.addAttribute("courseForms", courseFormService.getAllCourseForms());
        model.addAttribute("courseKinds", courseKindService.getAllCourseKinds());
        model.addAttribute("courseTypes", courseTypeService.getAllCourseTypes());
        model.addAttribute("waysOfCrediting", wayOfCreditingService.getAllWaysOfCrediting());
        model.addAttribute("studyEffects", studyEffectService.getPossibleStudyEffects());
        model.addAttribute("error", error);
        return "courses/add";
    }

    @GetMapping("/courses/show")
    public String showListOfCourses(final Model model, @RequestParam(required = false) String success)
    {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("success", success);
        return "courses/show";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute CourseInfo courseInfo) throws UserPrincipalNotFoundException
    {
        try
        {
            Course course = courseService.buildCourseFromCourseInfo(courseInfo);
            courseService.saveCourse(course);
            for (String studyEffect : courseInfo.getStudyeffects().split(","))
            {
                studyEffectService.update(studyEffect, course);
            }
        }
        catch(NoUniqueCourseCodeException e)
        {
            return "redirect:/courses/add?error=true";
        }
        return "redirect:/courses/show?success=true";
    }

}
