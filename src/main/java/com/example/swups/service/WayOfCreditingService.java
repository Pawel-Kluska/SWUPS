package com.example.swups.service;

import com.example.swups.entity.WayOfCrediting;
import com.example.swups.repository.WayOfCreditingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WayOfCreditingService {

    private final WayOfCreditingRepository wayOfCreditingRepository;

    public List<WayOfCrediting> getAllWaysOfCrediting()
    {
        return wayOfCreditingRepository.findAll();
    }

    public WayOfCrediting getWayOfCreditingById(Integer id)
    {
        return wayOfCreditingRepository.findWayOfCreditingById(id);
    }
}
