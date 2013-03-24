# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbl_access_policies (
  access_policy_id          integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  constraint pk_tbl_access_policies primary key (access_policy_id))
;

create table tbl_address_locations (
  address_location_id       integer not null,
  location_id               integer,
  constraint pk_tbl_address_locations primary key (address_location_id))
;

create table tbl_address_parts (
  address_part_id           integer not null,
  physical_address_id       integer,
  value                     varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_address_parts primary key (address_part_id))
;

create table tbl_complex_names (
  complex_name_id           integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_complex_names primary key (complex_name_id))
;

create table tbl_descriptions (
  description_id            integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_descriptions primary key (description_id))
;

create table tbl_electronic_addresses (
  electronic_address_id     integer not null,
  address_location_id       integer,
  value                     varchar(512),
  type                      varchar(512),
  constraint pk_tbl_electronic_addresses primary key (electronic_address_id))
;

create table tbl_electronic_address_args (
  electronic_address_arg_id integer not null,
  electronic_address_id     integer,
  name                      varchar(512),
  required                  boolean,
  type                      varchar(512),
  use_                      varchar(512),
  constraint pk_tbl_electronic_address_args primary key (electronic_address_arg_id))
;

create table tbl_identifiers (
  identifier_id             integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  type                      varchar(512),
  constraint pk_tbl_identifiers primary key (identifier_id))
;

create table tbl_locations (
  location_id               integer not null,
  registry_object_key       bigint,
  constraint pk_tbl_locations primary key (location_id))
;

create table tbl_name_parts (
  name_part_id              integer not null,
  complex_name_id           integer,
  value                     varchar(512),
  type                      varchar(512),
  constraint pk_tbl_name_parts primary key (name_part_id))
;

create table tbl_physical_addresses (
  physical_address_id       integer not null,
  address_location_id       integer,
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_physical_addresses primary key (physical_address_id))
;

create table tbl_registry_objects (
  registry_object_key       bigint not null,
  registry_object_class     varchar(512),
  type                      varchar(32),
  originating_source        varchar(512),
  originating_source_type   varchar(512),
  constraint pk_tbl_registry_objects primary key (registry_object_key))
;

create table tbl_related_info (
  related_info_id           integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  constraint pk_tbl_related_info primary key (related_info_id))
;

create table tbl_relations (
  relation_id               integer not null,
  registry_object_key       bigint,
  related_registry_object_key bigint,
  constraint pk_tbl_relations primary key (relation_id))
;

create table tbl_relation_descriptions (
  relation_description_id   integer not null,
  relation_id               integer,
  description               varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  url                       varchar(512),
  constraint pk_tbl_relation_descriptions primary key (relation_description_id))
;

create table tbl_spatial_locations (
  spatial_location_id       integer not null,
  location_id               integer,
  value                     varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_spatial_locations primary key (spatial_location_id))
;

create table tbl_subjects (
  subject_id                integer not null,
  registry_object_key       bigint,
  value                     varchar(512),
  type                      varchar(512),
  lang                      varchar(64),
  constraint pk_tbl_subjects primary key (subject_id))
;

create sequence tbl_access_policies_seq;

create sequence tbl_address_locations_seq;

create sequence tbl_address_parts_seq;

create sequence tbl_complex_names_seq;

create sequence tbl_descriptions_seq;

create sequence tbl_electronic_addresses_seq;

create sequence tbl_electronic_address_args_seq;

create sequence tbl_identifiers_seq;

create sequence tbl_locations_seq;

create sequence tbl_name_parts_seq;

create sequence tbl_physical_addresses_seq;

create sequence tbl_registry_objects_seq;

create sequence tbl_related_info_seq;

create sequence tbl_relations_seq;

create sequence tbl_relation_descriptions_seq;

create sequence tbl_spatial_locations_seq;

create sequence tbl_subjects_seq;

