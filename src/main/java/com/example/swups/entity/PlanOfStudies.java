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
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlanOfStudies {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profileid", nullable = false)
    @ToString.Exclude
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formofstudiesid", nullable = false)
    @ToString.Exclude
    private FormOfStudy formOfStudy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planstatusid", nullable = false)
    @ToString.Exclude
    private PlanStatus planStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "educationlevelid", nullable = false)
    @ToString.Exclude
    private EducationLevel educationLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "degreeid", nullable = false)
    @ToString.Exclude
    private Degree degree;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fieldofstudyid", nullable = false)
    @ToString.Exclude
    private FieldOfStudy fieldOfStudy;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "dateofcreation", nullable = false)
    private Instant dateOfCreation;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "languageofstudy", nullable = false)
    private String languageOfStudy;

    @Column(name = "educationcycle")
    private String educationCycle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlanOfStudies that = (PlanOfStudies) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}