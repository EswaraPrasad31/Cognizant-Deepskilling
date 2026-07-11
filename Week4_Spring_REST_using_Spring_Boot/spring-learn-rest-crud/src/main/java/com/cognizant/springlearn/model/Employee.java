package com.cognizant.springlearn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class Employee {
    @NotNull(message = "Employee ID is required")
    private Integer id;

    @NotBlank(message = "Employee name is required")
    @Size(min = 1, max = 30, message = "Employee name must be between 1 and 30 characters")
    private String name;

    @NotNull(message = "Employee salary is required")
    @Min(value = 0, message = "Employee salary must be positive")
    private Double salary;

    @NotNull(message = "Employee permanency status is required")
    private Boolean permanent;

    private Department department;

    private List<Skill> skill;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(Integer id, String name, Double salary, Boolean permanent, Department department, List<Skill> skill, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.permanent = permanent;
        this.department = department;
        this.skill = skill;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + ", permanent=" + permanent
                + ", department=" + department + ", skill=" + skill + ", dateOfBirth=" + dateOfBirth + "}";
    }
}
