package com.example.swups.service;

import com.example.swups.Utils;
import com.example.swups.entity.*;
import com.example.swups.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseCharacterService courseCharacterService;
    private final CourseFormService courseFormService;
    private final CourseKindService courseKindService;
    private final CourseTypeService courseTypeService;
    private final WayOfCreditingService wayOfCreditingService;

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    public Course getCourseByCode(String code)
    {
        return courseRepository.findCourseByCode(code);
    }

    public void saveCourse(Course course) throws UserPrincipalNotFoundException {
        Optional<User> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty())
        {
            throw new UserPrincipalNotFoundException("User not logged in");
        }
        courseRepository.save(course);
    }

    public Course buildCourseFromCourseInfo(CourseInfo info)
    {
        WayOfCrediting wayOfCrediting = wayOfCreditingService.getWayOfCreditingById(Integer.parseInt(info.getWayOfCrediting()));
        CourseCharacter courseCharacter = courseCharacterService.getCourseCharacterById(Integer.parseInt(info.getCourseCharacter()));
        CourseType courseType = courseTypeService.getCourseTypeById(Integer.parseInt(info.getCourseType()));
        CourseKind courseKind = courseKindService.getCourseKindById(Integer.parseInt(info.getCourseKind()));
        CourseForm courseForm = courseFormService.getCourseFormById(Integer.parseInt(info.getCourseForm()));

        return Course.builder().courseCharacter(courseCharacter)
                .courseForm(courseForm)
                .courseKind(courseKind)
                .courseType(courseType)
                .wayOfCrediting(wayOfCrediting)
                .code(info.getCode())
                .name(info.getName())
                .weeklySumOfHours(info.getWeeklySumOfHours())
                .sumOfZZUHours(info.getSumOfZZUHours())
                .sumOfCnpsHours(info.getSumOfCnpsHours())
                .sumOfEctsPoints(info.getSumOfEctsPoints())
                .sumOfEctsPointsFromBuClasses(info.getSumOfEctsPointsFromBuClasses())
                .sumOfEctsPointsFromDnClasses(info.getSumOfEctsPointsFromDnClasses())
                .build();
    }
}
