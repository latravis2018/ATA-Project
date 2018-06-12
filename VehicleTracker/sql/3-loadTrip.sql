
create temp table if not exists tripdata (
	make varchar(40),
	model varchar(50),
	year int,
	lat real,
	long real,
	city varchar(60),
	state varchar(60),
	zip int,
	trip_num int
);


create index IX_td_make on tripdata (make);
create index IX_td_model on tripdata (model);
create index IX_year on tripdata (year);

COPY tripdata(make,model,year,lat,long, city, state, zip, trip_num)
FROM '/Volumes/Data-HD3TB/Users/latravis/Documents/Professional/Career/JobApplications/2018/Procession Systems/Advanced Technology Applications/VehicleTrackerDemo/VehicleTracker/data/trip-locations.csv' WITH (FORMAT csv);

alter table trip add column if not exists trip_num int;
create index IX_trip on trip (trip_num);

delete from trip_location;
delete from trip;

insert into trip(trip_veh_id, trip_start_city_id, trip_start_state_id, trip_start_zip_id, trip_num)
select DISTINCT v.veh_id, c.city_id, s.state_id, z.zip_id, td.trip_num
from tripdata td, veh_make vmk, veh_model vmd, vehicle v, city c, state s, zip z
where td.make = vmk.vmk_name and td.model = vmd.vmd_name and vmk.vmk_id = v.vmk_id and vmd.vmd_id = v.vmd_id and td.year = v.veh_year and 
td.city = c.city_name and td.state = s.state_name and td.zip = z.zip_code
group by v.veh_id, c.city_id, s.state_id, z.zip_id, td.trip_num
order by td.trip_num;


insert into trip_location(trip_loc_lat, trip_loc_long, trip_id)
select td.lat, td.long, t.trip_id 
from tripdata td, trip t
where td.trip_num = t.trip_num order by td.trip_num;

alter table trip drop column if exists trip_num;



