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
@ToString
@RequiredArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wayofcreditingid", nullable = false)
    @ToString.Exclude
    private WayOfCrediting waysOfCrediting;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursetypeid", nullable = false)
    @ToString.Exclude
    private CourseType courseType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursekindid", nullable = false)
    @ToString.Exclude
    private CourseKind courseKind;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursecharacterid", nullable = false)
    @ToString.Exclude
    private CourseCharacter courseCharacter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "courseformid", nullable = false)
    @ToString.Exclude
    private CourseForm courseForm;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "weeklysumofhours", nullable = false)
    private Integer weeklySumOfHours;

    @Column(name = "sumofzzuhours", nullable = false)
    private Integer sumOfZZHours;

    @Column(name = "sumofcnpshours", nullable = false)
    private Integer sumOfCnpsHours;

    @Column(name = "sumofectspoints", nullable = false)
    private Integer sumOfEctsPoints;

    @Column(name = "sumofectspointsfrombuclasses", nullable = false)
    private Float sumOfEctsPointsFromBuClasses;

    @Column(name = "sumofectspointsfromdnclasses", nullable = false)
    private Float sumOfEctsPointsFromDnClasses;

    @Column(name = "discriminator", nullable = false)
    private String discriminator;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<BlockOfCourses> blocksOfCours;

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