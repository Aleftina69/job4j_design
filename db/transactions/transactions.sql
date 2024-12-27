CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    products_name VARCHAR(100),
    price DECIMAL(10, 2),
    stock INT
);

INSERT INTO products (products_name, price, stock) VALUES
('Product A', 10.00, 100),
('Product B', 15.00, 50),
('Product C', 20.00, 30);

--1 уровень транзакции

BEGIN TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

SELECT * FROM products;

--2 уровень транзакции

BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;

UPDATE products SET stock = stock - 10 WHERE id = 1;

SELECT * FROM products;

COMMIT;

--3 уровень транзакции

BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;

SELECT * FROM products;

--4 уровень транзакции
--1 транзакция

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

UPDATE products SET stock = stock - 10 WHERE id = 1;

--2 транзакция

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

UPDATE products SET stock = stock - 5 WHERE id = 2;