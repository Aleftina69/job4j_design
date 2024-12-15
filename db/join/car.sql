create table car_bodies(
id serial primary key,
name varchar(255)
);

create table car_engines(
id serial primary key,
name varchar(255)
);

create table car_transmissions(
id serial primary key,
name varchar(255)
);

create table cars(
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values
('Седан'),
('Хэтчбек'),
('Пикап'),
('Кроссовер');

insert into car_engines(name) values
('Бензиновый'),
('Дизельный'),
('Электрический');

insert into car_transmissions(name) values
('Механическая'),
('Автоматическая');

insert into cars(name, body_id, engine_id, transmission_id) values
('Машина 1', 1, 1, 1),
('Машина 2', 2, 2, 2),
('Машина 3', 1, 3, 1),
('Машина 4', 3, 1, null);

select c.id as id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmission_name
from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name as body_name
from car_bodies b
left join cars c on b.id = c.body_id
where c.body_id is null;

select e.name as engine_name
from car_engines e
left join cars c on e.id = c.engine_id
where c.engine_id is null;

select t.name as transmission_name
from car_transmissions t
left join cars c on t.id = c.transmission_id
where c.transmission_id is null;