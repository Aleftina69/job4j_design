create table devices(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people(
    id   serial primary key,
    name varchar(255)
);

create table devices_people(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price)
values ('phone', 100000), ('laptop', 150000), ('headphones', 3000), ('fitness tracker', 4000), ('GoPro', 60000);
insert into people(name) values ('Коля'), ('Ваня'), ('Настя'), ('Света');
insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(people_id, device_id) values (2, 1), (2, 3), (2, 4);
insert into devices_people(people_id, device_id) values (3, 1), (3, 2), (3, 3), (3, 4);
insert into devices_people(people_id, device_id) values (4, 1), (4, 2), (4, 3), (4, 4), (4, 5);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
