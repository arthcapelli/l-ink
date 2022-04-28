create table db_user (
	id integer primary key,
	user_name varchar(50) not null,
	email varchar(50) not null unique,
	password varchar(128) not null,
	avatar varchar(200),
	is_tattoo_artist boolean not null
)

create sequence seq_db_user start with 1 increment by 1;

select * from db_user

create table db_user_tag (
	id integer primary key,
	tag_name varchar(50) not null,
	user_id integer not null,
	constraint id_user_tag_fk_id_user foreign key (id_user) references db_user(id)
)

create sequence seq_db_user_tag start with 1 increment by 1;