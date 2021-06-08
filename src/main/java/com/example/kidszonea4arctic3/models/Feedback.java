package com.example.kidszonea4arctic3.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(name="descr",columnDefinition="LONGTEXT")
    private String descr;
    @Column(name="reply",columnDefinition="LONGTEXT")
    private String reply;
    private String status;
    private Instant creationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Parent parent;
    @ManyToOne(cascade = CascadeType.ALL)
    private ChildCareCenter childCareCenter;
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public Feedback() {
    }

    public Feedback(String title, String descr, String reply, String status, Instant creationDate, Parent parent, Employee employee,ChildCareCenter childCareCenter) {
        this.title = title;
        this.descr = descr;
        this.reply = reply;
        this.status = status;
        this.creationDate = creationDate;
        this.parent = parent;
        this.employee = employee;
        this.childCareCenter = childCareCenter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ChildCareCenter getChildCareCenter() {
        return childCareCenter;
    }

    public void setChildCareCenter(ChildCareCenter childCareCenter) {
        this.childCareCenter = childCareCenter;
    }
}
