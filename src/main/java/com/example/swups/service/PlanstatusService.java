package com.example.swups.service;

import com.example.swups.entity.Planstatus;
import com.example.swups.repository.PlanstatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanstatusService {
    private final PlanstatusRepository planstatusRepository;

    public Planstatus getPlanStatusByName(String name){
        return planstatusRepository.getPlanstatusByName(name);
    }
}
