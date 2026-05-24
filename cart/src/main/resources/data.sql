INSERT INTO carts(cart_id)
VALUES
(1),
(2),
(3);

INSERT INTO line_items(
    item_id,
    cart_id,
    product_id,
    product_name,
    quantity,
    price
)
VALUES
(1, 1, 1, 'Samsung Galaxy S24', 1, 79999.99),
(2, 1, 5, 'Boat Rockerz 450', 2, 1499.99),

(3, 2, 2, 'LG Refrigerator', 1, 45999.50),

(4, 3, 3, 'Sony WH-1000XM5', 1, 29999.00),
(5, 3, 4, 'Apple MacBook Air M3', 1, 124999.00);

ALTER TABLE carts ALTER COLUMN cart_id RESTART WITH 4;

ALTER TABLE line_items ALTER COLUMN item_id RESTART WITH 6;