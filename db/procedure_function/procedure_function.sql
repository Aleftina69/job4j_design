create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

create
or replace procedure delete_product(i_count integer)
language 'plpgsql'
as $$
    BEGIN
        if i_count = 0 THEN
            delete from products where count = i_count;
        end if;
    END
$$;

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

create
or replace function f_delete_product(p_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        if p_id > 5 then
            delete from products where id = p_id;
        end if;
    end;
$$;