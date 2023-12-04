CREATE TABLE IF NOT EXISTS transaction (
    transactionId SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    type VARCHAR(10) NOT NULL,
    description TEXT
);
