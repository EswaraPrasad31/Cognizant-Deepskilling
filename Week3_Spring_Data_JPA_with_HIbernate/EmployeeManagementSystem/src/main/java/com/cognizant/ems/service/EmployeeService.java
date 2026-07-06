package com.cognizant.ems.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ems.dto.EmployeeDto;
import com.cognizant.ems.model.primary.Employee;
import com.cognizant.ems.projection.EmployeeProjection;
import com.cognizant.ems.repository.primary.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findByNameContaining(String name, Pageable pageable) {
        return employeeRepository.findByNameContaining(name, pageable);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentName(String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }

    @Transactional(readOnly = true)
    public Employee findByEmailNamed(String email) {
        return employeeRepository.findByEmailNamed(email);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByMinSalaryNamed(double salary) {
        return employeeRepository.findByMinSalaryNamed(salary);
    }

    @Transactional(readOnly = true)
    public List<EmployeeProjection> findProjectedBy(Pageable pageable) {
        return employeeRepository.findProjectedBy(pageable);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAllEmployeeDtos() {
        return employeeRepository.findAllEmployeeDtos();
    }
}
