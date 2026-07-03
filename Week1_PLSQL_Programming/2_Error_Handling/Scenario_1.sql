CREATE OR REPLACE PROCEDURE SafeTransferFunds
(
    p_sourceAccountId IN NUMBER,
    p_targetAccountId IN NUMBER,
    p_transferAmount  IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_sourceAccountId;

    IF v_balance < p_transferAmount THEN

        RAISE_APPLICATION_ERROR(
            -20001,
            'Insufficient Funds'
        );

    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_transferAmount
    WHERE AccountID = p_sourceAccountId;

    UPDATE Accounts
    SET Balance = Balance + p_transferAmount
    WHERE AccountID = p_targetAccountId;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer Successful.');

EXCEPTION

    WHEN NO_DATA_FOUND THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(
            'Account not found.'
        );

    WHEN OTHERS THEN

        ROLLBACK;

        DBMS_OUTPUT.PUT_LINE(
            'Error : ' || SQLERRM
        );

END;
/

BEGIN
    SafeTransferFunds(
        1,
        2,
        500
    );
END;
/

SELECT *
FROM Accounts;