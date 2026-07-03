
BEGIN

    FOR loan_rec IN
    (
        SELECT LoanID,
               CustomerID,
               EndDate
        FROM Loans
        WHERE EndDate BETWEEN SYSDATE
                          AND SYSDATE + 30
    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Customer ID '
            || loan_rec.CustomerID
            || ' - Loan '
            || loan_rec.LoanID
            || ' is due on '
            || TO_CHAR(loan_rec.EndDate,'DD-MON-YYYY')
        );

    END LOOP;

END;
/

SELECT *
FROM Loans;