CREATE TABLE account (ID BIGINT AUTO_INCREMENT NOT NULL, EMAIL VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), PASSWORD VARCHAR(255), user_name VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE CASH (ID BIGINT AUTO_INCREMENT NOT NULL, curr_cash_val DOUBLE, initial_cash_val DOUBLE, ACCOUNT_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE holdings (ID BIGINT AUTO_INCREMENT NOT NULL, num_shares BIGINT, ACCOUNT_ID BIGINT, STOCK_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE stocks (ID BIGINT AUTO_INCREMENT NOT NULL, close_price DOUBLE, NAME VARCHAR(255), TICKER VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE transactions (ID BIGINT AUTO_INCREMENT NOT NULL, BUY TINYINT(1) default 0, DATE DATETIME, trade_price DOUBLE, trade_shares BIGINT, ACCOUNT_ID BIGINT, STOCK_ID BIGINT, PRIMARY KEY (ID))
ALTER TABLE CASH ADD CONSTRAINT FK_CASH_ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES account (ID)
ALTER TABLE holdings ADD CONSTRAINT FK_holdings_ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES account (ID)
ALTER TABLE holdings ADD CONSTRAINT FK_holdings_STOCK_ID FOREIGN KEY (STOCK_ID) REFERENCES stocks (ID)
ALTER TABLE transactions ADD CONSTRAINT FK_transactions_STOCK_ID FOREIGN KEY (STOCK_ID) REFERENCES stocks (ID)
ALTER TABLE transactions ADD CONSTRAINT FK_transactions_ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES account (ID)
