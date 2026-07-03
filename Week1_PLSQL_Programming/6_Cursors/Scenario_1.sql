CREATE OR REPLACE PROCEDURE GenerateMonthlyStatements
IS

CURSOR transactionCursor IS

SELECT
c.Name,
t.TransactionID,
t.Amount,
t.TransactionType,
t.TransactionDate
FROM Customers c
JOIN Accounts a
ON c.CustomerID=a.CustomerID
JOIN Transactions t
ON a.AccountID=t.AccountID
WHERE EXTRACT(MONTH FROM t.TransactionDate)=EXTRACT(MONTH FROM SYSDATE)
AND EXTRACT(YEAR FROM t.TransactionDate)=EXTRACT(YEAR FROM SYSDATE);

v_name Customers.Name%TYPE;
v_transactionId Transactions.TransactionID%TYPE;
v_amount Transactions.Amount%TYPE;
v_type Transactions.TransactionType%TYPE;
v_date Transactions.TransactionDate%TYPE;

BEGIN

OPEN transactionCursor;

LOOP

FETCH transactionCursor
INTO
v_name,
v_transactionId,
v_amount,
v_type,
v_date;

EXIT WHEN transactionCursor%NOTFOUND;

DBMS_OUTPUT.PUT_LINE(
'Customer : '||v_name||
' Transaction : '||v_transactionId||
' Amount : '||v_amount||
' Type : '||v_type||
' Date : '||TO_CHAR(v_date,'DD-MON-YYYY')
);

END LOOP;

CLOSE transactionCursor;

END;
/