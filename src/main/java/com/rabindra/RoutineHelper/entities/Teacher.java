package com.rabindra.RoutineHelper.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;
@Entity
@Table(name="teacher")
@NoArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String name;

    private String email;

    private String department;

    private Date hireDate;

    public Teacher(Long teacherId, String name, String email, String department, Date hireDate) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