alter table tbl_access_policies add constraint fk_tbl_access_policies__regist_1 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_access_policies__regist_1 on tbl_access_policies (registry_object_key);
alter table tbl_address_locations add constraint fk_tbl_address_locations__loca_2 foreign key (location_id) references tbl_locations (location_id) on delete restrict on update restrict;
create index ix_tbl_address_locations__loca_2 on tbl_address_locations (location_id);
alter table tbl_address_parts add constraint fk_tbl_address_parts__physical_3 foreign key (physical_address_id) references tbl_physical_addresses (physical_address_id) on delete restrict on update restrict;
create index ix_tbl_address_parts__physical_3 on tbl_address_parts (physical_address_id);
alter table tbl_complex_names add constraint fk_tbl_complex_names__registry_4 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_complex_names__registry_4 on tbl_complex_names (registry_object_key);
alter table tbl_descriptions add constraint fk_tbl_descriptions__registryO_5 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_descriptions__registryO_5 on tbl_descriptions (registry_object_key);
alter table tbl_electronic_addresses add constraint fk_tbl_electronic_addresses__a_6 foreign key (address_location_id) references tbl_address_locations (address_location_id) on delete restrict on update restrict;
create index ix_tbl_electronic_addresses__a_6 on tbl_electronic_addresses (address_location_id);
alter table tbl_electronic_address_args add constraint fk_tbl_electronic_address_args_7 foreign key (electronic_address_id) references tbl_electronic_addresses (electronic_address_id) on delete restrict on update restrict;
create index ix_tbl_electronic_address_args_7 on tbl_electronic_address_args (electronic_address_id);
alter table tbl_identifiers add constraint fk_tbl_identifiers__registryOb_8 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_identifiers__registryOb_8 on tbl_identifiers (registry_object_key);
alter table tbl_locations add constraint fk_tbl_locations__registryObje_9 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_locations__registryObje_9 on tbl_locations (registry_object_key);
alter table tbl_name_parts add constraint fk_tbl_name_parts__complexNam_10 foreign key (complex_name_id) references tbl_complex_names (complex_name_id) on delete restrict on update restrict;
create index ix_tbl_name_parts__complexNam_10 on tbl_name_parts (complex_name_id);
alter table tbl_physical_addresses add constraint fk_tbl_physical_addresses__ad_11 foreign key (address_location_id) references tbl_address_locations (address_location_id) on delete restrict on update restrict;
create index ix_tbl_physical_addresses__ad_11 on tbl_physical_addresses (address_location_id);
alter table tbl_related_info add constraint fk_tbl_related_info__registry_12 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_related_info__registry_12 on tbl_related_info (registry_object_key);
alter table tbl_relations add constraint fk_tbl_relations__registryObj_13 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_relations__registryObj_13 on tbl_relations (registry_object_key);
alter table tbl_relations add constraint fk_tbl_relations__relatedRegi_14 foreign key (related_registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_relations__relatedRegi_14 on tbl_relations (related_registry_object_key);
alter table tbl_relation_descriptions add constraint fk_tbl_relation_descriptions__15 foreign key (relation_id) references tbl_relations (relation_id) on delete restrict on update restrict;
create index ix_tbl_relation_descriptions__15 on tbl_relation_descriptions (relation_id);
alter table tbl_spatial_locations add constraint fk_tbl_spatial_locations__loc_16 foreign key (location_id) references tbl_locations (location_id) on delete restrict on update restrict;
create index ix_tbl_spatial_locations__loc_16 on tbl_spatial_locations (location_id);
alter table tbl_subjects add constraint fk_tbl_subjects__registryObje_17 foreign key (registry_object_key) references tbl_registry_objects (registry_object_key) on delete restrict on update restrict;
create index ix_tbl_subjects__registryObje_17 on tbl_subjects (registry_object_key);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tbl_access_policies;

drop table if exists tbl_address_locations;

drop table if exists tbl_address_parts;

drop table if exists tbl_complex_names;

drop table if exists tbl_descriptions;

drop table if exists tbl_electronic_addresses;

drop table if exists tbl_electronic_address_args;

drop table if exists tbl_identifiers;

drop table if exists tbl_locations;

drop table if exists tbl_name_parts;

drop table if exists tbl_physical_addresses;

drop table if exists tbl_registry_objects;

drop table if exists tbl_related_info;

drop table if exists tbl_relations;

drop table if exists tbl_relation_descriptions;

drop table if exists tbl_spatial_locations;

drop table if exists tbl_subjects;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tbl_access_policies_seq;

drop sequence if exists tbl_address_locations_seq;

drop sequence if exists tbl_address_parts_seq;

drop sequence if exists tbl_complex_names_seq;

drop sequence if exists tbl_descriptions_seq;

drop sequence if exists tbl_electronic_addresses_seq;

drop sequence if exists tbl_electronic_address_args_seq;

drop sequence if exists tbl_identifiers_seq;

drop sequence if exists tbl_locations_seq;

drop sequence if exists tbl_name_parts_seq;

drop sequence if exists tbl_physical_addresses_seq;

drop sequence if exists tbl_registry_objects_seq;

drop sequence if exists tbl_related_info_seq;

drop sequence if exists tbl_relations_seq;

drop sequence if exists tbl_relation_descriptions_seq;

drop sequence if exists tbl_spatial_locations_seq;

drop sequence if exists tbl_subjects_seq;

