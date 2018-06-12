package applicant.job.ltravis.VehicleTracker.domain;

public class TripLocation {
	private String vehMake;
	private String vehModel;
	private int vehYear;
	private float tripLat;
	private float tripLng;
	private String city;
	private String state;
	private int zip;
	private int tripGroupNumber;
	public TripLocation(String vehMake, String vehModel, int vehYear, float tripLat, float tripLng, String city,
			String state, int zip, int tripGroupNumber) {
		super();
		this.vehMake = vehMake;
		this.vehModel = vehModel;
		this.vehYear = vehYear;
		this.tripLat = tripLat;
		this.tripLng = tripLng;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.tripGroupNumber = tripGroupNumber;
	}
	public void setVehMake(String vehMake) {
		this.vehMake = vehMake;
	}
	public void setVehModel(String vehModel) {
		this.vehModel = vehModel;
	}
	public void setVehYear(int vehYear) {
		this.vehYear = vehYear;
	}
	public void setTripLat(float tripLat) {
		this.tripLat = tripLat;
	}
	public void setTripLng(float tripLng) {
		this.tripLng = tripLng;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public void setTripGroupNumber(int tripGroupNumber) {
		this.tripGroupNumber = tripGroupNumber;
	}
	public String getVehMake() {
		return vehMake;
	}
	public String getVehModel() {
		return vehModel;
	}
	public int getVehYear() {
		return vehYear;
	}
	public float getTripLat() {
		return tripLat;
	}
	public float getTripLng() {
		return tripLng;
	}
	public int getTripGroupNumber() {
		return tripGroupNumber;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getZip() {
		return zip;
	}
}
