DELIMITER $$

CREATE PROCEDURE ApplyAnnualFee()
BEGIN

    DECLARE done INT DEFAULT FALSE;

    DECLARE accountIdVar INT;

    DECLARE accountCursor CURSOR FOR

        SELECT AccountID
        FROM Accounts;

    DECLARE CONTINUE HANDLER
    FOR NOT FOUND
    SET done = TRUE;

    OPEN accountCursor;

    accountLoop: LOOP

        FETCH accountCursor
        INTO accountIdVar;

        IF done THEN
            LEAVE accountLoop;
        END IF;

        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = accountIdVar;

    END LOOP;

    CLOSE accountCursor;

END$$

DELIMITER ;

SHOW PROCEDURE STATUS
WHERE Db = 'bank_management';