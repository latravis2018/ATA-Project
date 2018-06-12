Welcome to a simple vehicle tracking concept project.

Here is the directory structure of the project:

VehicleTrackerDemo/VehicleTracker:
————————————————————————————————————————————————

Project-ENV.sh	- project environment file
data		- data to load into the database
jetty		- jetty BASE folder
lib		- jar dependencies of the project
pom.xml		- top level pom file
sql		- sql scripts to create the schema and load the data
src		- project java source files
target		- project java class files

Project assumptions:
————————————————————————————————————————————————
- an instance of PostgresSql is already installed and running
- an instance of jetty server is installed
- maven POM system is installed
- there is a linux-like environment to be able to use the convenience shell scripts

First steps:
————————————————————————————————————————————————
1. Modify the file VehicleTrackerDemo/VehicleTracker/Project-ENV.sh
2. Run the script to setup environment:
. Project-ENV.sh

Creating the schema:
————————————————————————————————————————————————
psql -f 0-schema.sql
psql -f 1-loadCityStateZip.sql
psql -f 2-loadVehicle.sql
psql -f 3-loadTrip.sql

If you need to recreate and reload the database, first drop all tables using this:
psql -f 99-drop-schema.sql

Building the project:
————————————————————————————————————————————————
1. Change directory to VehicleTrackerDemo/VehicleTracker
2. Build the war file:
mvn clean package
3. Copy the war file to the JETTY_BASE to deploy it
cp target/VehicleTracker.war jetty/webapps

Running the project:
————————————————————————————————————————————————
1. Change directory to VehicleTrackerDemo/VehicleTracker/jetty, and start jetty:
./start.sh
2. Assuming your jetty server is running on port 8080, in a browser, go to:
http://localhost:8080/VehicleTracker
