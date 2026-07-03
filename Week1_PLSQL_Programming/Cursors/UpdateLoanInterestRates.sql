DELIMITER $$

CREATE PROCEDURE UpdateLoanInterestRates()
BEGIN

    DECLARE done INT DEFAULT FALSE;

    DECLARE loanIdVar INT;

    DECLARE loanCursor CURSOR FOR

        SELECT LoanID
        FROM Loans;

    DECLARE CONTINUE HANDLER
    FOR NOT FOUND
    SET done = TRUE;

    OPEN loanCursor;

    loanLoop: LOOP

        FETCH loanCursor
        INTO loanIdVar;

        IF done THEN
            LEAVE loanLoop;
        END IF;

        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = loanIdVar;

    END LOOP;

    CLOSE loanCursor;

END$$

DELIMITER ;