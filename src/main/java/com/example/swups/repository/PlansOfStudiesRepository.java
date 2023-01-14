package com.example.swups.repository;

import com.example.swups.entity.Plansofstudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansOfStudiesRepository extends JpaRepository<Plansofstudy, Long> {
}
