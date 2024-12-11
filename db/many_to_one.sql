create table if not exists movie(
id serial primary key,
    name varchar(250)
);

create table if not exists viewer(
    id serial primary key,
    name varchar(250),
    movie_id int references movie(id)
);

insert into movie(name) values ('Гладиатор'), ('Титаник'), ('Человек-паук');
insert into viewer(name, movie_id) VALUES ('Ivan', 1);
insert into viewer(name, movie_id) VALUES ('Maria', 2);
insert into viewer(name, movie_id) VALUES ('Alex', 1);

select * from viewer;

select * from movie where id in (select movie_id from viewer);