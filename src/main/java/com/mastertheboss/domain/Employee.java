package com.mastertheboss.domain;

import com.mastertheboss.Constants.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "group_leader")
    private String groupleaderName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "Department")
    private Department department;

    public Employee() {}

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Employee(String name, String groupleaderName, Department department) {
        this.name = name;
        this.groupleaderName = groupleaderName;
        this.department = department;
    }

    // TODO: 20.05.2022 name and family name should be capital with first letter
    public Employee(String name, String familyName,Gender gender,String groupleaderName, String email, String phoneNumber, LocalDate birthday, Department department) {
        this.name = name;
        this.groupleaderName = groupleaderName;
        this.email = email;
        PhoneNumber = phoneNumber;
        setBirthday(birthday);
        this.department = department;
        this.familyName = familyName;
        this.gender = gender;
    }

    public Employee(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getGroupleaderName() {
        return groupleaderName;
    }

    public void setGroupleaderName(String groupleaderName) {
        this.groupleaderName = groupleaderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate  localDate = birthday;
        this.birthday = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", groupleaderName='" + groupleaderName + '\'' +
                ", email='" + email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                '}';
    }
}
