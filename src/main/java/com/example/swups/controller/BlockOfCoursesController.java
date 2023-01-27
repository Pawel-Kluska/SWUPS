package com.example.swups.controller;

import com.example.swups.entity.BlockOfCoursesInfo;
import com.example.swups.service.BlockCharacterService;
import com.example.swups.service.BlockOfCoursesService;
import com.example.swups.service.CourseService;
import com.example.swups.service.SemesterService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlockOfCoursesController {

    private final BlockOfCoursesService blockOfCoursesService;
    private final BlockCharacterService blockCharacterService;
    private final SemesterService semesterService;
    private final CourseService courseService;

    @GetMapping("/blocksofcourses/add")
    public String showBlockOfCoursesForm(final Model model)
    {
        model.addAttribute("blockOfCourses", new BlockOfCoursesInfo());
        model.addAttribute("blockCharacters", blockCharacterService.getAllBlockCharacters());
        model.addAttribute("semesters", semesterService.getAllSemesters());
        model.addAttribute("courses", courseService.getAllCourses());
        return "blocksofcourses/add";
    }
}
