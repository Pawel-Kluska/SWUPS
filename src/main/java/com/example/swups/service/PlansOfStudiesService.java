package com.example.swups.service;

import com.example.swups.entity.Plansofstudy;
import com.example.swups.repository.PlansOfStudiesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlansOfStudiesService {

    private final PlansOfStudiesRepository plansOfStudiesRepository;

    public List<Plansofstudy> getPlansOfStudies() {
        return plansOfStudiesRepository.findAll();
    }

    public Plansofstudy getPlanOfStudiesById(long id) {
        Optional<Plansofstudy> planById = plansOfStudiesRepository.findById(id);
        return planById.orElseThrow(EntityNotFoundException::new);
    }
}
