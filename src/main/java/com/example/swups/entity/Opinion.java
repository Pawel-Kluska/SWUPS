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
@RequiredArgsConstructor
public class Opinion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planofstudiesid", nullable = false)
    @ToString.Exclude
    private Plansofstudy planofstudiesid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "appuserid", nullable = false)
    private Appuser userid;

    @Column(name = "ispositive", nullable = false)
    private Boolean ispositive = false;

    @Column(name = "dateofopinion", nullable = false)
    private Instant dateofopinion;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "dateofmodification", nullable = false)
    private Instant dateofmodification;

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
    @Override
    public String toString() {
        return "";
    }
}