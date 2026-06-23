DELIMITER $$

CREATE PROCEDURE OpenAccount(
    IN accountIdParam INT,
    IN customerIdParam INT,
    IN accountTypeParam VARCHAR(20),
    IN initialBalance DECIMAL(12,2)
)
BEGIN

    INSERT INTO Accounts(
        AccountID,
        CustomerID,
        AccountType,
        Balance,
        LastModified
    )
    VALUES(
        accountIdParam,
        customerIdParam,
        accountTypeParam,
        initialBalance,
        NOW()
    );

END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE CloseAccount(
    IN accountIdParam INT
)
BEGIN

    DELETE FROM Accounts
    WHERE AccountID = accountIdParam;

END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION GetTotalCustomerBalance(
    customerIdParam INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC

BEGIN

    DECLARE totalBalance DECIMAL(12,2);

    SELECT SUM(Balance)
    INTO totalBalance
    FROM Accounts
    WHERE CustomerID = customerIdParam;

    RETURN IFNULL(totalBalance, 0);

END$$

DELIMITER ;

