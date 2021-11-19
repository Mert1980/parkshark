create sequence country_seq;

alter sequence country_seq owner to student;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to student;

create type parking_category as enum ('underground', 'aboveground');

alter type parking_category owner to student;

create type membership_type as enum ('Bronze', 'Silver', 'Gold');

alter type membership_type owner to student;

create table address
(
    address_id serial
        constraint address_pk
            primary key,
    streetname varchar,
    streetnumber varchar,
    city varchar,
    postal_code varchar
);

alter table address owner to student;

create table person
(
    id serial
        constraint person_pk
            primary key,
    firstname varchar,
    lastname varchar,
    email varchar,
    address_id_fk integer
        constraint address_id_fk
            references address,
    phonenumber_mobile varchar,
    phonenumber_local varchar,
    license_plate_number varchar,
    registration_date timestamp,
    membership_type_category parkshark.membership_type
);

alter table person owner to student;

create table division
(
    division_id serial
        constraint division_pk
            primary key,
    parent_division_id integer,
    name varchar,
    original_name varchar,
    director_id integer
        constraint fk6adxkthwccj7fqdgjwtfegd78
            references person
);

alter table division owner to student;

create table parking_lot
(
    name varchar,
    parking_lot_category parkshark.parking_category,
    price_per_hour numeric,
    capacity integer,
    address_id_fk integer
        constraint address_id_fk
            references address,
    division_id_fk integer
        constraint division_id_fk
            references division,
    contact_id_fk integer
        constraint contact_id_fk
            references person,
    parking_lot_id integer not null
        constraint parkinglot_pk
            primary key
);

alter table parking_lot owner to student;

create table parking_lot_allocations
(
    parking_lot_allocation_id integer not null
        constraint parkinglot_allocation_pk
            primary key,
    parking_lot_id_fk integer
        constraint parkinglot_id_fk
            references parking_lot,
    person_id_fk integer
        constraint member_id_fk
            references person,
    license_plate_number varchar,
    start_time varchar,
    stop_time varchar
);

alter table parking_lot_allocations owner to student;

