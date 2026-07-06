package com.cognizant.orm_learn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Department get(int id) {
        LOGGER.info("Start get department");
        return departmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Department department) {
        LOGGER.info("Start save department");
        departmentRepository.save(department);
        LOGGER.info("End save department");
    }
}
