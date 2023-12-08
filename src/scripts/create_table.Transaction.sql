CREATE TABLE IF NOT EXISTS transaction (
    transaction_id SERIAL PRIMARY KEY,
    transaction_date TIMESTAMP NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    type VARCHAR(10) NOT NULL,
    label VARCHAR(255) NOT NULL
);

