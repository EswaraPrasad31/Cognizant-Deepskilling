DELIMITER $$

CREATE PROCEDURE GenerateMonthlyStatements()
BEGIN

    DECLARE done INT DEFAULT FALSE;

    DECLARE transactionIdVar INT;
    DECLARE accountIdVar INT;
    DECLARE amountVar DECIMAL(12,2);

    DECLARE transactionCursor CURSOR FOR

        SELECT TransactionID,
               AccountID,
               Amount
        FROM Transactions
        WHERE MONTH(TransactionDate) =
              MONTH(CURDATE())
        AND YEAR(TransactionDate) =
            YEAR(CURDATE());

    DECLARE CONTINUE HANDLER
    FOR NOT FOUND
    SET done = TRUE;

    OPEN transactionCursor;

    transactionLoop: LOOP

        FETCH transactionCursor
        INTO transactionIdVar,
             accountIdVar,
             amountVar;

        IF done THEN
            LEAVE transactionLoop;
        END IF;

        SELECT CONCAT(
            'Transaction ID: ',
            transactionIdVar,
            ' Account ID: ',
            accountIdVar,
            ' Amount: ',
            amountVar
        ) AS MonthlyStatement;

    END LOOP;

    CLOSE transactionCursor;

END$$

DELIMITER ;