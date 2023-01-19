package com.example.swups.repository;

import com.example.swups.entity.PlanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanStatusRepository extends JpaRepository<PlanStatus, Integer> {
    PlanStatus getPlanStatusByName(String name);
}
