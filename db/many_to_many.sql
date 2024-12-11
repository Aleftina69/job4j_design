create table if not exists driver(
    id serial primary key,
    name varchar(255)
);

create table if not exists category(
    id serial primary key,
    name varchar(255)
);

create table if not exists driver_category(
    id serial primary key,
    driver_id int references driver(id),
    category_id int references category(id)
);
insert into driver(name) values ('Ivan');
insert into driver(name) values ('Kirill');
insert into driver(name) values ('Roman');

insert into category(name) values ('A');
insert into category(name) values ('B');
insert into category(name) values ('M');

insert into driver_category(driver_id, category_id) values (1, 1);
insert into driver_category(driver_id, category_id) values (1, 2);
insert into driver_category(driver_id, category_id) values (1, 3);
insert into driver_category(driver_id, category_id) values (2, 1);
insert into driver_category(driver_id, category_id) values (2, 2);
insert into driver_category(driver_id, category_id) values (3, 3);