create table auth_user (id bigint not null, email varchar(255), password varchar(255), role int, username varchar(255), primary key (id));

create table vehicle (id bigint not null, engine varchar(255), image_link varchar(255), make varchar(255), model varchar(255), price bigint, year int not null, primary key (id));