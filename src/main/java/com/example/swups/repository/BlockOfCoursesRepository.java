package com.example.swups.repository;

import com.example.swups.entity.BlockOfCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockOfCoursesRepository extends JpaRepository<BlockOfCourses, Integer> {

    public BlockOfCourses findBlockOfCoursesByCode(String code);
}
