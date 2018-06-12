package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="veh_id")
	private Integer vehId;

	@Column(name="veh_year")
	private Integer vehYear;

	//bi-directional many-to-one association to Trip
	@OneToMany(mappedBy="vehicle")
	private List<Trip> trips;

	//bi-directional many-to-one association to VehMake
	@ManyToOne
	@JoinColumn(name="vmk_id")
	private VehMake vehMake;

	//bi-directional many-to-one association to VehModel
	@ManyToOne
	@JoinColumn(name="vmd_id")
	private VehModel vehModel;

	public Vehicle() {
	}

	public Integer getVehId() {
		return this.vehId;
	}

	public void setVehId(Integer vehId) {
		this.vehId = vehId;
	}

	public Integer getVehYear() {
		return this.vehYear;
	}

	public void setVehYear(Integer vehYear) {
		this.vehYear = vehYear;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setVehicle(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setVehicle(null);

		return trip;
	}

	public VehMake getVehMake() {
		return this.vehMake;
	}

	public void setVehMake(VehMake vehMake) {
		this.vehMake = vehMake;
	}

	public VehModel getVehModel() {
		return this.vehModel;
	}

	public void setVehModel(VehModel vehModel) {
		this.vehModel = vehModel;
	}

}