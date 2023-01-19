package com.example.swups.service;

import com.example.swups.entity.PlanStatus;
import com.example.swups.repository.PlanStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanStatusService {
    private final PlanStatusRepository planstatusRepository;

    public PlanStatus getPlanStatusByName(String name){
        return planstatusRepository.getPlanStatusByName(name);
    }
}
