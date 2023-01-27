package com.example.swups.repository;

import com.example.swups.entity.StudyEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyEffectRepository extends JpaRepository<StudyEffect, Integer> {
    StudyEffect findStudyEffectById(Integer id);
    StudyEffect findStudyEffectByCode(String code);
    List<StudyEffect> findByCourseidIsNull();
}
