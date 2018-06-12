package applicant.job.ltravis.VehicleTracker.service;

import java.util.List;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import applicant.job.ltravis.VehicleTracker.dao.TripDao;

@Path("/GetTripVehicles") 

public class TripVehiclesService {
	  @GET 
		@Path("{state}/{city}")
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getTripVehicles(@PathParam("state") String state, @PathParam("city") String city) {
		  TripDao trips = new TripDao();
		  List<String[]> list = trips.getTripsPerCity(state,city);
		  Gson gson = new Gson();
		  return gson.toJson(list);
	   }  

}
