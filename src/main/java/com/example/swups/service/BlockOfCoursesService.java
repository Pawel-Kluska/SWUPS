package com.example.swups.service;

import com.example.swups.repository.BlockOfCoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockOfCoursesService {

    private final BlockOfCoursesRepository blockOfCoursesRepository;
}
