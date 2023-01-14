package com.example.swups.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BlockofcoursesCoursId implements Serializable {
    private static final long serialVersionUID = -5287067036291662263L;
    @Column(name = "blockofcoursesid", nullable = false)
    private Integer blockofcoursesid;

    @Column(name = "courseid", nullable = false)
    private Integer courseid;

    public Integer getBlockofcoursesid() {
        return blockofcoursesid;
    }

    public void setBlockofcoursesid(Integer blockofcoursesid) {
        this.blockofcoursesid = blockofcoursesid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BlockofcoursesCoursId entity = (BlockofcoursesCoursId) o;
        return Objects.equals(this.blockofcoursesid, entity.blockofcoursesid) &&
                Objects.equals(this.courseid, entity.courseid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockofcoursesid, courseid);
    }

}