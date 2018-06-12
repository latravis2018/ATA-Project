package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veh_make database table.
 * 
 */
@Entity
@Table(name="veh_make")
@NamedQuery(name="VehMake.findAll", query="SELECT v FROM VehMake v")
public class VehMake implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vmk_id")
	private Integer vmkId;

	@Column(name="vmk_name")
	private String vmkName;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="vehMake")
	private List<Vehicle> vehicles;

	public VehMake() {
	}

	public Integer getVmkId() {
		return this.vmkId;
	}

	public void setVmkId(Integer vmkId) {
		this.vmkId = vmkId;
	}

	public String getVmkName() {
		return this.vmkName;
	}

	public void setVmkName(String vmkName) {
		this.vmkName = vmkName;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setVehMake(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setVehMake(null);

		return vehicle;
	}

}