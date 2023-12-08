CREATE TABLE IF NOT EXISTS Account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    last_update_date TIMESTAMP,
    currency_id INT,
    type VARCHAR(50),
    FOREIGN KEY (currency_id) REFERENCES Currency(id)

);

