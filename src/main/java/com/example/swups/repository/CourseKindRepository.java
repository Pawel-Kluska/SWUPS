package com.example.swups.repository;

import com.example.swups.entity.CourseKind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseKindRepository extends JpaRepository<CourseKind, Integer> {
    CourseKind findCourseKindById(Integer id);
}
