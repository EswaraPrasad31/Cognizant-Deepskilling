DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN sourceAccountId INT,
    IN targetAccountId INT,
    IN transferAmount DECIMAL(12,2)
)
BEGIN

    DECLARE sourceBalance DECIMAL(12,2);

    SELECT Balance
    INTO sourceBalance
    FROM Accounts
    WHERE AccountID = sourceAccountId;

    IF sourceBalance >= transferAmount THEN

        UPDATE Accounts
        SET Balance = Balance - transferAmount
        WHERE AccountID = sourceAccountId;

        UPDATE Accounts
        SET Balance = Balance + transferAmount
        WHERE AccountID = targetAccountId;

    ELSE

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Insufficient Balance';

    END IF;

END$$

DELIMITER ;

SHOW PROCEDURE STATUS
WHERE Db = 'bank_management';