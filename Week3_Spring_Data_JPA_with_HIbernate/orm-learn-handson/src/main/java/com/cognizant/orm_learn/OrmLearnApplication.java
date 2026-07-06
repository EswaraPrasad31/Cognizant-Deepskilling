package com.cognizant.orm_learn;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.service.CountryService;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import com.cognizant.orm_learn.service.StockService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static StockService stockService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        // Get services from context
        countryService = context.getBean(CountryService.class);
        stockService = context.getBean(StockService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        LOGGER.info("Inside main - Starting queries and relationship tests");

        // --- PART 1: Country Query Methods ---
        testCountryQueryMethods();

        // --- PART 2: Stock Query Methods ---
        testStockQueryMethods();

        // --- PARTS 4-6: Employee, Department & Skill Relationship Mapping ---
        testEmployeeDepartmentRelationship();
        testDepartmentEmployeeOneToMany();
        testEmployeeSkillManyToMany();
    }

    private static void testCountryQueryMethods() {
        LOGGER.info("============ Country Queries ============");

        // Substring search (e.g. for "ou")
        LOGGER.info("--- Countries matching 'ou' ---");
        List<Country> countriesMatchingOu = countryService.findCountryByNameContaining("ou");
        countriesMatchingOu.forEach(c -> LOGGER.info("{} - {}", c.getCode(), c.getName()));

        // Sorted substring search (e.g. for "ou")
        LOGGER.info("--- Countries matching 'ou' ordered by name ---");
        List<Country> countriesMatchingOuSorted = countryService.findCountryByNameContainingOrderByNameAsc("ou");
        countriesMatchingOuSorted.forEach(c -> LOGGER.info("{} - {}", c.getCode(), c.getName()));

        // Alphabet prefix search (e.g. starting with "Z")
        LOGGER.info("--- Countries starting with 'Z' ---");
        List<Country> countriesStartingWithZ = countryService.findCountryByNameStartingWithOrderByNameAsc("Z");
        countriesStartingWithZ.forEach(c -> LOGGER.info("{} - {}", c.getCode(), c.getName()));
    }

    private static void testStockQueryMethods() {
        LOGGER.info("============ Stock Queries ============");

        // September 2019 Facebook stock records
        LOGGER.info("--- Facebook Stocks Sept 2019 ---");
        List<Stock> fbStocks = stockService.getFacebookStocksSeptember2019();
        fbStocks.forEach(s -> LOGGER.info("{} | {} | {} | {} | {}",
                s.getCode(), s.getDate(), s.getOpen(), s.getClose(), s.getVolume()));

        // Google stocks > 1250
        LOGGER.info("--- Google Stocks Close > 1250 ---");
        List<Stock> googleStocks = stockService.getGoogleStocksGreaterThan1250();
        googleStocks.forEach(s -> LOGGER.info("{} | {} | {} | {} | {}",
                s.getCode(), s.getDate(), s.getOpen(), s.getClose(), s.getVolume()));

        // Top 3 highest volume transactions
        LOGGER.info("--- Top 3 Highest Volume Stocks ---");
        List<Stock> highVolumeStocks = stockService.getTop3HighestVolumeStocks();
        highVolumeStocks.forEach(s -> LOGGER.info("{} | {} | {} | {} | {}",
                s.getCode(), s.getDate(), s.getOpen(), s.getClose(), s.getVolume()));

        // Three lowest Netflix stocks
        LOGGER.info("--- Top 3 Lowest Close Netflix Stocks ---");
        List<Stock> lowNetflixStocks = stockService.getTop3LowestNetflixStocks();
        lowNetflixStocks.forEach(s -> LOGGER.info("{} | {} | {} | {} | {}",
                s.getCode(), s.getDate(), s.getOpen(), s.getClose(), s.getVolume()));
    }

    private static void testEmployeeDepartmentRelationship() {
        LOGGER.info("============ Employee & Department (Many to One) Mappings ============");

        // 1. Fetching employee details along with department
        LOGGER.info("--- Fetching Employee 1 Details ---");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());

        // 2. Add a new employee associated with Department 1 (IT)
        LOGGER.info("--- Creating and Adding New Employee ---");
        Employee newEmp = new Employee();
        newEmp.setName("Sarah Jones");
        newEmp.setSalary(48000.0);
        newEmp.setPermanent(true);
        try {
            newEmp.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2000-05-18"));
        } catch (ParseException e) {
            LOGGER.error("Date format error: ", e);
        }
        Department dept1 = departmentService.get(1);
        newEmp.setDepartment(dept1);
        employeeService.save(newEmp);
        LOGGER.debug("New Employee added with generated ID: {}", newEmp);

        // 3. Update employee department association
        LOGGER.info("--- Updating Employee 4 Department to HR (ID 2) ---");
        Employee empToUpdate = employeeService.get(4);
        Department dept2 = departmentService.get(2);
        empToUpdate.setDepartment(dept2);
        employeeService.save(empToUpdate);
        LOGGER.debug("Employee updated: {}", employeeService.get(4));
    }

    private static void testDepartmentEmployeeOneToMany() {
        LOGGER.info("============ Department to Employees (One to Many) Mappings ============");

        // Fetching department and checking matching employees
        LOGGER.info("--- Fetching Department 1 (IT) and its Employee List ---");
        Department itDept = departmentService.get(1);
        LOGGER.debug("Department: {}", itDept);
        LOGGER.debug("Employees under IT: {}", itDept.getEmployeeList());
    }

    private static void testEmployeeSkillManyToMany() {
        LOGGER.info("============ Employee & Skill (Many to Many) Mappings ============");

        // 1. Fetch employee skills eagerly
        LOGGER.info("--- Checking skills of Employee 1 ---");
        Employee emp = employeeService.get(1);
        LOGGER.debug("Employee: {}", emp.getName());
        LOGGER.debug("Skills: {}", emp.getSkillList());

        // 2. Associate a new skill with an employee
        LOGGER.info("--- Adding Skill 4 (React) to Employee 3 ---");
        Employee emp3 = employeeService.get(3);
        Skill skill4 = skillService.get(4);

        Set<Skill> skillList = emp3.getSkillList();
        skillList.add(skill4);
        employeeService.save(emp3);

        // Verify updated skills list
        LOGGER.debug("Updated Skills of Employee 3: {}", employeeService.get(3).getSkillList());
    }
}
