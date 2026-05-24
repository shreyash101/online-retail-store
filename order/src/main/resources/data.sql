INSERT INTO orders(order_id)
VALUES
(1),
(2),
(3);

INSERT INTO order_line_items(
    item_id,
    order_id,
    product_id,
    product_name,
    quantity,
    price
)
VALUES
(1, 1, 6, 'Dell XPS 15', 1, 154999.00),
(2, 1, 7, 'Logitech MX Master 3S', 1, 8999.00),

(3, 2, 8, 'iPad Air M2', 2, 59999.00),

(4, 3, 9, 'Canon EOS R10', 1, 87999.00),
(5, 3, 10, 'JBL Flip 6', 3, 9999.00);

ALTER TABLE orders ALTER COLUMN order_id RESTART WITH 4;

ALTER TABLE order_line_items ALTER COLUMN item_id RESTART WITH 6;