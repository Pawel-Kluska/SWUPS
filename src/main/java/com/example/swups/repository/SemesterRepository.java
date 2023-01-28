package com.example.swups.repository;

import com.example.swups.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    public Semester getSemesterByCode(String code);
}
