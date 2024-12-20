create table products
(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create
or replace function tax_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
    after insert
    on products
    referencing new table as
    inserted
    for each statement
    execute procedure tax_after();

create
or replace function tax_before()
    returns trigger as
$$
    BEGIN
        NEW.price := NEW.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
    before insert
    on products
    for each row
    execute procedure tax_before();

create table history_of_price
(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create
or replace function log_price_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (NEW.name, NEW.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger after_insert_log_price
    after insert
    on products
    for each row
    execute procedure log_price_history();