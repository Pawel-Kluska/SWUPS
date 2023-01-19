package com.example.swups.service;

import com.example.swups.entity.PlanOfStudies;
import com.example.swups.repository.PlanOfStudiesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanOfStudiesService {

    private final PlanOfStudiesRepository planOfStudiesRepository;

    public List<PlanOfStudies> getPlansOfStudies() {
        return planOfStudiesRepository.findAll();
    }
    public PlanOfStudies getPlanOfStudiesById(int id) {
        Optional<PlanOfStudies> planById = planOfStudiesRepository.findById(id);
        return planById.orElseThrow(EntityNotFoundException::new);
    }
    public void savePlan(PlanOfStudies planOfStudies){
        planOfStudiesRepository.save(planOfStudies);
    }
}
