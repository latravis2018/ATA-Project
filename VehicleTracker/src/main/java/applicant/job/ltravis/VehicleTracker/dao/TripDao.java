package applicant.job.ltravis.VehicleTracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.postgresql.ds.PGConnectionPoolDataSource;

import applicant.job.ltravis.VehicleTracker.domain.State;

public class TripDao {

	private static final String GET_TRIP_STATES_QUERY =
			"select DISTINCT s.state_name from trip t, state s, city c"
			+ " where t.trip_start_state_id = s.state_id and t.trip_start_city_id = c.city_id"
			+ " order by s.state_name";
	private static final String GET_TRIP_CITIES_QUERY =
			"select DISTINCT c.city_name"
			+ " from trip t, state s, city c"
			+ " where s.state_name = ? and s.state_id = t.trip_start_state_id and t.trip_start_city_id = c.city_id"
			+ " order by c.city_name";

	private static final String GET_TRIPS_PER_CITY_QUERY = 
	"select DISTINCT vmk.vmk_name, vmd.vmd_name, v.veh_year"
	+ " from trip t, trip_location tl, vehicle v, veh_make vmk, veh_model vmd, state s, city c"
	+ " where s.state_name = ? and s.state_id = t.trip_start_state_id and c.city_name = ? and"
	+ " c.city_id = t.trip_start_city_id and t.trip_veh_id = v.veh_id and v.vmk_id = vmk.vmk_id and v.vmd_id = vmd.vmd_id"
	+ " order by vmk.vmk_name, vmd.vmd_name, v.veh_year";

	private static final String GET_TRIPS_LOCATIONS_QUERY = 
	"select tl.trip_loc_timestamp, tl.trip_loc_lat, tl.trip_loc_long"
	+ " from trip t, trip_location tl, vehicle v, veh_make vmk, veh_model vmd, state s, city c"
	+ " where s.state_name = ? and s.state_id = t.trip_start_state_id and c.city_name = ? and"
	+ " c.city_id = t.trip_start_city_id and t.trip_veh_id = v.veh_id and v.vmk_id = vmk.vmk_id and"
	+ " vmk.vmk_name = ? and v.vmd_id = vmd.vmd_id and vmd.vmd_name = ? and v.veh_year = CAST( ? AS REAL) and"
	+ " t.trip_id = tl.trip_id"
	+ " order by tl.trip_loc_timestamp, tl.trip_loc_lat, tl.trip_loc_long";
	
	
	public TripDao()
	{
	}
	
	public List<String[]> getTripStates() {
		
		return getListOfNames(GET_TRIP_STATES_QUERY);
	}
	
	public List<String[]> getTripCities(String stateName) {
		
		return getListOfNames(GET_TRIP_CITIES_QUERY, stateName);
	}
	
	public List<String[]> getTripsPerCity(String stateName, String cityName) {
		
		return getListOfNames(GET_TRIPS_PER_CITY_QUERY, stateName, cityName);
	}
	
	public List<String[]> getTripsLocations(String stateName, String cityName, String vehMake, String vehModel, String vehYear) {
		
		return getListOfNames(GET_TRIPS_LOCATIONS_QUERY, stateName, cityName, vehMake, vehModel, vehYear);
	}
	
	private List<String[]> getListOfNames(String query, String ... params) {
		ArrayList<String[]> nameListList = new ArrayList<String[]>();
		
		System.out.println("Running query: " + query);
		System.out.println("with parameters: " + Arrays.toString(params));
		
		PGConnectionPoolDataSource datasource = null;
		
		try {
			Context envCtx = (Context) new InitialContext();
			// Should not have to cast to DB vendor's class.
			// Code should be DB vendor neutral. Can maybe wrap their class as a work around.
			// Postgres didn't implement their DataSource as implementing that interface, or I wouldn't
			// have gotten the cast exception by casting it to DataSource.
			datasource = (PGConnectionPoolDataSource) envCtx.lookup("java:comp/env/jdbc/VehicleTrackerDS");
			
		} catch (NamingException e) {
			// Log the exception and rethrow another exception wrapping the SQLException so rest service can respond.
			e.printStackTrace();
		}
		
		try {
			Connection conn = datasource.getConnection();
						
			ResultSet r;
			
			if (params == null || params.length == 0) {
				Statement s = conn.createStatement();
				r = s.executeQuery(query);
			}
			else {
				PreparedStatement p = conn.prepareStatement(query);
				for (int i = 0; i < params.length; i++) {
					p.setString(i+1, params[i]);
				}
				r = p.executeQuery();
			}
			 
			ResultSetMetaData rsmd = r.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			
			System.out.println("columnsNumber=" + columnsNumber);
					
			while (r.next()) {
				String[] nameList = new String[columnsNumber];
				for (int i = 0; i < columnsNumber; i++) {
					nameList[i] = r.getString(i+1);
				}
				nameListList.add(nameList);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// Log the exception and rethrow another exception wrapping the SQLException so rest service can respond.
			e.printStackTrace();
		}
		
		System.out.println("returning list of size: " + nameListList.size());
		
		return nameListList;
	}

}
