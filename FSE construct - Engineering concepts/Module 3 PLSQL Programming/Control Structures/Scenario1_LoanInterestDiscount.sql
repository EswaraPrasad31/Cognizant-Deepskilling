UPDATE Loans l
JOIN Customers c
ON l.CustomerID = c.CustomerID
SET l.InterestRate = l.InterestRate - 1
WHERE TIMESTAMPDIFF(YEAR, c.DOB, CURDATE()) > 60;