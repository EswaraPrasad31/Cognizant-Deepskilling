USE bank_management;

INSERT INTO Customers
VALUES (
    1,
    'John Doe',
    '1985-05-15',
    1000,
    NOW()
);

INSERT INTO Customers
VALUES (
    2,
    'Jane Smith',
    '1990-07-20',
    1500,
    NOW()
);

INSERT INTO Accounts
VALUES (
    1,
    1,
    'Savings',
    1000,
    NOW()
);

INSERT INTO Accounts
VALUES (
    2,
    2,
    'Checking',
    1500,
    NOW()
);

INSERT INTO Transactions
VALUES (
    1,
    1,
    NOW(),
    200,
    'Deposit'
);

INSERT INTO Transactions
VALUES (
    2,
    2,
    NOW(),
    300,
    'Withdrawal'
);

INSERT INTO Loans
VALUES (
    1,
    1,
    5000,
    5,
    CURDATE(),
    DATE_ADD(CURDATE(), INTERVAL 60 MONTH)
);

INSERT INTO Employees
VALUES (
    1,
    'Alice Johnson',
    'Manager',
    70000,
    'HR',
    '2015-06-15'
);

INSERT INTO Employees
VALUES (
    2,
    'Bob Brown',
    'Developer',
    60000,
    'IT',
    '2017-03-20'
);