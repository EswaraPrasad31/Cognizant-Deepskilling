package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {
    private static final Logger log = LoggerFactory.getLogger(EmployeeDao.class);

    public static ArrayList<Employee> EMPLOYEE_LIST;

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml")) {
            EMPLOYEE_LIST = context.getBean("employeeList", ArrayList.class);
        } catch (Exception e) {
            log.error("Failed to load employee list from employee.xml", e);
        }
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                return;
            }
        }
        throw new EmployeeNotFoundException("Employee with id " + employee.getId() + " not found");
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId() == id) {
                EMPLOYEE_LIST.remove(i);
                return;
            }
        }
        throw new EmployeeNotFoundException("Employee with id " + id + " not found");
    }
}
