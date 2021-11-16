SET SCHEMA 'parkshark';

CREATE TYPE "parking_category" AS ENUM (
  'underground',
  'aboveground'
);

CREATE TYPE "membership_type" AS ENUM (
  'Bronze',
  'Silver',
  'Gold'
);

CREATE TABLE "division" (
  "division_id" int,
  "parent_division_id" int,
  "name" varchar,
  "original_name" varchar,
  "director_id" int
);

CREATE TABLE "parkinglot" (
  "name" varchar,
  "parking_category" parking_category,
  "price_per_hour" decimal,
  "capacity" int,
  "address_id_fk" int,
  "division_id_fk" int,
  "contact_id_fk" int,
  "parkinglot_id" int
);

CREATE TABLE "person" (
  "id" int,
  "firstname" varchar,
  "lastname" varchar,
  "email" varchar,
  "address_id_fk" int,
  "phonenumber_mobile" varchar,
  "phonenumber_local" varchar,
  "license_plate_number" varchar,
  "registration_date" date
);

CREATE TABLE "address" (
  "address_id" int,
  "streetname" varchar,
  "streetnumber" varchar,
  "city" varchar,
  "postal_code" varchar
);

CREATE TABLE "membership" (
  "person_id_fk" int,
  "type" membership_type
);

CREATE TABLE "parkinglot_allocation" (
  "parkinglot_allocation_id" int,
  "parkinglot_id_fk" int,
  "member_id" int,
  "license_plate_number" varchar,
  "start_time" varchar,
  "stop_time" varchar
);



ALTER TABLE "parkshark".address ADD FOREIGN KEY ("address_id") REFERENCES "person" ("address_id_fk");

ALTER TABLE "parkshark".person ADD FOREIGN KEY ("id") REFERENCES "parkinglot" ("contact_id_fk");

ALTER TABLE "address" ADD FOREIGN KEY ("address_id") REFERENCES "parkinglot" ("address_id_fk");

ALTER TABLE "division" ADD FOREIGN KEY ("division_id") REFERENCES "division" ("parent_division_id");

ALTER TABLE "person" ADD FOREIGN KEY ("id") REFERENCES "membership" ("person_id_fk");

ALTER TABLE "person" ADD FOREIGN KEY ("id") REFERENCES "division" ("director_id");

ALTER TABLE "person" ADD FOREIGN KEY ("id") REFERENCES "parkinglot_allocation" ("member_id");

ALTER TABLE "parkinglot_allocation" ADD FOREIGN KEY ("parkinglot_id_fk") REFERENCES "parkinglot" ("parkinglot_id");

ALTER TABLE "parkinglot" ADD FOREIGN KEY ("division_id_fk") REFERENCES "division" ("division_id");
