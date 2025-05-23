/*
Users table

create table users
(
    username    varchar(5)   not null,
    create_at   datetime(6)  null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6)  null,
    email       varchar(255) null,
    password    varchar(255) not null
);

Schedule table

create table schedule
(
    create_at   datetime(6)  null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6)  null,
    user_id     bigint       null,
    contents    longtext     null,
    title       varchar(255) not null,
    username    varchar(255) not null,
    constraint FKdn5svbxyacce1gpfiawk7iqtc
        foreign key (user_id) references users (id)
);

*/