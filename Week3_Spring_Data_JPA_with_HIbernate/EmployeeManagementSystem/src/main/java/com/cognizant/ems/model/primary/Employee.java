package com.cognizant.ems.model.primary;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@DynamicInsert
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Employee.findByEmailNamed", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByMinSalaryNamed", query = "SELECT e FROM Employee e WHERE e.salary >= :salary")
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "emp_email", nullable = false, unique = true)
    private String email;

    @Column(name = "emp_salary")
    private double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_dept_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String email, double salary, Department department) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", salary=" + salary + ", department=" + department + "]";
    }
}
