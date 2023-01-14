package com.example.swups.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "semesters")
public class Semester {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planofstudiesid", nullable = false)
    private Plansofstudy planofstudiesid;

    @Column(name = "code")
    private String code;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "ectspointsdeficite", nullable = false)
    private Integer ectspointsdeficite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plansofstudy getPlanofstudiesid() {
        return planofstudiesid;
    }

    public void setPlanofstudiesid(Plansofstudy planofstudiesid) {
        this.planofstudiesid = planofstudiesid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEctspointsdeficite() {
        return ectspointsdeficite;
    }

    public void setEctspointsdeficite(Integer ectspointsdeficite) {
        this.ectspointsdeficite = ectspointsdeficite;
    }

}