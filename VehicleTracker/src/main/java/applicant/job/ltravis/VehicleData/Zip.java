package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zip database table.
 * 
 */
@Entity
@NamedQuery(name="Zip.findAll", query="SELECT z FROM Zip z")
public class Zip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="zip_id")
	private Integer zipId;

	@Column(name="zip_code")
	private Integer zipCode;

	//bi-directional many-to-one association to CityStateZip
	@OneToMany(mappedBy="zip")
	private List<CityStateZip> cityStateZips;

	public Zip() {
	}

	public Integer getZipId() {
		return this.zipId;
	}

	public void setZipId(Integer zipId) {
		this.zipId = zipId;
	}

	public Integer getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public List<CityStateZip> getCityStateZips() {
		return this.cityStateZips;
	}

	public void setCityStateZips(List<CityStateZip> cityStateZips) {
		this.cityStateZips = cityStateZips;
	}

	public CityStateZip addCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().add(cityStateZip);
		cityStateZip.setZip(this);

		return cityStateZip;
	}

	public CityStateZip removeCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().remove(cityStateZip);
		cityStateZip.setZip(null);

		return cityStateZip;
	}

}