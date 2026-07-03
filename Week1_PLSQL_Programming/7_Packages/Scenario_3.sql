CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount
    (
        p_accountId NUMBER,
        p_customerId NUMBER,
        p_accountType VARCHAR2,
        p_initialBalance NUMBER
    );

    PROCEDURE CloseAccount
    (
        p_accountId NUMBER
    );

    FUNCTION GetTotalCustomerBalance
    (
        p_customerId NUMBER
    )
    RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

------------------------------------------------------------
-- Open Account
------------------------------------------------------------

PROCEDURE OpenAccount
(
    p_accountId NUMBER,
    p_customerId NUMBER,
    p_accountType VARCHAR2,
    p_initialBalance NUMBER
)
IS
BEGIN

    INSERT INTO Accounts
    (
        AccountID,
        CustomerID,
        AccountType,
        Balance,
        LastModified
    )
    VALUES
    (
        p_accountId,
        p_customerId,
        p_accountType,
        p_initialBalance,
        SYSDATE
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Account Opened Successfully.');

EXCEPTION

    WHEN DUP_VAL_ON_INDEX THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE('Account ID already exists.');

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END OpenAccount;

------------------------------------------------------------
-- Close Account
------------------------------------------------------------

PROCEDURE CloseAccount
(
    p_accountId NUMBER
)
IS
BEGIN

    DELETE FROM Accounts
    WHERE AccountID = p_accountId;

    IF SQL%ROWCOUNT = 0 THEN

        DBMS_OUTPUT.PUT_LINE('Account Not Found.');

    ELSE

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Account Closed Successfully.');

    END IF;

EXCEPTION

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END CloseAccount;

------------------------------------------------------------
-- Get Total Customer Balance
------------------------------------------------------------

FUNCTION GetTotalCustomerBalance
(
    p_customerId NUMBER
)
RETURN NUMBER
IS

    v_totalBalance NUMBER;

BEGIN

    SELECT NVL(SUM(Balance),0)
    INTO v_totalBalance
    FROM Accounts
    WHERE CustomerID = p_customerId;

    RETURN v_totalBalance;

EXCEPTION

    WHEN OTHERS THEN

        RETURN 0;

END GetTotalCustomerBalance;

END AccountOperations;
/