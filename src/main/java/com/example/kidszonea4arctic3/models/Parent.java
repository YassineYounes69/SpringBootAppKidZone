package com.example.kidszonea4arctic3.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Parent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5741785252220644855L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String pw;  //password
    private String fName; //first name
    private String lName;   //last name
    private int pTel;   //Parent Phone Number
    private boolean accStatus;  //Parent account status ( can be true or false )
    private String parentPic;   //Parent picture, to be implemented in future releases

    @OneToMany
    @JoinColumn(name = "parent_id" )
    private Set<Child> children = new HashSet<>();

    public Parent() {
    }

    public Parent(String email, String pw, String fName, String lName, int pTel, boolean accStatus) {
        this.email = email;
        this.pw = pw;
        this.fName = fName;
        this.lName = lName;
        this.pTel = pTel;
        this.accStatus = accStatus;
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

    public int getpTel() {
        return pTel;
    }

    public void setpTel(int pTel) {
        this.pTel = pTel;
    }

    public boolean isAccStatus() {
        return accStatus;
    }

    public void setAccStatus(boolean accStatus) {
        this.accStatus = accStatus;
    }

    public String getParentPic() {
        return parentPic;
    }

    public void setParentPic(String parentPic) {
        this.parentPic = parentPic;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
       // final String[] listOfChildren = {""};
        //this.getChildren().forEach(child -> listOfChildren[0] +=child.getfName()+"/" );

        return "Parent{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", pTel=" + pTel +
                ", accStatus=" + accStatus +
                //", books=" + listOfChildren[0] +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        return id != null ? id.equals(parent.id) : parent.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
