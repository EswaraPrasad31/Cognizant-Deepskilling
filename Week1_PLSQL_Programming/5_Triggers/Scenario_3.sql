CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT
ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Deposit'
       AND :NEW.Amount <= 0 THEN

        RAISE_APPLICATION_ERROR(
            -20001,
            'Deposit amount must be greater than zero'
        );

    END IF;

    IF :NEW.TransactionType = 'Withdrawal'
       AND :NEW.Amount > v_balance THEN

        RAISE_APPLICATION_ERROR(
            -20002,
            'Insufficient balance for withdrawal'
        );

    END IF;

END;
/

INSERT INTO Transactions
VALUES
(
    10,
    1,
    NOW(),
    500,
    'Deposit'
);

INSERT INTO Transactions
VALUES
(
    11,
    1,
    NOW(),
    -500,
    'Deposit'
);