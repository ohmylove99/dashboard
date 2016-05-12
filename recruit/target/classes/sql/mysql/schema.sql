drop table if exists ss_task;
drop table if exists ss_user;
drop table if exists ss_resume;
drop table if exists ss_resumehis;
drop table if exists ss_job;
drop table if exists ss_jobhis;

create table ss_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table ss_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp default 0,
	primary key (id)
) engine=InnoDB;

create table ss_resume (
	id bigint auto_increment,
	name varchar(128) not null,
	description varchar(255),
	kvs varchar(255),/*Key-Value*/
	original_doc blob,
	converted_doc blob,
	job_id bigint,
	version bigint,
	updated_time timestamp default 0,
	created_time timestamp default 0,
	updated_by varchar(64),
	created_by varchar(64),
    primary key (id)
) engine=InnoDB;

create table ss_resumehis (
	id bigint auto_increment,
	name varchar(128) not null,
	description varchar(255),
	kvs varchar(255),/*Key-Value*/
	original_doc blob,
	converted_doc blob,
	job_id bigint,
	pid bigint,
	version bigint,
	updated_time timestamp default 0,
	created_time timestamp default 0,
	updated_by varchar(64),
	created_by varchar(64),
    primary key (id)
) engine=InnoDB;

create table ss_job (
	id bigint auto_increment,
	name varchar(64),
	description varchar(64),
	grade varchar(64),
	status varchar(64),
	open_time timestamp default 0,
	closed_time timestamp default 0,
	open_by varchar(64),
	closed_by varchar(64),
	version bigint,
	updated_time timestamp default 0,
	created_time timestamp default 0,
	updated_by varchar(64),
	created_by varchar(64),
	primary key (id)
) engine=InnoDB;

create table ss_jobhis (
	id bigint auto_increment,
	name varchar(64),
	description varchar(64),
	grade varchar(64),
	status varchar(64),
	open_time timestamp default 0,
	closed_time timestamp default 0,
	open_by varchar(64),
	closed_by varchar(64),
	pid bigint,
	version bigint,
	updated_time timestamp default 0,
	created_time timestamp default 0,
	updated_by varchar(64),
	created_by varchar(64),
	primary key (id)
) engine=InnoDB;