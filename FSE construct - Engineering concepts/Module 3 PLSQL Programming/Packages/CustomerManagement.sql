DELIMITER $$

CREATE PROCEDURE AddCustomer(
    IN customerIdParam INT,
    IN customerNameParam VARCHAR(100),
    IN customerDobParam DATE,
    IN customerBalanceParam DECIMAL(12,2)
)
BEGIN

    INSERT INTO Customers(
        CustomerID,
        Name,
        DOB,
        Balance,
        LastModified
    )
    VALUES(
        customerIdParam,
        customerNameParam,
        customerDobParam,
        customerBalanceParam,
        NOW()
    );

END$$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE UpdateCustomer(
    IN customerIdParam INT,
    IN customerNameParam VARCHAR(100),
    IN customerBalanceParam DECIMAL(12,2)
)
BEGIN

    UPDATE Customers
    SET Name = customerNameParam,
        Balance = customerBalanceParam
    WHERE CustomerID = customerIdParam;

END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION GetCustomerBalance(
    customerIdParam INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC

BEGIN

    DECLARE customerBalance DECIMAL(12,2);

    SELECT Balance
    INTO customerBalance
    FROM Customers
    WHERE CustomerID = customerIdParam;

    RETURN customerBalance;

END$$

DELIMITER ;