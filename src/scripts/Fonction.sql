CREATE OR REPLACE FUNCTION get_transaction_sum(
    account_id INT,
    start_datetime TIMESTAMP,
    end_datetime TIMESTAMP
)
RETURNS DOUBLE PRECISION
AS $$
DECLARE
total_amount DOUBLE PRECISION;
BEGIN
SELECT
        COALESCE(SUM(CASE WHEN t.amount > 0 THEN t.amount ELSE 0 END), 0)
        - COALESCE(SUM(CASE WHEN t.amount < 0 THEN t.amount ELSE 0 END), 0)
INTO total_amount
FROM transaction t
WHERE t.account_id = account_id
  AND t.transaction_date BETWEEN start_datetime AND end_datetime;

RETURN total_amount;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_category_sum(
    account_id INT,
    start_datetime TIMESTAMP,
    end_datetime TIMESTAMP
)
RETURNS TABLE (restaurant DOUBLE PRECISION, salary DOUBLE PRECISION)
AS $$
BEGIN
RETURN QUERY
SELECT
    COALESCE(SUM(CASE WHEN t.label = 'Restaurant' THEN t.amount ELSE 0 END), 0) AS restaurant,
    COALESCE(SUM(CASE WHEN t.label = 'Salary' THEN t.amount ELSE 0 END), 0) AS salary
FROM transaction t
WHERE t.transaction_date BETWEEN start_datetime AND end_datetime
  AND t.account_id = account_id;
END;
$$ LANGUAGE plpgsql
