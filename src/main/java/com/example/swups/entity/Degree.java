package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "degrees")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class Degree {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Degree degree = (Degree) o;
        return id != null && Objects.equals(id, degree.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}