

CREATE TABLE "template" (
	id serial4 primary key NOT NULL,
	description varchar NOT NULL,
	"name" varchar,
	status boolean,
	create_user_id int not null,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL
);


CREATE TABLE "template_form_builder" (
	id serial4 primary key NOT NULL,
	"label" varchar,
	"name" varchar,
	placeholder varchar,
	required boolean,
	layout varchar,
	"options" varchar,
	template_id int,
	create_user_id int not null,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT null,
	format_date_time timestamp,
	value varchar,
	type varchar
);

CREATE TABLE "approval_template" (
	user_id int not null,
	template_id int not null,
	PRIMARY KEY (user_id, template_id)
);


CREATE TABLE "request" (
	id serial4 primary key NOT NULL,
	resource_id int,
	"purpose" varchar,
	note varchar,
	create_user_id int not null,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT null,
	status varchar
);


CREATE TABLE "request_form_value" (
	id serial4 primary key NOT NULL,
	request_id int,
	"label" varchar,
	placeholder varchar,
	required boolean,
	layout varchar,
	"options" varchar,
	template_id int,
	create_user_id int not null,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT null,
	format_date_time timestamp,
	value varchar,
	type varchar
);

CREATE TABLE "request_approval" (
	user_id int,
	request_id int,
	approval_status varchar,
	approval_time timestamp,
	comment varchar,
	PRIMARY KEY (user_id,request_id)
);





