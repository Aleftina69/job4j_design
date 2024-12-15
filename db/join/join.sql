create table departments(
id serial primary key,
name varchar(255)
);

create table employees(
id serial primary key,
name varchar(255),
department_id int references departments(id)
);

insert into departments(name) values
('Отдел продаж'),
('Отдел маркетинга'),
('Отдел разработки'),
('Отдел поддержки');

insert into employees(name, department_id) values
('Иван Иванов', 1),
('Петр Петров', 1),
('Мария Сидорова', 2),
('Алексей Алексеев', 3),
('Елена Еленовская', 3),
('Александр Александров', 4);

select d.name as Департамент, e.name as Работник
from departments d
left join employees e on d.id = e.department_id;

select d.name as Департамент, e.name as Работник
from employees e
right join departments d on e.department_id = d.id;

select d.name as Департамент, e.name as Работник
from departments d
full join employees e on d.id = e.department_id;

select d.name as Департамент, e.name as Работник
from departments d
cross join employees e;

select d.name as Департамент
from departments d
left join employees e on d.id = e.department_id
where e.id is null;

create table teens(
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens (name, gender) values
('Вася', 'мужской'),
('Маша', 'женский'),
('Петя', 'мужской'),
('Даша', 'женский'),
('Коля', 'мужской'),
('Лена', 'женский');

select t1.name as name1, t2.name as name2
from teens t1
cross join teens t2
where t1.gender != t2.gender AND t1.id < t2.id;