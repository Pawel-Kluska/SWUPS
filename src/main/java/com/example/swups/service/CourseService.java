package com.example.swups.service;

import com.example.swups.Utils;
import com.example.swups.entity.Course;
import com.example.swups.entity.User;
import com.example.swups.exceptions.EmptyCourseCodeException;
import com.example.swups.exceptions.EmptyCourseNameException;
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

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id)
    {
        return courseRepository.findCourseById(id);
    }

    public Course getCourseByCode(String code)
    {
        return courseRepository.findCourseByCode(code);
    }

    public List<Course> getCoursesByName(String name)
    {
        return courseRepository.findCoursesByName(name);
    }

    public void saveCourse(Course course) throws EmptyCourseNameException, EmptyCourseCodeException, UserPrincipalNotFoundException {
        Optional<User> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty())
        {
            throw new UserPrincipalNotFoundException("User not logged in");
        }
        if (course.getCode().equals(""))
        {
            throw new EmptyCourseCodeException("Course code cannot be empty!");
        }
        if (course.getName().equals(""))
        {
            throw new EmptyCourseNameException("Course name cannot be empty!");
        }
        courseRepository.save(course);
    }
}
