package com.cognizant.ems.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ems.model.primary.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class EmployeeBatchService {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    // Batch insertion utilizing Hibernate batch_size configurations (Exercise 10)
    @Transactional
    public void saveEmployeesInBatch(List<Employee> employees) {
        int batchSize = 50;
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i > 0 && i % batchSize == 0) {
                // Flush database changes and clear context memory
                entityManager.flush();
                entityManager.clear();
            }
        }
        // Flush remaining records
        entityManager.flush();
        entityManager.clear();
    }
}
