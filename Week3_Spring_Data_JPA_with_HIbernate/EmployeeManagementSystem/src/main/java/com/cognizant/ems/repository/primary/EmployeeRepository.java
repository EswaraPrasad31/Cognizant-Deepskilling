package com.cognizant.ems.repository.primary;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ems.dto.EmployeeDto;
import com.cognizant.ems.model.primary.Employee;
import com.cognizant.ems.projection.EmployeeProjection;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find by name containing with pagination
    Page<Employee> findByNameContaining(String name, Pageable pageable);

    // Derived query method to find by email
    Optional<Employee> findByEmail(String email);

    // HQL Custom Query (Exercise 5)
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDepartmentName(@Param("deptName") String deptName);

    // Named Query executions matching class level @NamedQuery names (Exercise 5)
    Employee findByEmailNamed(@Param("email") String email);
    List<Employee> findByMinSalaryNamed(@Param("salary") double salary);

    // Interface-based Projection retrieval (Exercise 8)
    List<EmployeeProjection> findProjectedBy(Pageable pageable);

    // Class-based Projection using constructor expression (Exercise 8)
    @Query("SELECT new com.cognizant.ems.dto.EmployeeDto(e.id, e.name, e.email, e.department.name) FROM Employee e")
    List<EmployeeDto> findAllEmployeeDtos();
}
