package com.example.swups.service;

import com.example.swups.entity.CourseForm;
import com.example.swups.repository.CourseFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseFormService {
    private final CourseFormRepository courseFormRepository;
    public List<CourseForm> getAllCourseForms()
    {
        return courseFormRepository.findAll();
    }
    public CourseForm getCourseFormById(Integer id)
    {
        return courseFormRepository.findCourseFormById(id);
    }
}
