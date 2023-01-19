package com.example.swups.repository;

import com.example.swups.entity.PlanOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanOfStudiesRepository extends JpaRepository<PlanOfStudy, Integer> {
}
