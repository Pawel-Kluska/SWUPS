package com.example.swups.service;

import com.example.swups.entity.Course;
import com.example.swups.exceptions.EmptyCourseNameException;
import com.example.swups.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

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

    public void saveCourse(Course course) throws EmptyCourseNameException
    {
        if (course.getName().equals(""))
        {
            throw new EmptyCourseNameException("Course name cannot be empty!");
        }
        courseRepository.save(course);
    }
}
