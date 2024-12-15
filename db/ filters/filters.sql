create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type (id),
    expired_date date,
    price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('ОВОЩИ'), ('МОРОЖЕННОЕ');
insert into product(name, type_id, expired_date, price) values
('Сыр плавленный', 1, '2024-12-08', 150),
('Сыр моцарелла', 1, '2024-12-25', 300),
('Сыр голандский', 1, '2024-12-17', 500),
('Сыр брынза', 1, '2024-12-21', 400);

insert into product(name, type_id, expired_date, price) values
('Молоко домик в деревне', 2, '2024-12-20', 100),
('Молоко простоквашино', 2, '2024-12-13', 90),
('Молоко молочная ферма', 2, '2024-12-28', 110);

insert into product(name, type_id, expired_date, price) values
('Картофель', 3, '2025-03-10', 50),
('Морковь', 3, '2025-03-15', 35),
('Лук', 3, '2025-01-23', 25),
('Капуста', 3, '2025-02-05', 60);

insert into product(name, type_id, expired_date, price) values
('Мороженое эскимо', 4, '2025-10-08', 78),
('Мороженое стаканчик', 4, '2025-11-21', 34),
('Мороженое фруктовый лед', 4, '2024-07-16', 65);

select * from product
join type t on p.type_id = t.id
where t.name  = 'СЫР';

select * from product
where name LIKE '%мороженое%';

select * from product
where expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name, count(p.id) as количество
from type t
join product p on t.id = p.type_id
group by t.name;

select * from product
join type t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select * from product
join type t on p.type_id = t.id
where t.name  = 'СЫР';

select t.name
from type t
join product p ON t.id = p.type_id
group by t.name
having count(p.id) < 10;

select * from product
join type t on p.type_id = t.id;