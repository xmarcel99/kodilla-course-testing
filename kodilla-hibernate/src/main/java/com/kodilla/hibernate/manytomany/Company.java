package com.kodilla.hibernate.manytomany;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@NamedNativeQuery(
        name = "Company.findCompanyByName",
        query = "select*from companies where substring(company_name,1,3) = :userInput ",
        resultClass = Company.class
)

@NamedNativeQuery(
        name = "Company.findCompanyByFragment",
        query = "select*from companies where company_name like Concat('%',:fragment,'%') ",
        resultClass = Company.class
)
@Entity
@Table(name = "companies")
public class Company {
    private int id;
    private String name;
    private List<Employee> employees = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "companies")

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "company_id", unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name = "company_name")
    public String getName() {
        return name;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}