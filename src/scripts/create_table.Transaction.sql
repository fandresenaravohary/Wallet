CREATE TABLE IF NOT EXISTS transaction (
    transaction_id SERIAL PRIMARY KEY,
    transaction_date TIMESTAMP NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    type VARCHAR(10) NOT NULL,
    label VARCHAR(255) NOT NULL
);

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
