package com.example.swups.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wayofcreditingid", nullable = false)
    private Waysofcrediting wayofcreditingid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursetypeid", nullable = false)
    private Coursetype coursetypeid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursekindid", nullable = false)
    private Coursekind coursekindid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coursecharacterid", nullable = false)
    private Coursecharacter coursecharacterid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "courseformid", nullable = false)
    private Courseform courseformid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid")
    private Cours courseid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "weeklysumofhours", nullable = false)
    private Integer weeklysumofhours;

    @Column(name = "sumofzzuhours", nullable = false)
    private Integer sumofzzuhours;

    @Column(name = "sumofcnpshours", nullable = false)
    private Integer sumofcnpshours;

    @Column(name = "sumofectspoints", nullable = false)
    private Integer sumofectspoints;

    @Column(name = "sumofectspointsfrombuclasses", nullable = false)
    private Float sumofectspointsfrombuclasses;

    @Column(name = "sumofectspointsfromdnclasses", nullable = false)
    private Float sumofectspointsfromdnclasses;

    @Column(name = "discriminator", nullable = false)
    private String discriminator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Waysofcrediting getWayofcreditingid() {
        return wayofcreditingid;
    }

    public void setWayofcreditingid(Waysofcrediting wayofcreditingid) {
        this.wayofcreditingid = wayofcreditingid;
    }

    public Coursetype getCoursetypeid() {
        return coursetypeid;
    }

    public void setCoursetypeid(Coursetype coursetypeid) {
        this.coursetypeid = coursetypeid;
    }

    public Coursekind getCoursekindid() {
        return coursekindid;
    }

    public void setCoursekindid(Coursekind coursekindid) {
        this.coursekindid = coursekindid;
    }

    public Coursecharacter getCoursecharacterid() {
        return coursecharacterid;
    }

    public void setCoursecharacterid(Coursecharacter coursecharacterid) {
        this.coursecharacterid = coursecharacterid;
    }

    public Courseform getCourseformid() {
        return courseformid;
    }

    public void setCourseformid(Courseform courseformid) {
        this.courseformid = courseformid;
    }

    public Cours getCourseid() {
        return courseid;
    }

    public void setCourseid(Cours courseid) {
        this.courseid = courseid;
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

    public Integer getWeeklysumofhours() {
        return weeklysumofhours;
    }

    public void setWeeklysumofhours(Integer weeklysumofhours) {
        this.weeklysumofhours = weeklysumofhours;
    }

    public Integer getSumofzzuhours() {
        return sumofzzuhours;
    }

    public void setSumofzzuhours(Integer sumofzzuhours) {
        this.sumofzzuhours = sumofzzuhours;
    }

    public Integer getSumofcnpshours() {
        return sumofcnpshours;
    }

    public void setSumofcnpshours(Integer sumofcnpshours) {
        this.sumofcnpshours = sumofcnpshours;
    }

    public Integer getSumofectspoints() {
        return sumofectspoints;
    }

    public void setSumofectspoints(Integer sumofectspoints) {
        this.sumofectspoints = sumofectspoints;
    }

    public Float getSumofectspointsfrombuclasses() {
        return sumofectspointsfrombuclasses;
    }

    public void setSumofectspointsfrombuclasses(Float sumofectspointsfrombuclasses) {
        this.sumofectspointsfrombuclasses = sumofectspointsfrombuclasses;
    }

    public Float getSumofectspointsfromdnclasses() {
        return sumofectspointsfromdnclasses;
    }

    public void setSumofectspointsfromdnclasses(Float sumofectspointsfromdnclasses) {
        this.sumofectspointsfromdnclasses = sumofectspointsfromdnclasses;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

}