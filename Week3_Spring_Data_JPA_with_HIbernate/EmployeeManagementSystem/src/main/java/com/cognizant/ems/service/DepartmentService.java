package com.cognizant.ems.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ems.model.primary.Department;
import com.cognizant.ems.repository.primary.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
