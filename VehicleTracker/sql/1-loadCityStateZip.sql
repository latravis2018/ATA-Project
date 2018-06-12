
create temp table if not exists citystatezip (
	zip int,
	junk1 varchar(20),
	city varchar(60),
	state varchar(60),
	junk2 varchar(20),
	lat real,
	long real,
	junk3 varchar(100),
	junk4 varchar(40),
	junk5 varchar(40),
	junk6 varchar(40),
	junk7 varchar(40)
);

-- Indexes needed to load city state zip table (doesn't seem to be improving load performance.
-- I will put back into tables as constraints to see if performance improves.
-- Noticed city table got unique contraint violation when loading city data when had constraint on city name.

create index IX_csz_city on citystatezip (city);
create index IX_csz_state on citystatezip (state);
create index IX_csz_zip on citystatezip (zip);

COPY citystatezip(zip,junk1,city,state,junk2,lat,long,junk3,junk4,junk5,junk6,junk7)
FROM '/Volumes/Data-HD3TB/Users/latravis/Documents/Professional/Career/JobApplications/2018/Procession Systems/Advanced Technology Applications/VehicleTrackerDemo/VehicleTracker/data/free-zipcode-database-Primary.csv' WITH (FORMAT csv, HEADER);

insert into zip (zip_code) select DISTINCT zip from citystatezip order by zip;
insert into state (state_name) select DISTINCT state from citystatezip order by state;
insert into city_location (city_loc_lat, city_loc_long) select DISTINCT lat, long from citystatezip order by lat, long;

insert into city (city_name, city_loc_id)
select DISTINCT csz.city, cl.city_loc_id
from citystatezip csz, city_location cl
where csz.lat = cl.city_loc_lat and csz.long = cl.city_loc_long order by csz.city;

insert into city_state_zip (csz_city_id, csz_state_id, csz_zip_id)
select c.city_id c, s.state_id s, z.zip_id z
from citystatezip csz, city_location cl, city c, state s, zip z
where csz.lat = cl.city_loc_lat and csz.long = cl.city_loc_long and cl.city_loc_id = c.city_loc_id and csz.zip = z.zip_code and csz.state = s.state_name order by c.city_id, s.state_id, z.zip_id;
