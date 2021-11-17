create table parkshark.division
(
    division_id        integer not null
        constraint division_pk
            primary key,
    parent_division_id integer,
    name               varchar,
    original_name      varchar,
    director_id        integer
);

alter table parkshark.division
    owner to student;

create table parkshark.address
(
    address_id   integer not null
        constraint address_pk
            primary key,
    streetname   varchar,
    streetnumber varchar,
    city         varchar,
    postal_code  varchar
);

alter table parkshark.address
    owner to student;

create table parkshark.person
(
    id                   integer not null
        constraint person_pk
            primary key,
    firstname            varchar,
    lastname             varchar,
    email                varchar,
    address_id_fk        integer
        constraint address_id_fk
            references parkshark.address,
    phonenumber_mobile   varchar,
    phonenumber_local    varchar,
    license_plate_number varchar,
    registration_date    date
);

alter table parkshark.person
    owner to student;

create table parkshark.parkinglot
(
    name             varchar,
    parking_category parkshark.parking_category,
    price_per_hour   numeric,
    capacity         integer,
    address_id_fk    integer
        constraint address_id_fk
            references parkshark.address,
    division_id_fk   integer
        constraint division_id_fk
            references parkshark.division,
    contact_id_fk    integer
        constraint contact_id_fk
            references parkshark.person,
    parkinglot_id    integer not null
        constraint parkinglot_pk
            primary key
);

alter table parkshark.parkinglot
    owner to student;

create table parkshark.membership
(
    person_id_fk integer,
    type         parkshark.membership_type
);

alter table parkshark.membership
    owner to student;

create table parkshark.parkinglot_allocation
(
    parkinglot_allocation_id integer not null
        constraint parkinglot_allocation_pk
            primary key,
    parkinglot_id_fk         integer
        constraint parkinglot_id_fk
            references parkshark.parkinglot,
    member_id                integer
        constraint member_id_fk
            references parkshark.person,
    license_plate_number     varchar,
    start_time               varchar,
    stop_time                varchar
);

alter table parkshark.parkinglot_allocation
    owner to student;

create type parkshark.parking_category as enum ('underground', 'aboveground');
create type parkshark.membership_type as enum ('Bronze', 'Silver', 'Gold');
