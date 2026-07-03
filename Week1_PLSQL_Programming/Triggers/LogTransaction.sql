CREATE TABLE AuditLog (

    AuditID INT AUTO_INCREMENT PRIMARY KEY,
    TransactionID INT,
    AccountID INT,
    Amount DECIMAL(12,2),
    TransactionType VARCHAR(20),
    LogDate DATETIME

);

DELIMITER $$

CREATE TRIGGER LogTransaction
AFTER INSERT
ON Transactions
FOR EACH ROW

BEGIN

    INSERT INTO AuditLog(
        TransactionID,
        AccountID,
        Amount,
        TransactionType,
        LogDate
    )
    VALUES(
        NEW.TransactionID,
        NEW.AccountID,
        NEW.Amount,
        NEW.TransactionType,
        NOW()
    );

END$$

DELIMITER ;