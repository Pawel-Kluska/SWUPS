package com.example.swups.service;

import com.example.swups.entity.Semester;
import com.example.swups.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public List<Semester> getAllSemesters()
    {
        return semesterRepository.findAll();
    }
    public Semester getSemesterByCode(String code)
    {
        return semesterRepository.getSemesterByCode(code);
    }
}
