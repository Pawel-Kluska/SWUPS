package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "blocksofcourses")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BlockOfCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blockcharacterid", nullable = false)
    @ToString.Exclude
    private BlockCharacter blockCharacter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semesterid", nullable = false)
    @ToString.Exclude
    private Semester semester;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "BlockOfCourses_Courses",
            joinColumns = @JoinColumn(name = "BlockOfCoursesID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID"))
    @ToString.Exclude
    Set<Course> courses;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BlockOfCourses that = (BlockOfCourses) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}