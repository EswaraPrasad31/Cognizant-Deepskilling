DELIMITER $$

CREATE FUNCTION HasSufficientBalance(
    accountIdParam INT,
    amountParam DECIMAL(12,2)
)
RETURNS BOOLEAN
DETERMINISTIC

BEGIN

    DECLARE accountBalance DECIMAL(12,2);

    SELECT Balance
    INTO accountBalance
    FROM Accounts
    WHERE AccountID = accountIdParam;

    RETURN accountBalance >= amountParam;

END$$

DELIMITER ;

SHOW FUNCTION STATUS
WHERE Db = 'bank_management';