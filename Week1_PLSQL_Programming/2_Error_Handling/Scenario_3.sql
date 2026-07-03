CREATE OR REPLACE PROCEDURE AddNewCustomer
(
    p_customerId   IN NUMBER,
    p_name         IN VARCHAR2,
    p_dob          IN DATE,
    p_balance      IN NUMBER
)
IS
    v_count NUMBER;
BEGIN

    SELECT COUNT(*)
    INTO v_count
    FROM Customers
    WHERE CustomerID = p_customerId;

    IF v_count > 0 THEN

        RAISE_APPLICATION_ERROR(
            -20002,
            'Customer ID already exists.'
        );

    END IF;

    INSERT INTO Customers
    (
        CustomerID,
        Name,
        DOB,
        Balance,
        LastModified
    )
    VALUES
    (
        p_customerId,
        p_name,
        p_dob,
        p_balance,
        SYSDATE
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer Added Successfully.');

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(
            'Error : ' || SQLERRM
        );

END;
/

BEGIN

    AddNewCustomer
    (
        5,
        'David Miller',
        DATE '1992-08-15',
        12000
    );

END;
/

SELECT *
FROM Customers;