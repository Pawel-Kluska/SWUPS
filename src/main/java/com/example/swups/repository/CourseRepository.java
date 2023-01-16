package com.example.swups.repository;

import com.example.swups.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findCourseById(Integer id);

    Course findCourseByCode(String code);

    List<Course> findCoursesByName(String name);
}
