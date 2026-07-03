DELIMITER $$

CREATE PROCEDURE UpdateSalary(
    IN employeeIdParam INT,
    IN percentageIncrease DECIMAL(5,2)
)
BEGIN

    DECLARE employeeCount INT;

    SELECT COUNT(*)
    INTO employeeCount
    FROM Employees
    WHERE EmployeeID = employeeIdParam;

    IF employeeCount = 0 THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Employee ID does not exist';

    ELSE

        UPDATE Employees
        SET Salary =
            Salary +
            (Salary * percentageIncrease / 100)
        WHERE EmployeeID = employeeIdParam;

    END IF;

END$$

DELIMITER ;