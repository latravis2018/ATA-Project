package applicant.job.ltravis.VehicleTracker.service;

import java.util.List;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import applicant.job.ltravis.VehicleTracker.dao.TripDao;

@Path("/GetTripLocations") 

public class TripLocationsService {
	  @GET 
		@Path("{state}/{city}/{make}/{model}/{year}")
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getTripVehicles(@PathParam("state") String state, @PathParam("city") String city,
			   @PathParam("make") String make, @PathParam("model") String model, @PathParam("year") String year) {
		  TripDao trips = new TripDao();
		  List<String[]> list = trips.getTripsLocations(state,city,make,model,year);
		  Gson gson = new Gson();
		  return gson.toJson(list);
	   }  
}
