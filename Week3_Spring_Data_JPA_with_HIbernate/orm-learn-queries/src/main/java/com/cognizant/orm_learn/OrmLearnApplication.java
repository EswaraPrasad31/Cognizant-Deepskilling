package com.cognizant.orm_learn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.model.AttemptOption;
import com.cognizant.orm_learn.model.AttemptQuestion;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Options;
import com.cognizant.orm_learn.model.Question;
import com.cognizant.orm_learn.service.AttemptService;
import com.cognizant.orm_learn.service.EmployeeService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static EmployeeService employeeService;
    private static AttemptService attemptService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        employeeService = context.getBean(EmployeeService.class);
        attemptService = context.getBean(AttemptService.class);

        LOGGER.info("Inside main - Running HQL, Native, and Criteria Query Tests");

        // Hands-on 2: Get all permanent employees using HQL (Optimized fetch join query)
        testGetAllPermanentEmployees();

        // Hands-on 3: Fetch quiz attempt details using HQL
        testGetAttemptDetail();

        // Hands-on 4: Get average salary using HQL
        testGetAverageSalary();

        // Hands-on 5: Get all employees using Native Query
        testGetAllEmployeesNative();

        // Hands-on 6: Criteria Query Demo
        testCriteriaQuery();
    }

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("============ Permanent Employees (HQL Fetch Join) ============");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        for (Employee e : employees) {
            LOGGER.debug("Employee: {} | Department: {} | Salary: {}", e.getName(), e.getDepartment().getName(), e.getSalary());
            LOGGER.debug("Skills: {}", e.getSkillList());
        }
    }

    private static void testGetAttemptDetail() {
        LOGGER.info("============ Quiz Attempt Details (HQL Fetch Join) ============");
        // Fetch details of attempt 1 by user 1 (John)
        Attempt attempt = attemptService.getAttemptDetail(1, 1);
        if (attempt != null) {
            LOGGER.info("User: {}", attempt.getUser().getName());
            LOGGER.info("Attempt Date: {}", attempt.getDate());
            LOGGER.info("Attempt ID: {}", attempt.getId());
            
            java.util.Set<AttemptQuestion> attemptQuestions = attempt.getAttemptQuestions();
            for (AttemptQuestion aq : attemptQuestions) {
                Question question = aq.getQuestion();
                System.out.println("\n" + question.getText());
                
                java.util.Set<Options> optionsList = question.getOptionsList();
                int index = 1;
                for (Options option : optionsList) {
                    boolean isSelected = false;
                    if (aq.getAttemptOptions() != null) {
                        for (AttemptOption ao : aq.getAttemptOptions()) {
                            if (ao.getOptions().getId() == option.getId() && ao.isSelected()) {
                                isSelected = true;
                                break;
                            }
                        }
                    }
                    System.out.printf(" %d) %-12s \t%-3s \t%b\n", index++, option.getText(), option.getScore(), isSelected);
                }
            }
            System.out.println();
        } else {
            LOGGER.error("Attempt not found for user 1, attempt 1");
        }

    }

    private static void testGetAverageSalary() {
        LOGGER.info("============ Average Salary by Department (HQL Parameter Binding) ============");
        // Get average salary for IT department (ID 1)
        double avgSalaryIt = employeeService.getAverageSalary(1);
        LOGGER.debug("Average Salary for IT Department (ID 1): {}", avgSalaryIt);

        // Get average salary for HR department (ID 2)
        double avgSalaryHr = employeeService.getAverageSalary(2);
        LOGGER.debug("Average Salary for HR Department (ID 2): {}", avgSalaryHr);
    }

    private static void testGetAllEmployeesNative() {
        LOGGER.info("============ Native SQL Query (All Employees) ============");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        for (Employee e : employees) {
            LOGGER.debug("Employee (Native): {} | Salary: {}", e.getName(), e.getSalary());
        }
    }

    private static void testCriteriaQuery() {
        LOGGER.info("============ Dynamic Criteria Query (Amazon Filter Demo) ============");
        // Example: Search permanent employees with salary >= 55000
        LOGGER.info("--- Filters: Permanent=true, MinSalary=55000.0 ---");
        List<Employee> list1 = employeeService.findEmployeesByCriteria(55000.0, true);
        list1.forEach(e -> LOGGER.debug("Matched: {} | Salary: {}", e.getName(), e.getSalary()));

        // Example: Search only by MinSalary = 60000.0 (no permanent filter)
        LOGGER.info("--- Filters: MinSalary=60000.0 ---");
        List<Employee> list2 = employeeService.findEmployeesByCriteria(60000.0, null);
        list2.forEach(e -> LOGGER.debug("Matched: {} | Salary: {}", e.getName(), e.getSalary()));
    }
}
