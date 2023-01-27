package com.example.swups.repository;

import com.example.swups.entity.CourseForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseFormRepository extends JpaRepository<CourseForm, Integer> {
    CourseForm findCourseFormById(Integer id);
}
