package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the trip_location database table.
 * 
 */
@Entity
@Table(name="trip_location")
@NamedQuery(name="TripLocation.findAll", query="SELECT t FROM TripLocation t")
public class TripLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="trip_loc_id")
	private Integer tripLocId;

	@Column(name="trip_loc_lat")
	private float tripLocLat;

	@Column(name="trip_loc_long")
	private float tripLocLong;

	@Column(name="trip_loc_timestamp")
	private Timestamp tripLocTimestamp;

	//bi-directional many-to-one association to Trip
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;

	public TripLocation() {
	}

	public Integer getTripLocId() {
		return this.tripLocId;
	}

	public void setTripLocId(Integer tripLocId) {
		this.tripLocId = tripLocId;
	}

	public float getTripLocLat() {
		return this.tripLocLat;
	}

	public void setTripLocLat(float tripLocLat) {
		this.tripLocLat = tripLocLat;
	}

	public float getTripLocLong() {
		return this.tripLocLong;
	}

	public void setTripLocLong(float tripLocLong) {
		this.tripLocLong = tripLocLong;
	}

	public Timestamp getTripLocTimestamp() {
		return this.tripLocTimestamp;
	}

	public void setTripLocTimestamp(Timestamp tripLocTimestamp) {
		this.tripLocTimestamp = tripLocTimestamp;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}