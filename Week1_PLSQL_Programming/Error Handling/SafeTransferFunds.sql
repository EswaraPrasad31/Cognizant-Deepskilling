DELIMITER $$

CREATE PROCEDURE SafeTransferFunds(
    IN sourceAccountId INT,
    IN targetAccountId INT,
    IN transferAmount DECIMAL(12,2)
)
BEGIN

    DECLARE sourceBalance DECIMAL(12,2);

    START TRANSACTION;

    SELECT Balance
    INTO sourceBalance
    FROM Accounts
    WHERE AccountID = sourceAccountId;

    IF sourceBalance < transferAmount THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Insufficient Funds';

    ELSE

        UPDATE Accounts
        SET Balance = Balance - transferAmount
        WHERE AccountID = sourceAccountId;

        UPDATE Accounts
        SET Balance = Balance + transferAmount
        WHERE AccountID = targetAccountId;

        COMMIT;

    END IF;

END$$

DELIMITER ;