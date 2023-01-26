package com.example.swups.controller;

import com.example.swups.entity.*;
import com.example.swups.exceptions.EmptyCourseCodeException;
import com.example.swups.exceptions.EmptyCourseNameException;
import com.example.swups.repository.*;
import com.example.swups.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseCharacterRepository courseCharacterRepository;
    private final CourseFormRepository courseFormRepository;
    private final CourseKindRepository courseKindRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final WayOfCreditingRepository wayOfCreditingRepository;


    @GetMapping("/courses/add")
    public String showCourseForm(final Model model)
    {
        model.addAttribute("course", new CourseInfo());
        model.addAttribute("courseCharacters", courseCharacterRepository.findAll());
        model.addAttribute("courseForms", courseFormRepository.findAll());
        model.addAttribute("courseKinds", courseKindRepository.findAll());
        model.addAttribute("courseTypes", courseTypeRepository.findAll());
        model.addAttribute("waysOfCrediting", wayOfCreditingRepository.findAll());
        return "courses/add";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute CourseInfo courseInfo) throws UserPrincipalNotFoundException
    {
        WayOfCrediting wayOfCrediting = wayOfCreditingRepository.findWayOfCreditingById(Integer.parseInt(courseInfo.getWayOfCrediting()));
        CourseCharacter courseCharacter = courseCharacterRepository.findCourseCharacterById(Integer.parseInt(courseInfo.getCourseCharacter()));
        CourseType courseType = courseTypeRepository.findCourseTypeById(Integer.parseInt(courseInfo.getCourseType()));
        CourseKind courseKind = courseKindRepository.findCourseKindById(Integer.parseInt(courseInfo.getCourseKind()));
        CourseForm courseForm = courseFormRepository.findCourseFormById(Integer.parseInt(courseInfo.getCourseForm()));

        Course course = Course.builder().courseCharacter(courseCharacter)
                .courseForm(courseForm)
                .courseKind(courseKind)
                .courseType(courseType)
                .wayOfCrediting(wayOfCrediting)
                .code(courseInfo.getCode())
                .name(courseInfo.getName())
                .weeklySumOfHours(courseInfo.getWeeklySumOfHours())
                .sumOfZZUHours(courseInfo.getSumOfZZUHours())
                .sumOfCnpsHours(courseInfo.getSumOfCnpsHours())
                .sumOfEctsPoints(courseInfo.getSumOfEctsPoints())
                .sumOfEctsPointsFromBuClasses(courseInfo.getSumOfEctsPointsFromBuClasses())
                .sumOfEctsPointsFromDnClasses(courseInfo.getSumOfEctsPointsFromDnClasses())
                .build();
        try
        {
            courseService.saveCourse(course);
        }
        catch (EmptyCourseCodeException | EmptyCourseNameException e)
        {
            System.out.println(course);
            return "redirect:/?error=true";
        }
        System.out.println(course);
        return "redirect:/";
    }


}
