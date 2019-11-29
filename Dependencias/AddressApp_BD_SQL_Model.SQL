/*
Created: 29/11/2019
Modified: 29/11/2019
Project: Address App
Model: PostgreSQL 10
Company: CEFET-MG
Author: Rubens Souza
Version: 1.0
Database: PostgreSQL 10
*/


-- Create tables section -------------------------------------------------

-- Table Person

CREATE TABLE "Person"(
 "id" Serial NOT NULL,
 "firstName" Text NOT NULL,
 "lastName" Text NOT NULL,
 "birthday" Date NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Person

ALTER TABLE "Person" ADD CONSTRAINT "PK_Person" PRIMARY KEY ("id")
;

-- Table Address

CREATE TABLE "Address"(
 "postalCode" Integer NOT NULL,
 "street" Text NOT NULL,
 "city" Text NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Address

ALTER TABLE "Address" ADD CONSTRAINT "PK_Address" PRIMARY KEY ("postalCode")
;

-- Table Person_Address

CREATE TABLE "Person_Address"(
 "id" Integer NOT NULL,
 "postalCode" Integer NOT NULL,
 "addressType" Text
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Person_Address

ALTER TABLE "Person_Address" ADD CONSTRAINT "PK_Person_Address" PRIMARY KEY ("postalCode","id")
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "Person_Address" ADD CONSTRAINT "Relationship1" FOREIGN KEY ("postalCode") REFERENCES "Address" ("postalCode") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Person_Address" ADD CONSTRAINT "Relationship2" FOREIGN KEY ("id") REFERENCES "Person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
;




