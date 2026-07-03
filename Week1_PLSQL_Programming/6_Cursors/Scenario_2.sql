CREATE OR REPLACE PROCEDURE ApplyAnnualFee
IS

CURSOR accountCursor IS
SELECT AccountID
FROM Accounts;

v_accountId Accounts.AccountID%TYPE;

BEGIN

OPEN accountCursor;

LOOP

FETCH accountCursor
INTO v_accountId;

EXIT WHEN accountCursor%NOTFOUND;

UPDATE Accounts
SET Balance = Balance - 100
WHERE AccountID = v_accountId;

END LOOP;

COMMIT;

CLOSE accountCursor;

DBMS_OUTPUT.PUT_LINE('Annual fee applied successfully.');

EXCEPTION

WHEN OTHERS THEN

ROLLBACK;

DBMS_OUTPUT.PUT_LINE('Error : '||SQLERRM);

END;
/

CALL ApplyAnnualFee();

SELECT AccountID,
       Balance
FROM Accounts;