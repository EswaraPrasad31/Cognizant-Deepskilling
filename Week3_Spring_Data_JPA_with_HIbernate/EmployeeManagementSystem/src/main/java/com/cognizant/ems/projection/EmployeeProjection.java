package com.cognizant.ems.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

    Long getId();

    String getName();

    String getEmail();

    // Open projection displaying composite name and email (Exercise 8)
    @Value("#{target.name} - #{target.email}")
    String getNameAndEmail();
}
