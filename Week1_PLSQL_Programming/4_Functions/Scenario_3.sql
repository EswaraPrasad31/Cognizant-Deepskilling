CREATE OR REPLACE FUNCTION HasSufficientBalance
(
    p_accountId IN NUMBER,
    p_amount    IN NUMBER
)
RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_accountId;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

SELECT HasSufficientBalance(1,500);