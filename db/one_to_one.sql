create table if not exists key_home(
    id serial primary key,
    address varchar(250),
    number_home int
);

create table if not exists people(
    id serial primary key,
    name varchar(255),
    key_home_id int references key_home(id)
);