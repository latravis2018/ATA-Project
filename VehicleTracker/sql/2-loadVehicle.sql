

create temp table if not exists vehicledata (
	junk1 varchar(20),
	junk2 varchar(40),
	make varchar(40),
	model varchar(50),
	junk3 varchar(100),
	junk4 varchar(40),
	year int
);

COPY vehicledata(junk1,junk2,make,model,junk3,junk4,year) FROM '/Volumes/Data-HD3TB/Users/latravis/Documents/Professional/Career/JobApplications/2018/Procession Systems/Advanced Technology Applications/VehicleTrackerDemo/VehicleTracker/data/vehicles-nodups.csv' WITH (FORMAT csv, HEADER);

insert into veh_make (vmk_name) select DISTINCT make from vehicledata order by make;
insert into veh_model (vmd_name) select DISTINCT model from vehicledata order by model;

insert into vehicle (veh_year, vmk_id, vmd_id)
select DISTINCT vd.year, vmk.vmk_id, vmd.vmd_id
from vehicledata vd, veh_make vmk, veh_model vmd
where vd.make = vmk.vmk_name and vd.model = vmd.vmd_name order by vd.year, vmk.vmk_id, vmd.vmd_id;

