CREATE TABLE IF NOT EXISTS TransferHistory (
    id SERIAL PRIMARY KEY,
    debitor_transaction_id INT,
    creditor_transaction_id INT,
    transfer_date TIMESTAMP
);

