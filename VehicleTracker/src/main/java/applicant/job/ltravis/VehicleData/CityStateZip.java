package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the city_state_zip database table.
 * 
 */
@Entity
@Table(name="city_state_zip")
@NamedQuery(name="CityStateZip.findAll", query="SELECT c FROM CityStateZip c")
public class CityStateZip implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CityStateZipPK id;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="csz_city_id")
	private City city;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="csz_state_id")
	private State state;

	//bi-directional many-to-one association to Zip
	@ManyToOne
	@JoinColumn(name="csz_zip_id")
	private Zip zip;

	public CityStateZip() {
	}

	public CityStateZipPK getId() {
		return this.id;
	}

	public void setId(CityStateZipPK id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Zip getZip() {
		return this.zip;
	}

	public void setZip(Zip zip) {
		this.zip = zip;
	}

}