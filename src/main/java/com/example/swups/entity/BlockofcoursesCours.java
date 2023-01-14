package com.example.swups.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blockofcourses_courses")
public class BlockofcoursesCours {
    @EmbeddedId
    private BlockofcoursesCoursId id;

    @MapsId("blockofcoursesid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blockofcoursesid", nullable = false)
    private Blocksofcours blockofcoursesid;

    @MapsId("courseid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "courseid", nullable = false)
    private Cours courseid;

    public BlockofcoursesCoursId getId() {
        return id;
    }

    public void setId(BlockofcoursesCoursId id) {
        this.id = id;
    }

    public Blocksofcours getBlockofcoursesid() {
        return blockofcoursesid;
    }

    public void setBlockofcoursesid(Blocksofcours blockofcoursesid) {
        this.blockofcoursesid = blockofcoursesid;
    }

    public Cours getCourseid() {
        return courseid;
    }

    public void setCourseid(Cours courseid) {
        this.courseid = courseid;
    }

}