# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "TBL_ACCESS_POLICIES" ("ACCESS_POLICY_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512));
create table "TBL_ADDRESS_LOCATIONS" ("ADDRESS_LOCATION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"LOCATION_ID" integer NOT NULL);
create table "TBL_ADDRESS_PARTS" ("ADDRESS_PART_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"PHYSICAL_ADDRESS_ID" integer NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512),"LANG" varchar(64));
create table "TBL_COMPLEX_NAMES" ("COMPLEX_NAME_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512),"LANG" varchar(64));
create table "TBL_DESCRIPTIONS" ("DESCRIPTION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512),"LANG" varchar(64));
create table "TBL_ELECTRONIC_ADDRESS_ARGS" ("ELECTRONIC_ADDRESS_ARG_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"ELECTRONIC_ADDRESS_ID" integer NOT NULL,"NAME" varchar(512),"REQUIRED" boolean,"TYPE" varchar(512),"USE" varchar(512));
create table "TBL_ELECTRONIC_ADDRESSES" ("ELECTRONIC_ADDRESS_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"ADDRESS_LOCATION_ID" integer NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512));
create table "TBL_IDENTIFIERS" ("IDENTIFIER_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512));
create table "TBL_LOCATIONS" ("LOCATION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL);
create table "TBL_NAME_PARTS" ("NAME_PART_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"COMPLEX_NAME_ID" integer NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512));
create table "TBL_PHYSICAL_ADDRESSES" ("PHYSICAL_ADDRESS_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"ADDRESS_LOCATION_ID" integer NOT NULL,"TYPE" varchar(512),"LANG" varchar(64));
create table "TBL_REGISTRY_OBJECTS" ("REGISTRY_OBJECT_KEY" bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_CLASS" varchar(512),"TYPE" varchar(512),"ORIGINATING_SOURCE" varchar(512),"ORIGINATING_SOURCE_TYPE" varchar(512));
create table "TBL_RELATED_INFOS" ("RELATED_INFO_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512));
create table "TBL_RELATION_DESCRIPTIONS" ("RELATION_DESCRIPTION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"RELATION_ID" integer NOT NULL,"DESCRIPTION" varchar(512),"TYPE" varchar(512),"LANG" varchar(64),"URL" varchar(512));
create table "TBL_RELATIONS" ("RELATION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"RELATED_REGISTRY_OBJECT_KEY" bigint NOT NULL);
create table "TBL_SPATIAL_LOCATIONS" ("SPATIAL_LOCATION_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"LOCATION_ID" integer NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512),"LANG" varchar(64));
create table "TBL_SUBJECTS" ("SUBJECT_ID" integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"REGISTRY_OBJECT_KEY" bigint NOT NULL,"VALUE" varchar(512),"TYPE" varchar(512),"LANG" varchar(64));
alter table "TBL_ACCESS_POLICIES" add constraint "FK_TBL_ACCESS_POLICIES__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_ADDRESS_LOCATIONS" add constraint "FK_TBL_ADDRESS_LOCATIONS__LOCATIONS" foreign key("LOCATION_ID") references "TBL_LOCATIONS"("LOCATION_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_ADDRESS_PARTS" add constraint "FK_TBL_ADDRESS_PARTS__PHYSICAL_ADDRESSES" foreign key("PHYSICAL_ADDRESS_ID") references "TBL_PHYSICAL_ADDRESSES"("PHYSICAL_ADDRESS_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_COMPLEX_NAMES" add constraint "FK_TBL_COMPLEX_NAMES__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_DESCRIPTIONS" add constraint "FK_TBL_DESCRIPTIONS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_ELECTRONIC_ADDRESS_ARGS" add constraint "FK_TBL_ELECTRONIC_ADDRESS_ARGS__ELECTRONIC_ADDRESSES" foreign key("ELECTRONIC_ADDRESS_ID") references "TBL_ELECTRONIC_ADDRESSES"("ELECTRONIC_ADDRESS_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_ELECTRONIC_ADDRESSES" add constraint "FK_TBL_ELECTRONIC_ADDRESSES__ADDRESS_LOCATIONS" foreign key("ADDRESS_LOCATION_ID") references "TBL_ADDRESS_LOCATIONS"("ADDRESS_LOCATION_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_IDENTIFIERS" add constraint "FK_TBL_IDENTIFIERS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_LOCATIONS" add constraint "FK_TBL_LOCATIONS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_NAME_PARTS" add constraint "FK_TBL_NAME_PARTS__COMPLEX_NAMES" foreign key("COMPLEX_NAME_ID") references "TBL_COMPLEX_NAMES"("COMPLEX_NAME_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_PHYSICAL_ADDRESSES" add constraint "FK_TBL_PHYSICAL_ADDRESSES__ADDRESS_LOCATIONS" foreign key("ADDRESS_LOCATION_ID") references "TBL_ADDRESS_LOCATIONS"("ADDRESS_LOCATION_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_RELATED_INFOS" add constraint "FK_TBL_RELATED_INFOS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_RELATION_DESCRIPTIONS" add constraint "FK_TBL_RELATION_DESCRIPTIONS__RELATIONS" foreign key("RELATION_ID") references "TBL_RELATIONS"("RELATION_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_RELATIONS" add constraint "FK_TBL_RELATIONS__RELATED_REGISTRY_OBJECTS" foreign key("RELATED_REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_RELATIONS" add constraint "FK_TBL_RELATIONS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;
alter table "TBL_SPATIAL_LOCATIONS" add constraint "FK_TBL_SPATIAL_LOCATIONS__LOCATIONS" foreign key("LOCATION_ID") references "TBL_LOCATIONS"("LOCATION_ID") on update RESTRICT on delete RESTRICT;
alter table "TBL_SUBJECTS" add constraint "FK_TBL_SUBJECTS__REGISTRY_OBJECTS" foreign key("REGISTRY_OBJECT_KEY") references "TBL_REGISTRY_OBJECTS"("REGISTRY_OBJECT_KEY") on update RESTRICT on delete RESTRICT;

# --- !Downs

alter table "TBL_ACCESS_POLICIES" drop constraint "FK_TBL_ACCESS_POLICIES__REGISTRY_OBJECTS";
alter table "TBL_ADDRESS_LOCATIONS" drop constraint "FK_TBL_ADDRESS_LOCATIONS__LOCATIONS";
alter table "TBL_ADDRESS_PARTS" drop constraint "FK_TBL_ADDRESS_PARTS__PHYSICAL_ADDRESSES";
alter table "TBL_COMPLEX_NAMES" drop constraint "FK_TBL_COMPLEX_NAMES__REGISTRY_OBJECTS";
alter table "TBL_DESCRIPTIONS" drop constraint "FK_TBL_DESCRIPTIONS__REGISTRY_OBJECTS";
alter table "TBL_ELECTRONIC_ADDRESS_ARGS" drop constraint "FK_TBL_ELECTRONIC_ADDRESS_ARGS__ELECTRONIC_ADDRESSES";
alter table "TBL_ELECTRONIC_ADDRESSES" drop constraint "FK_TBL_ELECTRONIC_ADDRESSES__ADDRESS_LOCATIONS";
alter table "TBL_IDENTIFIERS" drop constraint "FK_TBL_IDENTIFIERS__REGISTRY_OBJECTS";
alter table "TBL_LOCATIONS" drop constraint "FK_TBL_LOCATIONS__REGISTRY_OBJECTS";
alter table "TBL_NAME_PARTS" drop constraint "FK_TBL_NAME_PARTS__COMPLEX_NAMES";
alter table "TBL_PHYSICAL_ADDRESSES" drop constraint "FK_TBL_PHYSICAL_ADDRESSES__ADDRESS_LOCATIONS";
alter table "TBL_RELATED_INFOS" drop constraint "FK_TBL_RELATED_INFOS__REGISTRY_OBJECTS";
alter table "TBL_RELATION_DESCRIPTIONS" drop constraint "FK_TBL_RELATION_DESCRIPTIONS__RELATIONS";
alter table "TBL_RELATIONS" drop constraint "FK_TBL_RELATIONS__RELATED_REGISTRY_OBJECTS";
alter table "TBL_RELATIONS" drop constraint "FK_TBL_RELATIONS__REGISTRY_OBJECTS";
alter table "TBL_SPATIAL_LOCATIONS" drop constraint "FK_TBL_SPATIAL_LOCATIONS__LOCATIONS";
alter table "TBL_SUBJECTS" drop constraint "FK_TBL_SUBJECTS__REGISTRY_OBJECTS";
drop table "TBL_ACCESS_POLICIES";
drop table "TBL_ADDRESS_LOCATIONS";
drop table "TBL_ADDRESS_PARTS";
drop table "TBL_COMPLEX_NAMES";
drop table "TBL_DESCRIPTIONS";
drop table "TBL_ELECTRONIC_ADDRESS_ARGS";
drop table "TBL_ELECTRONIC_ADDRESSES";
drop table "TBL_IDENTIFIERS";
drop table "TBL_LOCATIONS";
drop table "TBL_NAME_PARTS";
drop table "TBL_PHYSICAL_ADDRESSES";
drop table "TBL_REGISTRY_OBJECTS";
drop table "TBL_RELATED_INFOS";
drop table "TBL_RELATION_DESCRIPTIONS";
drop table "TBL_RELATIONS";
drop table "TBL_SPATIAL_LOCATIONS";
drop table "TBL_SUBJECTS";

