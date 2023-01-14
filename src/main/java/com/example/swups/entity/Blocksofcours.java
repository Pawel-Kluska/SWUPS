package com.example.swups.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blocksofcourses")
public class Blocksofcours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blockcharacterid", nullable = false)
    private Blockcharacter blockcharacterid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semesterid", nullable = false)
    private Semester semesterid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blockcharacter getBlockcharacterid() {
        return blockcharacterid;
    }

    public void setBlockcharacterid(Blockcharacter blockcharacterid) {
        this.blockcharacterid = blockcharacterid;
    }

    public Semester getSemesterid() {
        return semesterid;
    }

    public void setSemesterid(Semester semesterid) {
        this.semesterid = semesterid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}