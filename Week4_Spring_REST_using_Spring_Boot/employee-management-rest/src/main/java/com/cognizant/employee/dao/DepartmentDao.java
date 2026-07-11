package com.cognizant.employee.dao;

import com.cognizant.employee.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDao {
    private static final Logger log = LoggerFactory.getLogger(DepartmentDao.class);

    public static ArrayList<Department> DEPARTMENT_LIST;

    @SuppressWarnings("unchecked")
    public DepartmentDao() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml")) {
            DEPARTMENT_LIST = context.getBean("departmentList", ArrayList.class);
        } catch (Exception e) {
            log.error("Failed to load department list from employee.xml", e);
        }
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}
