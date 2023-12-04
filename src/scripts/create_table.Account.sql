CREATE TABLE IF NOT EXISTS Account(
    id SERIAL PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    balance DOUBLE PRECISION NOT NULL 
);