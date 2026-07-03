DELIMITER $$

CREATE FUNCTION CalculateMonthlyInstallment(
    loanAmount DECIMAL(12,2),
    interestRate DECIMAL(5,2),
    durationYears INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC

BEGIN

    DECLARE totalAmount DECIMAL(12,2);
    DECLARE totalMonths INT;

    SET totalAmount =
        loanAmount +
        (loanAmount * interestRate / 100);

    SET totalMonths =
        durationYears * 12;

    RETURN totalAmount /
           totalMonths;

END$$

DELIMITER ;