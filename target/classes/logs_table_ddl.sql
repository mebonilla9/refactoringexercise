create table logs
(
    id serial not null primary key,
    date_log    time with time zone,
    date_string varchar,
    level       varchar,
    message     varchar
);

alter table logs
    owner to postgres;