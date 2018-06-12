package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trip database table.
 * 
 */
@Entity
@NamedQuery(name="Trip.findAll", query="SELECT t FROM Trip t")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="trip_id")
	private Integer tripId;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="trip_veh_id")
	private Vehicle vehicle;

	//bi-directional many-to-one association to TripLocation
	@OneToMany(mappedBy="trip")
	private List<TripLocation> tripLocations;

	public Trip() {
	}

	public Integer getTripId() {
		return this.tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<TripLocation> getTripLocations() {
		return this.tripLocations;
	}

	public void setTripLocations(List<TripLocation> tripLocations) {
		this.tripLocations = tripLocations;
	}

	public TripLocation addTripLocation(TripLocation tripLocation) {
		getTripLocations().add(tripLocation);
		tripLocation.setTrip(this);

		return tripLocation;
	}

	public TripLocation removeTripLocation(TripLocation tripLocation) {
		getTripLocations().remove(tripLocation);
		tripLocation.setTrip(null);

		return tripLocation;
	}

}