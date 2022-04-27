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
