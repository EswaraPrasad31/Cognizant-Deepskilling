package com.cognizant.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.ems.dto.EmployeeDto;
import com.cognizant.ems.model.primary.Employee;
import com.cognizant.ems.projection.EmployeeProjection;
import com.cognizant.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.findById(id)
                .map(emp -> {
                    emp.setName(employeeDetails.getName());
                    emp.setEmail(employeeDetails.getEmail());
                    emp.setSalary(employeeDetails.getSalary());
                    emp.setDepartment(employeeDetails.getDepartment());
                    return ResponseEntity.ok(employeeService.save(emp));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.findById(id).isPresent()) {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Dynamic search with pagination & sorting (Exercise 6)
    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployeesByName(
            @RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(employeeService.findByNameContaining(name, pageable));
    }

    // Custom query - HQL (Exercise 5)
    @GetMapping("/search/department")
    public ResponseEntity<List<Employee>> getEmployeesByDeptName(@RequestParam String deptName) {
        return ResponseEntity.ok(employeeService.findByDepartmentName(deptName));
    }

    // Named query - Email (Exercise 5)
    @GetMapping("/search/email-named")
    public ResponseEntity<Employee> getEmployeeByEmailNamed(@RequestParam String email) {
        Employee employee = employeeService.findByEmailNamed(email);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    // Named query - Min Salary (Exercise 5)
    @GetMapping("/search/salary-named")
    public ResponseEntity<List<Employee>> getEmployeesByMinSalaryNamed(@RequestParam double salary) {
        return ResponseEntity.ok(employeeService.findByMinSalaryNamed(salary));
    }

    // Interface-based Projection (Exercise 8)
    @GetMapping("/projections/interface")
    public ResponseEntity<List<EmployeeProjection>> getInterfaceProjections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(employeeService.findProjectedBy(PageRequest.of(page, size)));
    }

    // Class-based Projection / DTO (Exercise 8)
    @GetMapping("/projections/dto")
    public ResponseEntity<List<EmployeeDto>> getDtoProjections() {
        return ResponseEntity.ok(employeeService.findAllEmployeeDtos());
    }
}
