DELIMITER $$

CREATE FUNCTION CalculateAge(
    dateOfBirth DATE
)
RETURNS INT
DETERMINISTIC

BEGIN

    RETURN TIMESTAMPDIFF(
        YEAR,
        dateOfBirth,
        CURDATE()
    );

END$$

DELIMITER ;