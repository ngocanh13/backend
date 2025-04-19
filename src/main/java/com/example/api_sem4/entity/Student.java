package com.example.api_sem4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;

    private String address;

    private String email;

    private Date dob;

    private String dad;

    private String mom;

    @Column(name = "phone_dad")
    private String phoneDad;

    @Column(name = "phone_mom")
    private String phoneMom;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private com.example.api_sem4.entity.Class classId;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attendance> attendents;



    public List<Attendance> getAttendents() {
        return attendents;
    }

    public void setAttendents(List<Attendance> attendents) {
        this.attendents = attendents;
    }

    // --- Getters và Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDad() {
        return dad;
    }

    public void setDad(String dad) {
        this.dad = dad;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getPhoneDad() {
        return phoneDad;
    }

    public void setPhoneDad(String phoneDad) {
        this.phoneDad = phoneDad;
    }

    public String getPhoneMom() {
        return phoneMom;
    }

    public void setPhoneMom(String phoneMom) {
        this.phoneMom = phoneMom;
    }

    public com.example.api_sem4.entity.Class getClassId() {
        return classId;
    }

    public void setClassId(com.example.api_sem4.entity.Class classId) {
        this.classId = classId;
    }
}
