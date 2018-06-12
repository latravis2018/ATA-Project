
-- City / State / Zip Schema

create table if not exists city_location (
	city_loc_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	city_loc_lat real,
	city_loc_long real,
	CONSTRAINT PK_city_location PRIMARY KEY (city_loc_id)
);

create table if not exists city (
	city_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	city_name varchar(60),
	city_loc_id int NOT NULL,
	foreign key (city_loc_id) references city_location(city_loc_id),
	CONSTRAINT PK_city PRIMARY KEY (city_id)
);

create index if not exists IX_city_name on city (city_name);

create table if not exists state (
	state_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	state_name varchar(60),
	CONSTRAINT PK_state PRIMARY KEY (state_id)
);

create index if not exists IX_state_name on state (state_name);

create table if not exists zip (
	zip_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	zip_code int,
	CONSTRAINT PK_zip PRIMARY KEY (zip_id)
);

create index if not exists IX_zip_code on zip (zip_code);

create table if not exists city_state_zip (
	csz_city_id int,
	csz_state_id int,
	csz_zip_id int,
	foreign key (csz_city_id) references city(city_id),
	foreign key (csz_state_id) references state(state_id),
	foreign key (csz_zip_id) references zip(zip_id),
	CONSTRAINT PK_city_state_zip PRIMARY KEY (csz_city_id, csz_state_id, csz_zip_id)
);

create table if not exists veh_make (
	vmk_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	vmk_name varchar(40),
	CONSTRAINT PK_veh_make PRIMARY KEY (vmk_id)
);

create index if not exists IX_vmk_name on veh_make (vmk_name);

create table if not exists veh_model (
	vmd_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	vmd_name varchar(50),
	CONSTRAINT PK_veh_model PRIMARY KEY (vmd_id)
);

create index if not exists IX_veh_model on veh_model (vmd_name);

create table if not exists vehicle (
	veh_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	veh_year int NOT NULL,
	vmk_id int NOT NULL,
	vmd_id int NOT NULL,
	foreign key (vmk_id) references veh_make(vmk_id),
	foreign key (vmd_id) references veh_model(vmd_id),
	CONSTRAINT PK_vehicle PRIMARY KEY (veh_id)
);

create table if not exists trip (
	trip_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	trip_veh_id int NOT NULL,
	trip_start_timestamp timestamp default current_timestamp,
	trip_start_city_id int NOT NULL,
	trip_start_state_id int NOT NULL,
	trip_start_zip_id int NOT NULL,
	
	foreign key (trip_veh_id) references vehicle(veh_id),
	foreign key (trip_start_city_id) references city(city_id),
	foreign key (trip_start_state_id) references state(state_id),
	foreign key (trip_start_zip_id) references zip(zip_id),
	CONSTRAINT PK_trip PRIMARY KEY (trip_id)
);

create table if not exists trip_location (
	trip_loc_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	trip_loc_timestamp timestamp default current_timestamp,
	trip_loc_lat real,
	trip_loc_long real,
	trip_id int NOT NULL,
	foreign key (trip_id) references trip(trip_id),
	CONSTRAINT PK_trip_location PRIMARY KEY (trip_loc_id)
);





