package com.example.swups.repository;

import com.example.swups.entity.Fieldsofstudy;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion>findOpinionByPlanofstudiesid(Plansofstudy plansofstudy);
}
