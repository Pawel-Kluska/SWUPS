package com.example.swups.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseInfo {
    private String wayOfCrediting;
    private String courseType;
    private String courseKind;
    private String courseCharacter;
    private String courseForm;
    private String code;
    private String name;
    private Integer weeklySumOfHours;
    private Integer sumOfZZUHours;
    private Integer sumOfCnpsHours;
    private Integer sumOfEctsPoints;
    private Float sumOfEctsPointsFromBuClasses;
    private Float sumOfEctsPointsFromDnClasses;
    private String studyeffects;

}
