package com.mastertheboss.domain;

import com.mastertheboss.util.MakeFirstLettercapital;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String department_name;


    @OneToMany(mappedBy="department",cascade=CascadeType.ALL)
    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
        super();
    }
    public Department(String department_name) {
        setDepartment_name(department_name);
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDepartment_name() {
        return department_name;
    }
    public void setDepartment_name(String department_name) {
        this.department_name = MakeFirstLettercapital.makeCapital(department_name);
    }


    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
