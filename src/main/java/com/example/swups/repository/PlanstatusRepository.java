package com.example.swups.repository;

import com.example.swups.entity.Planstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface PlanstatusRepository extends JpaRepository<Planstatus, Integer> {
    Planstatus getPlanstatusByName(String name);
}
