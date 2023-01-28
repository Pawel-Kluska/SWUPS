package com.example.swups.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BlockOfCoursesInfo {

    private String code;
    private String name;
    private String blockCharacter;
    private String semester;
    private String courses;
}
