package com.example.swups.repository;

import com.example.swups.entity.WayOfCrediting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WayOfCreditingRepository extends JpaRepository<WayOfCrediting, Integer> {
    WayOfCrediting getWayOfCreditingById(Integer id);
}
