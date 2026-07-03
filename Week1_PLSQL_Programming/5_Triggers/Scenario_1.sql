CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE
ON Customers
FOR EACH ROW
BEGIN

    :NEW.LastModified := SYSDATE;

END;
/

UPDATE Customers
SET Balance = 5000
WHERE CustomerID = 1;

SELECT CustomerID,
       Name,
       Balance,
       LastModified
FROM Customers;