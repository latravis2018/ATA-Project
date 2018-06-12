package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="state_id")
	private Integer stateId;

	@Column(name="state_name")
	private String stateName;

	//bi-directional many-to-one association to CityStateZip
	@OneToMany(mappedBy="state")
	private List<CityStateZip> cityStateZips;

	public State() {
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<CityStateZip> getCityStateZips() {
		return this.cityStateZips;
	}

	public void setCityStateZips(List<CityStateZip> cityStateZips) {
		this.cityStateZips = cityStateZips;
	}

	public CityStateZip addCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().add(cityStateZip);
		cityStateZip.setState(this);

		return cityStateZip;
	}

	public CityStateZip removeCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().remove(cityStateZip);
		cityStateZip.setState(null);

		return cityStateZip;
	}

}