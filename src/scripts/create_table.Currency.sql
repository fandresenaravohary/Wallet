CREATE TABLE IF NOT EXISTS Account(
    id SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL,
    code varchar(100) NOT NULL
);