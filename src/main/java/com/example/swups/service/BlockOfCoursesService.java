package com.example.swups.service;

import com.example.swups.Utils;
import com.example.swups.entity.*;
import com.example.swups.exceptions.NoUniqueBlockOfCoursesCodeException;
import com.example.swups.repository.BlockOfCoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlockOfCoursesService {

    private final BlockOfCoursesRepository blockOfCoursesRepository;
    private final BlockCharacterService blockCharacterService;
    private final CourseService courseService;
    private final SemesterService semesterService;
    public BlockOfCourses buildBlockOfCoursesFromInfo(BlockOfCoursesInfo info)
    {
        BlockCharacter blockCharacter = blockCharacterService.getBlockCharacterById(Integer.parseInt(info.getBlockCharacter()));
        Semester semester = semesterService.getSemesterByCode(info.getSemester());
        Set<Course> courses = createCoursesSet(info.getCourses().split(","));

        return BlockOfCourses.builder().blockCharacter(blockCharacter)
                .code(info.getCode())
                .name(info.getName())
                .semester(semester)
                .courses(courses)
                .build();
    }

    public List<BlockOfCourses> getAllBlocksOfCourses()
    {
        return blockOfCoursesRepository.findAll();
    }

    public void saveBlockOfCourses(BlockOfCourses blockOfCourses) throws UserPrincipalNotFoundException, NoUniqueBlockOfCoursesCodeException
    {
        Optional<User> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty())
        {
            throw new UserPrincipalNotFoundException("User not logged in");
        }

        if(blockOfCoursesRepository.findBlockOfCoursesByCode(blockOfCourses.getCode()) != null)
        {
            throw new NoUniqueBlockOfCoursesCodeException("Block code must be unique!");
        }
        blockOfCoursesRepository.save(blockOfCourses);
    }

    public HashSet<Course> createCoursesSet(String[] courses)
    {
        HashSet<Course> coursesSet = new HashSet<>();
        for(String course : courses)
        {
            coursesSet.add(courseService.getCourseByCode(course));
        }
        return coursesSet;
    }
}
