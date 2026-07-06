package com.cognizant.ems;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.cognizant.ems.dto.EmployeeDto;
import com.cognizant.ems.model.primary.Department;
import com.cognizant.ems.model.primary.Employee;
import com.cognizant.ems.model.secondary.AuditLog;
import com.cognizant.ems.projection.EmployeeProjection;
import com.cognizant.ems.repository.secondary.AuditLogRepository;
import com.cognizant.ems.service.DepartmentService;
import com.cognizant.ems.service.EmployeeBatchService;
import com.cognizant.ems.service.EmployeeService;

// Exclude standard autoconfiguration for DataSource because we have custom multi-datasource configurations (Exercise 9)
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EmployeeManagementSystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagementSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner testRunner(
            EmployeeService employeeService,
            DepartmentService departmentService,
            AuditLogRepository auditLogRepository,
            EmployeeBatchService employeeBatchService) {
        return args -> {
            LOGGER.info("============ INITIALIZING DATABASE TESTING ============");

            // 1. Create and save departments (Primary Datasource)
            Department it = new Department("IT");
            Department hr = new Department("HR");
            departmentService.save(it);
            departmentService.save(hr);
            LOGGER.info("Saved Departments: {}, {}", it, hr);

            // 2. Create and save employees (Primary Datasource)
            Employee emp1 = new Employee("John Doe", "john.doe@ems.com", 65000.0, it);
            Employee emp2 = new Employee("Alice Smith", "alice.smith@ems.com", 72000.0, it);
            Employee emp3 = new Employee("Bob Jones", "bob.jones@ems.com", 48000.0, hr);
            employeeService.save(emp1);
            employeeService.save(emp2);
            employeeService.save(emp3);
            LOGGER.info("Saved Employees: {}, {}, {}", emp1, emp2, emp3);

            // 3. Verify Entity Auditing (Exercise 7)
            LOGGER.info("============ VERIFYING AUDITING PROPERTIES ============");
            LOGGER.info("Employee John Doe CreatedBy: {}", emp1.getCreatedBy());
            LOGGER.info("Employee John Doe CreatedDate: {}", emp1.getCreatedDate());

            // 4. Custom Queries: Named Query & HQL (Exercise 5)
            LOGGER.info("============ RUNNING CUSTOM JPA QUERIES ============");
            List<Employee> itEmployees = employeeService.findByDepartmentName("IT");
            LOGGER.info("Employees in IT department (HQL): {}", itEmployees);

            Employee namedEmailResult = employeeService.findByEmailNamed("alice.smith@ems.com");
            LOGGER.info("Employee found by named query (email): {}", namedEmailResult);

            List<Employee> salaryResult = employeeService.findByMinSalaryNamed(50000.0);
            LOGGER.info("Employees with salary >= 50000 (named query): {}", salaryResult);

            // 5. Pagination & Sorting (Exercise 6)
            LOGGER.info("============ PAGINATION AND SORTING ============");
            // Sort by salary descending, page size 2
            Page<Employee> sortedEmployeePage = employeeService.findAll(
                    PageRequest.of(0, 2, Sort.by("salary").descending()));
            LOGGER.info("Top 2 highest paid employees:");
            sortedEmployeePage.getContent().forEach(e -> LOGGER.info(" - {} (Salary: {})", e.getName(), e.getSalary()));

            // 6. Projections (Exercise 8)
            LOGGER.info("============ TESTING JPA PROJECTIONS ============");
            // Interface-based projection
            List<EmployeeProjection> projectedList = employeeService.findProjectedBy(PageRequest.of(0, 5));
            LOGGER.info("Interface Projections (Name & Email SpEL):");
            projectedList.forEach(p -> LOGGER.info(" - ID: {} | NameAndEmail: {}", p.getId(), p.getNameAndEmail()));

            // Class/DTO-based projection
            List<EmployeeDto> dtoList = employeeService.findAllEmployeeDtos();
            LOGGER.info("DTO Projections (Constructor Expression):");
            dtoList.forEach(dto -> LOGGER.info(" - {}", dto));

            // 7. Secondary Data Source Auditing Verification (Exercise 9)
            LOGGER.info("============ WRITING TO SECONDARY DATASOURCE ============");
            AuditLog actionLog = new AuditLog("EMS_STARTUP", "Application database verification executed successfully");
            auditLogRepository.save(actionLog);
            
            List<AuditLog> allLogs = auditLogRepository.findAll();
            LOGGER.info("Secondary DB Log Table Contents:");
            allLogs.forEach(log -> LOGGER.info(" - Log entry: {}", log));

            // 8. Hibernate Batch Processing (Exercise 10)
            LOGGER.info("============ TESTING BATCH PROCESSING ============");
            List<Employee> batchEmployees = new ArrayList<>();
            for (int i = 1; i <= 60; i++) {
                batchEmployees.add(new Employee("BatchEmp_" + i, "batch.emp" + i + "@ems.com", 50000.0 + (i * 100), it));
            }
            LOGGER.info("Saving 60 employee records in batch (batch size = 50)...");
            long start = System.currentTimeMillis();
            employeeBatchService.saveEmployeesInBatch(batchEmployees);
            long end = System.currentTimeMillis();
            LOGGER.info("Successfully persisted 60 records in batch! Execution time: {} ms", (end - start));
            LOGGER.info("Primary DB Total Employees Count: {}", employeeService.findAll(PageRequest.of(0, 100)).getTotalElements());
        };
    }
}
