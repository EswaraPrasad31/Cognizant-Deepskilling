DELIMITER $$

CREATE PROCEDURE HireEmployee(
    IN employeeIdParam INT,
    IN employeeNameParam VARCHAR(100),
    IN employeePositionParam VARCHAR(50),
    IN employeeSalaryParam DECIMAL(12,2),
    IN departmentParam VARCHAR(50),
    IN hireDateParam DATE
)
BEGIN

    INSERT INTO Employees(
        EmployeeID,
        Name,
        Position,
        Salary,
        Department,
        HireDate
    )
    VALUES(
        employeeIdParam,
        employeeNameParam,
        employeePositionParam,
        employeeSalaryParam,
        departmentParam,
        hireDateParam
    );

END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE UpdateEmployee(
    IN employeeIdParam INT,
    IN newSalary DECIMAL(12,2)
)
BEGIN

    UPDATE Employees
    SET Salary = newSalary
    WHERE EmployeeID = employeeIdParam;

END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION CalculateAnnualSalary(
    employeeIdParam INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC

BEGIN

    DECLARE monthlySalary DECIMAL(12,2);

    SELECT Salary
    INTO monthlySalary
    FROM Employees
    WHERE EmployeeID = employeeIdParam;

    RETURN monthlySalary * 12;

END$$

DELIMITER ;