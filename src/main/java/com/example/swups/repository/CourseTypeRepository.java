package com.example.swups.repository;

import com.example.swups.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
    CourseType findCourseTypeById(Integer id);
}
