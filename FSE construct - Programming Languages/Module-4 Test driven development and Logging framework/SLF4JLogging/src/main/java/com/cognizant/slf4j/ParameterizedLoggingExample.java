package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {

    private static final Logger logger =
            LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {

        String employeeName = "Eswar";
        int employeeId = 101;
        String department = "IT";
        double salary = 55000.50;

        logger.info("Employee Name: {}", employeeName);

        logger.info("Employee ID: {}", employeeId);

        logger.info("Department: {}", department);

        logger.info("Salary: {}", salary);

        logger.info("Employee {} belongs to {} department with salary {}",
                employeeName,
                department,
                salary);

    }

}