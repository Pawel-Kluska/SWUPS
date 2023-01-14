package com.example.swups.repository;

import com.example.swups.entity.Fieldsofstudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<Fieldsofstudy, Long> {
}
