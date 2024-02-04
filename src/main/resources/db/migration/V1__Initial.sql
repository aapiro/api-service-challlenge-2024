DROP TABLE IF EXISTS PUBLIC.PRICES;

CREATE TABLE PUBLIC.PRICES
(
    ID BIGINT NOT NULL,
    BRAND_ID   INTEGER    NOT NULL,
    START_DATE TIMESTAMP  NOT NULL,
    END_DATE   TIMESTAMP  NOT NULL,
    PRICE_LIST INTEGER    NOT NULL,
    PRODUCT_ID INTEGER    NOT NULL,
    PRIORITY   INTEGER    NOT NULL,
    PRICE      DOUBLE     NOT NULL,
    CURR       VARCHAR(3) NOT NULL,
    ACTIVE_STATUS INTEGER NOT NULL
);
INSERT INTO PUBLIC.PRICES (ID,BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR ,ACTIVE_STATUS)
VALUES (1,1, parsedatetime('2020-06-14-00.00.00', 'yyyy-MM-dd-hh.ss.SS'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.ss.SS'), 1, 35455, 0, 35.50, 'EUR',1),
       (2,1, parsedatetime('2020-06-14-15.00.00', 'yyyy-MM-dd-hh.ss.SS'), parsedatetime('2020-06-14-18.30.00', 'yyyy-MM-dd-hh.ss.SS'), 2, 35455, 1, 25.45, 'EUR',1),
       (3,1, parsedatetime('2020-06-15-00.00.00', 'yyyy-MM-dd-hh.ss.SS'), parsedatetime('2020-06-15-11.00.00', 'yyyy-MM-dd-hh.ss.SS'), 3, 35455, 1, 30.50, 'EUR',1),
       (4,1, parsedatetime('2020-06-15-16.00.00', 'yyyy-MM-dd-hh.ss.SS'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.ss.SS'), 4, 35455, 1, 38.95, 'EUR',1);