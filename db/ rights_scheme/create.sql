create table if not exists roles(
    id serial primary key,
    name varchar(255)
);

create table if not exists users(
    id serial primary key,
    name varchar(255),
    roles_id int references roles(id)
);

create table if not exists rules(
    id serial primary key,
    name varchar(255)
);

create table if not exists roles_rules(
    id serial primary key,
    name varchar(255),
     roles_id int references roles(id),
     rules_id int references rules(id)
);

create table if not exists states(
    id serial primary key,
    name varchar(255)
);

create table if not exists categories(
    id serial primary key,
    name varchar(255)
);


create table if not exists items(
    id serial primary key,
    name varchar(255),
    users_id int references users(id),
	categories_id int references categories(id),
	states_id int references states(id)
);

create table if not exists attachs(
    id serial primary key,
    name varchar(255),
    items_id int references items(id)
);

create table if not exists comments(
    id serial primary key,
    name varchar(255),
    items_id int references items(id)
);