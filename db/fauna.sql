create table if not exists fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) VALUES
('Lion', 12, '1650-05-01'),
('Elephant', 60, '1833-08-15'),
('Giraffe', 25, '2012-03-10'),
('Panda', 20, '2018-11-20'),
('Kangaroo', 18, '2016-07-30'),
('Penguin', 15000, '2014-09-12'),
('Zebra', 25, '2011-02-28'),
('Cheetah', 10, null),
('Tiger', 15, '2013-06-18'),
('Goldfish', 3, '1994-05-30'),
('Catfish', 12, null),
('Angelfish', 10, '2000-03-04'),
('Giant Fish', 20000, '1945-01-01'),
('Prehistoric Fish', 18, '1940-05-15'),
('Bear', 20, '2009-12-05');
select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 20000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';