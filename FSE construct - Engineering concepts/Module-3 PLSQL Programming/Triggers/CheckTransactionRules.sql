DELIMITER $$

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT
ON Transactions
FOR EACH ROW

BEGIN

    DECLARE currentBalance DECIMAL(12,2);

    SELECT Balance
    INTO currentBalance
    FROM Accounts
    WHERE AccountID = NEW.AccountID;

    IF NEW.TransactionType = 'Deposit'
       AND NEW.Amount <= 0 THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Deposit amount must be greater than zero';

    END IF;

    IF NEW.TransactionType = 'Withdrawal'
       AND NEW.Amount > currentBalance THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Insufficient balance for withdrawal';

    END IF;

END$$

DELIMITER ;

SHOW TRIGGERS;