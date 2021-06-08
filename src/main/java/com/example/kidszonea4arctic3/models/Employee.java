package com.example.kidszonea4arctic3.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String pw;
    private String fName; //first name
    private String lName; //Last name
    private String role;
    private int eTel;   //Parent Phone Number
    @ManyToOne
    private ChildCareCenter ccc; //the childcare center that the employee works at

    private boolean availability; //specific attribute to Doctor role


    public Employee() {
    }








    public Employee(String email, String pw, String fName, String lName, String role, boolean availability, ChildCareCenter ccc,int eTel) {
        this.email = email;
        this.pw = pw;
        this.fName = fName;
        this.lName = lName;
        this.role = role;
        this.availability = availability;
        this.ccc = ccc;
        this.eTel= eTel;
    }

    public ChildCareCenter getCcc() {
        return ccc;
    }

    public void setCcc(ChildCareCenter ccc) {
        this.ccc = ccc;
    }

    public int geteTel() {
        return eTel;
    }

    public void seteTel(int eTel) {
        this.eTel = eTel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", role='" + role + '\'' +
                ", eTel=" + eTel +
                '}';
    }
}
