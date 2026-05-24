INSERT INTO inventory_products(inventory_id, product_id, quantity)
VALUES
(1, 1, 50),
(2, 2, 30),
(3, 3, 100),
(4, 4, 15),
(5, 5, 200);

ALTER TABLE inventory_products ALTER COLUMN inventory_id RESTART WITH 6;