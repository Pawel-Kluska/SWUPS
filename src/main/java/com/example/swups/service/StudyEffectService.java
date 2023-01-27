package com.example.swups.service;

import com.example.swups.entity.Course;
import com.example.swups.entity.StudyEffect;
import com.example.swups.repository.StudyEffectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyEffectService {

    private final StudyEffectRepository studyEffectRepository;

    public List<StudyEffect> getPossibleStudyEffects()
    {
        return studyEffectRepository.findByCourseidIsNull();
    }

    public StudyEffect getStudyEffectByCode(String code)
    {
        return studyEffectRepository.findStudyEffectByCode(code);
    }

    public void update(String code, Course course)
    {
        StudyEffect effectToUpdate = getStudyEffectByCode(code);
        effectToUpdate.setCourseid(course);
        studyEffectRepository.save(effectToUpdate);
    }
}
