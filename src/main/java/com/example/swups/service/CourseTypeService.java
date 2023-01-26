package com.example.swups.service;

import com.example.swups.entity.CourseType;
import com.example.swups.repository.CourseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseTypeService {

    private final CourseTypeRepository courseTypeRepository;

    public List<CourseType> getAllCourseTypes()
    {
        return courseTypeRepository.findAll();
    }

    public CourseType getCourseTypeById(Integer id)
    {
        return courseTypeRepository.findCourseTypeById(id);
    }
}
