CREATE OR REPLACE PROCEDURE UpdateSalary
(
    p_employeeId IN NUMBER,
    p_percentage IN NUMBER
)
IS
    v_salary NUMBER;
BEGIN

    SELECT Salary
    INTO v_salary
    FROM Employees
    WHERE EmployeeID = p_employeeId;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employeeId;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully.');

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Employee ID does not exist.');

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);

END;
/

BEGIN
    UpdateSalary(1,10);
END;
/

SELECT EmployeeID,
       Name,
       Salary
FROM Employees;