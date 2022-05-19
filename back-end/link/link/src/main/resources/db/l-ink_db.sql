--USER
create table db_user (
	id integer primary key,
	user_name varchar(50) not null,
	email varchar(50) not null unique,
	password varchar(128) not null,
	avatar varchar(200),
	is_tattoo_artist boolean not null,
	exp_time integer
);
create sequence seq_db_user start with 1 increment by 1;
select * from db_user;

--USER TAG
create table db_user_tag (
	id integer primary key,
	tag_name varchar(50) not null,
	user_id integer not null,
	constraint id_user_tag_fk_id_user foreign key (user_id) references db_user(id)
);
create sequence seq_db_user_tag start with 1 increment by 1;
select * from db_user_tag

--POST
create table db_post (
	id integer primary key,
	post_img varchar(500) not null,
	body_local varchar(150),
	measures varchar(25),
	user_id integer NOT NULL,
	CONSTRAINT id_post_fk_id_user FOREIGN KEY (user_id) REFERENCES db_user(id)
);
create sequence seq_db_post start with 1 increment by 1;
select * from db_post

--POST TAG
create table db_post_tag (
	id integer primary key,
	tag_name varchar(50) not null,
	post_id integer not null,
	constraint id_post_tag_fk_id_post foreign key (post_id) references db_post(id)
);
create sequence seq_db_post_tag start with 1 increment by 1;
select * from db_post_tag

--FAVORITE
CREATE TABLE db_favorite (
	id integer PRIMARY KEY,
	post_id integer not null,
	user_id integer not null,
	CONSTRAINT favorite_fk_id_post FOREIGN KEY (post_id) REFERENCES db_post(id),
	CONSTRAINT favorite_fk_id_user FOREIGN KEY (user_id) REFERENCES db_user(id)
);
CREATE SEQUENCE seq_db_favorite START WITH 1 INCREMENT BY 1;
SELECT * FROM db_favorite;

--COMMENT
CREATE TABLE db_comment (
	id integer PRIMARY KEY,
	comment_text varchar NOT NULL,
	created_at Timestamp not null,
	post_id integer not null,
	user_id integer not null,
	CONSTRAINT comment_fk_id_post FOREIGN KEY (post_id) REFERENCES db_post(id),
	CONSTRAINT comment_fk_id_user FOREIGN KEY (user_id) REFERENCES db_user(id)
);
CREATE SEQUENCE seq_db_comment START WITH 1 INCREMENT BY 1;

select * from db_comment;
