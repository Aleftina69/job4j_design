create table customers(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
insert into customers (first_name, last_name, age, country)
values ('Ivan', 'Ivanov', 30, 'Russia'),
       ('Anna', 'Petrova', 27, 'China'),
       ('Tom', 'Braun', 35, 'USA');

select * from customers
where age = (select MIN(age) from customers);

create table orders(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id)
values (100, 1),
       (200, 2);

select * from customers
where id not in (select customer_id from orders);
