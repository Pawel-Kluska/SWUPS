package com.example.swups.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "plansofstudies")
public class Plansofstudy {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profileid", nullable = false)
    private Profile profileid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formofstudiesid", nullable = false)
    private Formsofstudy formofstudiesid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planstatusid", nullable = false)
    private Planstatus planstatusid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "educationlevelid", nullable = false)
    private Educationlevel educationlevelid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "degreeid", nullable = false)
    private Degree degreeid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fieldofstudyid", nullable = false)
    private Fieldsofstudy fieldofstudyid;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "dateofcreation", nullable = false)
    private Instant dateofcreation;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "languageofstudy", nullable = false)
    private String languageofstudy;

    @Column(name = "educationcycle")
    private String educationcycle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfileid() {
        return profileid;
    }

    public void setProfileid(Profile profileid) {
        this.profileid = profileid;
    }

    public Formsofstudy getFormofstudiesid() {
        return formofstudiesid;
    }

    public void setFormofstudiesid(Formsofstudy formofstudiesid) {
        this.formofstudiesid = formofstudiesid;
    }

    public Planstatus getPlanstatusid() {
        return planstatusid;
    }

    public void setPlanstatusid(Planstatus planstatusid) {
        this.planstatusid = planstatusid;
    }

    public Educationlevel getEducationlevelid() {
        return educationlevelid;
    }

    public void setEducationlevelid(Educationlevel educationlevelid) {
        this.educationlevelid = educationlevelid;
    }

    public Degree getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(Degree degreeid) {
        this.degreeid = degreeid;
    }

    public Fieldsofstudy getFieldofstudyid() {
        return fieldofstudyid;
    }

    public void setFieldofstudyid(Fieldsofstudy fieldofstudyid) {
        this.fieldofstudyid = fieldofstudyid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Instant getDateofcreation() {
        return dateofcreation;
    }

    public void setDateofcreation(Instant dateofcreation) {
        this.dateofcreation = dateofcreation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLanguageofstudy() {
        return languageofstudy;
    }

    public void setLanguageofstudy(String languageofstudy) {
        this.languageofstudy = languageofstudy;
    }

    public String getEducationcycle() {
        return educationcycle;
    }

    public void setEducationcycle(String educationcycle) {
        this.educationcycle = educationcycle;
    }

}