package com.example.swups.service;

import com.example.swups.entity.CourseCharacter;
import com.example.swups.repository.CourseCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCharacterService {

    private final CourseCharacterRepository courseCharacterRepository;

    public List<CourseCharacter> getAllCourseCharacters()
    {
        return courseCharacterRepository.findAll();
    }

    public CourseCharacter getCourseCharacterById(Integer id)
    {
        return courseCharacterRepository.findCourseCharacterById(id);
    }
}