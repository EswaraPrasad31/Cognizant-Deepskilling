package com.cognizant.ems.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmployeeDto {

    private final Long id;
    private final String name;
    private final String email;
    private final String departmentName;

    // Constructor for constructor-expression projections (Exercise 8)
    public EmployeeDto(Long id, String name, String email, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
    }
}
