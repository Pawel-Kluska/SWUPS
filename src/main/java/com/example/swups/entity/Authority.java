package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disciplineid", nullable = false)
    private Discipline disciplineid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "facultyid", nullable = false)
    private Faculty facultyid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fieldofstudyid", nullable = false)
    private Fieldsofstudy fieldofstudyid;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Authority authority = (Authority) o;
        return id != null && Objects.equals(id, authority.id);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}