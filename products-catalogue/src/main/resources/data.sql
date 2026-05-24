INSERT INTO products(product_id, product_name, product_description, product_price)
VALUES
(1, 'Samsung Galaxy S24', 'Flagship Samsung smartphone with AMOLED display', 79999.99),

(2, 'LG Refrigerator', 'Double door refrigerator with smart cooling', 45999.50),

(3, 'Sony WH-1000XM5', 'Wireless noise cancelling headphones', 29999.00),

(4, 'Apple MacBook Air M3', 'Lightweight laptop with Apple M3 chip', 124999.00),

(5, 'Boat Rockerz 450', 'Bluetooth wireless over-ear headphones', 1499.99);

ALTER TABLE products ALTER COLUMN product_id RESTART WITH 6;