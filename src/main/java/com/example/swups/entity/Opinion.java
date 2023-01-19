package com.example.swups.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "opinions")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Opinion {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planofstudiesid", nullable = false)
    @ToString.Exclude
    private PlanOfStudy planOfStudy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appuserid", nullable = false)
    @ToString.Exclude
    private Appuser appuser;

    @Column(name = "ispositive", nullable = false)
    private Boolean isPositive;

    @Column(name = "dateofopinion", nullable = false)
    private Instant dateOfOpinion;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "dateofmodification", nullable = false)
    private Instant dateOfModification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Opinion opinion = (Opinion) o;
        return id != null && Objects.equals(id, opinion.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}