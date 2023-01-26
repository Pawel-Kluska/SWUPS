package com.example.swups.repository;

import com.example.swups.entity.CourseCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCharacterRepository extends JpaRepository<CourseCharacter, Integer> {
    CourseCharacter findCourseCharacterById(Integer id);
}
