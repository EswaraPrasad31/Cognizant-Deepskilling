package com.cognizant.orm_learn.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Employee> getAllPermanentEmployees() {
        return employeeRepository.getAllPermanentEmployees();
    }

    @Transactional(readOnly = true)
    public double getAverageSalary(int departmentId) {
        return employeeRepository.getAverageSalary(departmentId);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByCriteria(Double minSalary, Boolean permanent) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employee = cq.from(Employee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        if (minSalary != null) {
            predicates.add(cb.greaterThanOrEqualTo(employee.get("salary"), minSalary));
        }
        if (permanent != null) {
            predicates.add(cb.equal(employee.get("permanent"), permanent));
        }
        
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
