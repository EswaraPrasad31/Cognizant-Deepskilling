DELIMITER $$

CREATE PROCEDURE UpdateEmployeeBonus(
    IN departmentParam VARCHAR(50),
    IN bonusPercentage DECIMAL(5,2)
)
BEGIN

    UPDATE Employees
    SET Salary =
        Salary +
        (Salary * bonusPercentage / 100)
    WHERE Department = departmentParam;

END$$

DELIMITER ;