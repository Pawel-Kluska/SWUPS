package com.example.swups.controller;

import com.example.swups.entity.BlockOfCoursesInfo;
import com.example.swups.exceptions.NoUniqueBlockOfCoursesCodeException;
import com.example.swups.service.BlockCharacterService;
import com.example.swups.service.BlockOfCoursesService;
import com.example.swups.service.CourseService;
import com.example.swups.service.SemesterService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Controller
@RequiredArgsConstructor
public class BlockOfCoursesController {

    private final BlockOfCoursesService blockOfCoursesService;
    private final BlockCharacterService blockCharacterService;
    private final SemesterService semesterService;
    private final CourseService courseService;

    @GetMapping("/blocksOfCourses/add")
    public String showBlockOfCoursesForm(final Model model, @RequestParam(required = false) String error)
    {
        model.addAttribute("blockOfCourses", new BlockOfCoursesInfo());
        model.addAttribute("blockCharacters", blockCharacterService.getAllBlockCharacters());
        model.addAttribute("semesters", semesterService.getAllSemesters());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("error", error);
        return "blocksOfCourses/add";
    }
    @PostMapping("/blocksOfCourses/add")
    public String addBlockOfCourses(@ModelAttribute BlockOfCoursesInfo blockOfCoursesInfo) throws UserPrincipalNotFoundException
    {
        try
        {
            blockOfCoursesService.saveBlockOfCourses(blockOfCoursesService.buildBlockOfCoursesFromInfo(blockOfCoursesInfo));
        }
        catch(NoUniqueBlockOfCoursesCodeException e)
        {
            return "redirect:/blocksOfCourses/add?error=true";
        }
        return "redirect:/blocksOfCourses/show?success=true";
    }

    @GetMapping("/blocksOfCourses/show")
    public String showListOfCourses(final Model model, @RequestParam(required = false) String success)
    {
        model.addAttribute("blocksOfCourses", blockOfCoursesService.getAllBlocksOfCourses());
        model.addAttribute("success", success);
        return "blocksOfCourses/show";
    }
}
