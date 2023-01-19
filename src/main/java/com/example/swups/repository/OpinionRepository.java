package com.example.swups.repository;

import com.example.swups.entity.Opinion;
import com.example.swups.entity.PlanOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
    List<Opinion> findOpinionByPlanOfStudy(PlanOfStudy planOfStudy);
}
