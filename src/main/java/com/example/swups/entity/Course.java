package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wayofcreditingid")
    @ToString.Exclude
    private WayOfCrediting wayOfCrediting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coursetypeid")
    @ToString.Exclude
    private CourseType courseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coursekindid")
    @ToString.Exclude
    private CourseKind courseKind;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursecharacterid", nullable = false)
    @ToString.Exclude
    private CourseCharacter courseCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseformid")
    @ToString.Exclude
    private CourseForm courseForm;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "weeklysumofhours")
    private Integer weeklySumOfHours;

    @Column(name = "sumofzzuhours")
    private Integer sumOfZZUHours;

    @Column(name = "sumofcnpshours")
    private Integer sumOfCnpsHours;

    @Column(name = "sumofectspoints")
    private Integer sumOfEctsPoints;

    @Column(name = "sumofectspointsfrombuclasses")
    private Float sumOfEctsPointsFromBuClasses;

    @Column(name = "sumofectspointsfromdnclasses")
    private Float sumOfEctsPointsFromDnClasses;

    @Column(name = "discriminator")
    private String discriminator;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<BlockOfCourses> blocksOfCourses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}