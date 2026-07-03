use bank_management;

SELECT CONCAT(
    'Reminder: Loan ',
    LoanID,
    ' is due on ',
    EndDate
) AS ReminderMessage
FROM Loans
WHERE EndDate BETWEEN CURDATE()
AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);

SELECT * FROM Customers;

SELECT * FROM Loans;