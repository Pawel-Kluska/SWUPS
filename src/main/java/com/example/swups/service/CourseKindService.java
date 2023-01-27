package com.example.swups.service;

import com.example.swups.entity.CourseKind;
import com.example.swups.repository.CourseKindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseKindService {
    private final CourseKindRepository courseKindRepository;
    public List<CourseKind> getAllCourseKinds()
    {
        return courseKindRepository.findAll();
    }
    public CourseKind getCourseKindById(Integer id)
    {
        return courseKindRepository.findCourseKindById(id);
    }
}
