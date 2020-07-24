DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
                          customer_id INT IDENTITY NOT NULL PRIMARY KEY,
                          name VARCHAR(250) NOT NULL,
                          date_of_birth DATE NOT NULL,
                          nationality VARCHAR(250) NOT NULL,
                          email_address VARCHAR(250) NOT NULL,
                          address VARCHAR(250) NOT NULL
);

CREATE TABLE account (
                         account_id INT IDENTITY NOT NULL PRIMARY KEY,
                         customer_id INT NOT NULL,
                         account_type VARCHAR(250) NOT NULL,
                         account_number VARCHAR(250) NOT NULL,
                         iban VARCHAR(250) NOT NULL,
                         status VARCHAR(250) NOT NULL,
                         balance FLOAT,
                         currency_code VARCHAR(10) NOT NULL
);

CREATE TABLE transaction (
                             transaction_id INT IDENTITY NOT NULL PRIMARY KEY,
                             account_id INT NOT NULL,
                             type VARCHAR(250) NOT NULL,
                             description VARCHAR(250) NOT NULL,
                             amount FLOAT,
                             currency_code VARCHAR(10) NOT NULL,
                             time_stamp TIMESTAMP NOT NULL
);

ALTER TABLE account ADD FOREIGN KEY (customer_id) REFERENCES customer(customer_id);
ALTER TABLE transaction ADD FOREIGN KEY (account_id) REFERENCES account(account_id);