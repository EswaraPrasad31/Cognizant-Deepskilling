package com.cognizant.orm_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
