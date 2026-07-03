CREATE OR REPLACE PROCEDURE TransferFunds
(
    p_sourceAccount IN NUMBER,
    p_targetAccount IN NUMBER,
    p_amount        IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_sourceAccount;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(
            -20001,
            'Insufficient Balance'
        );
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_sourceAccount;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_targetAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Funds Transferred Successfully.');

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Account not found.');

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);

END;
/

CALL TransferFunds(1,2,500);    

SELECT *
FROM Accounts;