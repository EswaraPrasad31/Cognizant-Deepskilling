DELIMITER $$

CREATE PROCEDURE AddNewCustomer(
    IN customerIdParam INT,
    IN customerNameParam VARCHAR(100),
    IN customerDobParam DATE,
    IN customerBalanceParam DECIMAL(12,2)
)
BEGIN

    DECLARE customerCount INT;

    SELECT COUNT(*)
    INTO customerCount
    FROM Customers
    WHERE CustomerID = customerIdParam;

    IF customerCount > 0 THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Customer ID already exists';

    ELSE

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

    END IF;

END$$

DELIMITER ;

SHOW PROCEDURE STATUS
WHERE Db = 'bank_management';