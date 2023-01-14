package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "plansofstudies")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Plansofstudy {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profileid", nullable = false)
    @ToString.Exclude
    private Profile profileid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formofstudiesid", nullable = false)
    @ToString.Exclude
    private Formsofstudy formofstudiesid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planstatusid", nullable = false)
    @ToString.Exclude
    private Planstatus planstatusid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "educationlevelid", nullable = false)
    @ToString.Exclude
    private Educationlevel educationlevelid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "degreeid", nullable = false)
    @ToString.Exclude
    private Degree degreeid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fieldofstudyid", nullable = false)
    @ToString.Exclude
    private Fieldsofstudy fieldofstudyid;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "dateofcreation", nullable = false)
    private Instant dateofcreation;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "languageofstudy", nullable = false)
    private String languageofstudy;

    @Column(name = "educationcycle")
    private String educationcycle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plansofstudy that = (Plansofstudy) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}