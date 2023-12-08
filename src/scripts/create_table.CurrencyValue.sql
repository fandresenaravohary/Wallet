CREATE TABLE IF NOT EXISTS CurrencyValue (
    id SERIAL PRIMARY KEY,
    source_currency_id INT,
    destination_currency_id INT,
    value DOUBLE PRECISION,
    value_date DATE
);

