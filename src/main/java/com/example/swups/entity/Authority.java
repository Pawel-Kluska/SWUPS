package com.example.swups.entity;

import jakarta.persistence.*;

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

    public Fieldsofstudy getFieldofstudyid() {
        return fieldofstudyid;
    }

    public void setFieldofstudyid(Fieldsofstudy fieldofstudyid) {
        this.fieldofstudyid = fieldofstudyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}