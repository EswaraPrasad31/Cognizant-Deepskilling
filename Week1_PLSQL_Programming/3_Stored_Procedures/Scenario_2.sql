CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus
(
    p_department IN VARCHAR2,
    p_bonus      IN NUMBER
)
IS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus / 100)
    WHERE Department = p_department;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Bonus Updated Successfully.');

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(
            'Error : ' || SQLERRM
        );

END;
/

BEGIN

    UpdateEmployeeBonus(
        'IT',
        15
    );

END;
/

SELECT EmployeeID,
       Name,
       Department,
       Salary
FROM Employees;