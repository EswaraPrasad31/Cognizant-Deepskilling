CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee
    (
        p_employeeId NUMBER,
        p_name       VARCHAR2,
        p_position   VARCHAR2,
        p_salary     NUMBER,
        p_department VARCHAR2,
        p_hireDate   DATE
    );

    PROCEDURE UpdateEmployee
    (
        p_employeeId NUMBER,
        p_salary     NUMBER
    );

    FUNCTION CalculateAnnualSalary
    (
        p_employeeId NUMBER
    )
    RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

---------------------------------------------------------
-- Hire Employee
---------------------------------------------------------

PROCEDURE HireEmployee
(
    p_employeeId NUMBER,
    p_name       VARCHAR2,
    p_position   VARCHAR2,
    p_salary     NUMBER,
    p_department VARCHAR2,
    p_hireDate   DATE
)
IS
BEGIN

    INSERT INTO Employees
    (
        EmployeeID,
        Name,
        Position,
        Salary,
        Department,
        HireDate
    )
    VALUES
    (
        p_employeeId,
        p_name,
        p_position,
        p_salary,
        p_department,
        p_hireDate
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Employee Added Successfully.');

EXCEPTION

    WHEN DUP_VAL_ON_INDEX THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE('Employee ID already exists.');

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END HireEmployee;

---------------------------------------------------------
-- Update Employee Salary
---------------------------------------------------------

PROCEDURE UpdateEmployee
(
    p_employeeId NUMBER,
    p_salary NUMBER
)
IS
BEGIN

    UPDATE Employees
    SET Salary = p_salary
    WHERE EmployeeID = p_employeeId;

    IF SQL%ROWCOUNT = 0 THEN

        DBMS_OUTPUT.PUT_LINE('Employee Not Found.');

    ELSE

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully.');

    END IF;

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END UpdateEmployee;

---------------------------------------------------------
-- Calculate Annual Salary
---------------------------------------------------------

FUNCTION CalculateAnnualSalary
(
    p_employeeId NUMBER
)
RETURN NUMBER
IS

    v_salary NUMBER;

BEGIN

    SELECT Salary
    INTO v_salary
    FROM Employees
    WHERE EmployeeID = p_employeeId;

    RETURN v_salary * 12;

EXCEPTION

    WHEN NO_DATA_FOUND THEN

        DBMS_OUTPUT.PUT_LINE('Employee Not Found.');

        RETURN NULL;

END CalculateAnnualSalary;

END EmployeeManagement;
/