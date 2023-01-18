package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "semesters")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Semester {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planofstudiesid", nullable = false)
    @ToString.Exclude
    private Plansofstudy planofstudiesid;

    @Column(name = "code")
    private String code;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "ectspointsdeficite", nullable = false)
    private Integer ectspointsdeficite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Semester semester = (Semester) o;
        return id != null && Objects.equals(id, semester.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}