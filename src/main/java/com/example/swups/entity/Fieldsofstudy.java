package com.example.swups.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fieldsofstudy")
public class Fieldsofstudy {
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

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Discipline getDisciplineid() {
        return disciplineid;
    }

    public void setDisciplineid(Discipline disciplineid) {
        this.disciplineid = disciplineid;
    }

    public Faculty getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(Faculty facultyid) {
        this.facultyid = facultyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

}