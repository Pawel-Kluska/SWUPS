package com.example.swups.service;

import com.example.swups.entity.PlanOfStudy;
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

    public List<PlanOfStudy> getPlansOfStudies() {
        return planOfStudiesRepository.findAll();
    }
    public PlanOfStudy getPlanOfStudiesById(int id) {
        Optional<PlanOfStudy> planById = planOfStudiesRepository.findById(id);
        return planById.orElseThrow(EntityNotFoundException::new);
    }
    public void savePlan(PlanOfStudy planOfStudy){
        planOfStudiesRepository.save(planOfStudy);
    }
}
