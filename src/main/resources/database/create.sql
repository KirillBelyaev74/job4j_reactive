create table weather(
    id serial primary key,
    city varchar(50) not null unique,
    temperature int not null
);