CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment
(
    p_loanAmount NUMBER,
    p_interestRate NUMBER,
    p_durationYears NUMBER
)
RETURN NUMBER
IS
    v_totalAmount NUMBER;
    v_months NUMBER;
BEGIN

    v_totalAmount :=
        p_loanAmount *
        (1 + p_interestRate/100);

    v_months :=
        p_durationYears * 12;

    RETURN ROUND(
        v_totalAmount /
        v_months,
        2
    );

END;
/

SELECT CalculateMonthlyInstallment(
       120000,
       12,
       5)
FROM dual;