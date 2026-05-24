INSERT INTO customer_addresses(address_id, door_no, street_name, layout, city, pincode)
VALUES
(1, '12A', 'MG Road', 'Central', 'Pune', '411001'),
(2, '45B', 'FC Road', 'West', 'Mumbai', '400001'),
(3, '78C', 'Ring Road', 'North', 'Bangalore', '560001'),
(4, '22D', 'Park Street', 'East', 'Kolkata', '700001'),
(5, '99E', 'Anna Salai', 'South', 'Chennai', '600001'),
(6, '11F', 'Brigade Road', 'Central', 'Bangalore', '560002'),
(7, '33G', 'JM Road', 'Shivaji Nagar', 'Pune', '411004'),
(8, '55H', 'Link Road', 'Andheri', 'Mumbai', '400053'),
(9, '66I', 'Sector 18', 'Noida', 'Noida', '201301'),
(10, '88J', 'Banjara Hills', 'Jubilee Hills', 'Hyderabad', '500034');

INSERT INTO customers(
    customer_id,
    customer_name,
    customer_email,
    billing_address_id,
    shipping_address_id
)
VALUES
(1, 'John Doe', 'john@example.com', 1, 2),
(2, 'Alice Smith', 'alice@example.com', 3, 4),
(3, 'Bob Johnson', 'bob@example.com', 5, 6),
(4, 'Charlie Brown', 'charlie@example.com', 7, 8),
(5, 'David Miller', 'david@example.com', 9, 10);

ALTER TABLE customer_addresses ALTER COLUMN address_id RESTART WITH 11;
ALTER TABLE customers ALTER COLUMN customer_id RESTART WITH 6;