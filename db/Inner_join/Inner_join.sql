create table if not exists categories(
    id serial primary key,
    name varchar(255),
	term date
);

create table if not exists drivers(
    id serial primary key,
    name varchar(255),
	age int,
	categories_id int references categories(id)
);

insert into categories(name, term) values ('A', '2026-05-15');
insert into categories(name, term) values ('B', '2026-10-9');
insert into categories(name, term) values ('M', '2028-7-14');

insert into drivers(name, age, categories_id) values ('Ivan', 30, 2);
insert into drivers(name, age, categories_id) values ('Kirill', 23, 1);
insert into drivers(name, age, categories_id) values ('Roman', 27, 3);

select d.name Имя, d.age Возраст, c.name Категория, c.term Срок from drivers d
join categories c on d.categories_id = c.id;
select d.name Имя, d.age Возраст, c.name Категория, c.term Срок from drivers d
join categories c on d.categories_id = c.id
where c.name = 'A';
select d.name Имя, d.age Возраст, c.name Категория, c.term Срок from drivers d
join categories c on d.categories_id = c.id
where c.term < '2027-01-01';