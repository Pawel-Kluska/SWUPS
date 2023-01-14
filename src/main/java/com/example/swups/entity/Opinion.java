package com.example.swups.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "opinions")
public class Opinion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planofstudiesid", nullable = false)
    private Plansofstudy planofstudiesid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User userid;

    @Column(name = "ispositive", nullable = false)
    private Boolean ispositive = false;

    @Column(name = "dateofopinion", nullable = false)
    private Instant dateofopinion;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "dateofmodification", nullable = false)
    private Instant dateofmodification;

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

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Boolean getIspositive() {
        return ispositive;
    }

    public void setIspositive(Boolean ispositive) {
        this.ispositive = ispositive;
    }

    public Instant getDateofopinion() {
        return dateofopinion;
    }

    public void setDateofopinion(Instant dateofopinion) {
        this.dateofopinion = dateofopinion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getDateofmodification() {
        return dateofmodification;
    }

    public void setDateofmodification(Instant dateofmodification) {
        this.dateofmodification = dateofmodification;
    }

}