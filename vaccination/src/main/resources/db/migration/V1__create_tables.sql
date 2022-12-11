create table if not exists cities (
	id_city bigint not null auto_increment,
	zip smallint(4) not null,
	city varchar(40) not null,
	district varchar(40),
	constraint cities_pk primary key (id_city)
);

create table if not exists citizens(
	id_citizen bigint not null auto_increment,
	citizen_name VARCHAR(255) not null,
	city_id bigint not null,
	age bigint not null,
	email varchar(255),
	snn varchar(10),
	constraint citizens_pk primary key (id_citizen),
	constraint citizens_FK foreign key (city_id) references cities(id_city)
);

create table if not exists vaccinations (
	id_vaccination bigint not null auto_increment,
	citizen_id bigint not null,
	vaccination_date date,
	vaccine_type varchar(40),
	status varchar(10) not null,
	note varchar(50),
	constraint vaccinations_pk primary key (id_vaccination),
	constraint vaccinations_FK foreign key (citizen_id) references citizens(id_citizen)
);
