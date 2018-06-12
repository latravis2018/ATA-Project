package applicant.job.ltravis.VehicleTracker.service;

import java.util.List;

import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import applicant.job.ltravis.VehicleTracker.dao.TripDao;

@Path("/GetTripStates") 

public class TripStatesService {
	  @GET 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getTripStates() {
		  TripDao trips = new TripDao();
		  List<String[]> list = trips.getTripStates();
		  Gson gson = new Gson();
		  return gson.toJson(list);
	   }  

}
