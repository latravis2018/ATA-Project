package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veh_model database table.
 * 
 */
@Entity
@Table(name="veh_model")
@NamedQuery(name="VehModel.findAll", query="SELECT v FROM VehModel v")
public class VehModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vmd_id")
	private Integer vmdId;

	@Column(name="vmd_name")
	private String vmdName;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="vehModel")
	private List<Vehicle> vehicles;

	public VehModel() {
	}

	public Integer getVmdId() {
		return this.vmdId;
	}

	public void setVmdId(Integer vmdId) {
		this.vmdId = vmdId;
	}

	public String getVmdName() {
		return this.vmdName;
	}

	public void setVmdName(String vmdName) {
		this.vmdName = vmdName;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setVehModel(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setVehModel(null);

		return vehicle;
	}

}