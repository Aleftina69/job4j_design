create table products
(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted)
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax1_trigger
    before insert
    on products
    for each row
    execute procedure tax1();

create
or replace function tax1()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create table history_of_price
(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger after_insert_log_price
    after insert
    on products
    for each row
    execute procedure log_price_history();

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