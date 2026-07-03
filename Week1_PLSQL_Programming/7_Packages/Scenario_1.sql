CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

----------------------------------------------------------
-- Procedure : AddCustomer
----------------------------------------------------------

PROCEDURE AddCustomer
(
    p_customerId NUMBER,
    p_name       VARCHAR2,
    p_dob        DATE,
    p_balance    NUMBER
)
IS
BEGIN

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

    WHEN DUP_VAL_ON_INDEX THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE('Customer ID already exists.');

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END AddCustomer;

----------------------------------------------------------
-- Procedure : UpdateCustomer
----------------------------------------------------------

PROCEDURE UpdateCustomer
(
    p_customerId NUMBER,
    p_name       VARCHAR2,
    p_balance    NUMBER
)
IS
BEGIN

    UPDATE Customers
    SET Name = p_name,
        Balance = p_balance,
        LastModified = SYSDATE
    WHERE CustomerID = p_customerId;

    IF SQL%ROWCOUNT = 0 THEN

        DBMS_OUTPUT.PUT_LINE('Customer Not Found.');

    ELSE

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Customer Updated Successfully.');

    END IF;

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END UpdateCustomer;

----------------------------------------------------------
-- Function : GetCustomerBalance
----------------------------------------------------------

FUNCTION GetCustomerBalance
(
    p_customerId NUMBER
)
RETURN NUMBER
IS

    v_balance NUMBER;

BEGIN

    SELECT Balance
    INTO v_balance
    FROM Customers
    WHERE CustomerID = p_customerId;

    RETURN v_balance;

EXCEPTION

    WHEN NO_DATA_FOUND THEN

        DBMS_OUTPUT.PUT_LINE('Customer Not Found.');

        RETURN NULL;

END GetCustomerBalance;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

----------------------------------------------------------
-- Procedure : AddCustomer
----------------------------------------------------------

PROCEDURE AddCustomer
(
    p_customerId NUMBER,
    p_name       VARCHAR2,
    p_dob        DATE,
    p_balance    NUMBER
)
IS
BEGIN

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

    WHEN DUP_VAL_ON_INDEX THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE('Customer ID already exists.');

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END AddCustomer;

----------------------------------------------------------
-- Procedure : UpdateCustomer
----------------------------------------------------------

PROCEDURE UpdateCustomer
(
    p_customerId NUMBER,
    p_name       VARCHAR2,
    p_balance    NUMBER
)
IS
BEGIN

    UPDATE Customers
    SET Name = p_name,
        Balance = p_balance,
        LastModified = SYSDATE
    WHERE CustomerID = p_customerId;

    IF SQL%ROWCOUNT = 0 THEN

        DBMS_OUTPUT.PUT_LINE('Customer Not Found.');

    ELSE

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Customer Updated Successfully.');

    END IF;

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END UpdateCustomer;

----------------------------------------------------------
-- Function : GetCustomerBalance
----------------------------------------------------------

FUNCTION GetCustomerBalance
(
    p_customerId NUMBER
)
RETURN NUMBER
IS

    v_balance NUMBER;

BEGIN

    SELECT Balance
    INTO v_balance
    FROM Customers
    WHERE CustomerID = p_customerId;

    RETURN v_balance;

EXCEPTION

    WHEN NO_DATA_FOUND THEN

        DBMS_OUTPUT.PUT_LINE('Customer Not Found.');

        RETURN NULL;

END GetCustomerBalance;

END CustomerManagement;
/