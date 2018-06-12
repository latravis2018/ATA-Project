package applicant.job.ltravis.VehicleTracker.domain;

import java.util.ArrayList;

public class City {
	private int zip;
	private String city;
	private String state;
	private float lat;
	private float lng;
	
	private ArrayList<TripLocation> locations;
	
	public City(int zip, String city, String state, float lat, float lng) {
		super();
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lng = lng;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public int getZip() {
		return zip;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public float getLat() {
		return lat;
	}
	public float getLng() {
		return lng;
	}
	
}
