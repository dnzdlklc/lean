INSERT INTO customer (customer_id, name, date_of_birth, nationality, email_address, address) VALUES
(1, 'John Doe', PARSEDATETIME('15/09/1990','DD/MM/YYYY'), 'SAUDI', 'user1@leantech.me', '1 National Arabic Towers, Riyadh, Saudi Arabia'),
(2, 'Jane Bloggs', PARSEDATETIME('22/09/1987','DD/MM/YYYY'), 'BRITISH', 'user2@leantech.me', '140 Tabernacle Street, Shoreditch EC2A 4SD, London, UK'),
(3, 'Mike Smith', PARSEDATETIME('07/02/1995','DD/MM/YYYY'), 'AMERICAN', 'user3@leantech.me', '20045 Ocean Drive, Los Angeles, CA, USA');

INSERT INTO account (account_id, customer_id, account_type, account_number, iban, status, balance, currency_code) VALUES
(1,3,'SAVINGS','789654123051', 'SA000000789654123051', 'ACTIVE', 123.56, 'SAR'),
(2,1,'CHECKING','789654123052','SA000000789654123052', 'CLOSED', 0.00, 'GBP'),
(3,2,'DEPOSIT','789654123053','SA000000789654123053', 'ACTIVE', 6578.21, 'CHF');

INSERT INTO transaction (transaction_id, account_id, type, description, amount, currency_code, time_stamp) VALUES
(1,1,'TRANSFER','Transfer from Savings Account', 33.256, 'BHD', '2020-05-01T00:11:37'),
(2,1,'TRANSFER','Transfer from Another Bank', 11.99, 'SAR','2020-05-01T00:12:44'),
(3,1,'CARD_PAYMENT','Amazon', -3.68, 'SAR', '2020-04-22T00:11:13'),
(4,1,'CARD_PAYMENT','McDonalds', -11.58, 'SAR', '2020-04-30T00:19:25'),
(5,2,'DIRECT_DEBIT','Utility Payment', -180, 'GBP', '2020-06-11T00:01:55'),
(6,3,'REFUND', 'Return of Clothing', 56, 'CHF', '2008-01-25T06:28:31'),
(7,3,'TRANSFER', 'Transfer from Savings Account', 1000.45, 'CHF', '2020-05-01T00:11:37');