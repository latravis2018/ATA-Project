package applicant.job.ltravis.VehicleTracker.service;

import java.util.List;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import applicant.job.ltravis.VehicleTracker.dao.TripDao;

@Path("/GetTripCities") 

public class TripCitiesService {
	@GET
	@Path("{state}")
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getTripCities(@PathParam("state") String state) {
		  TripDao trips = new TripDao();
		  List<String[]> list = trips.getTripCities(state);
		  Gson gson = new Gson();
		  return gson.toJson(list);
	   }  

}
