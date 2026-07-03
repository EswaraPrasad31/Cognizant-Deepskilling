CREATE OR REPLACE PROCEDURE UpdateLoanInterestRates
IS

CURSOR loanCursor IS
SELECT LoanID
FROM Loans;

v_loanId Loans.LoanID%TYPE;

BEGIN

OPEN loanCursor;

LOOP

FETCH loanCursor
INTO v_loanId;

EXIT WHEN loanCursor%NOTFOUND;

UPDATE Loans
SET InterestRate = InterestRate + 0.5
WHERE LoanID = v_loanId;

END LOOP;

COMMIT;

CLOSE loanCursor;

DBMS_OUTPUT.PUT_LINE('Loan Interest Updated Successfully.');

EXCEPTION

WHEN OTHERS THEN

ROLLBACK;

DBMS_OUTPUT.PUT_LINE('Error : '||SQLERRM);

END;
/

CALL UpdateLoanInterestRates();

SELECT LoanID,
       InterestRate
FROM Loans;