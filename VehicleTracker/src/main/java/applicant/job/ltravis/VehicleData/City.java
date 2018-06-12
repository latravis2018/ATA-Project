package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id")
	private Integer cityId;

	@Column(name="city_name")
	private String cityName;

	//bi-directional many-to-one association to CityLocation
	@ManyToOne
	@JoinColumn(name="city_loc_id")
	private CityLocation cityLocation;

	//bi-directional many-to-one association to CityStateZip
	@OneToMany(mappedBy="city")
	private List<CityStateZip> cityStateZips;

	public City() {
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CityLocation getCityLocation() {
		return this.cityLocation;
	}

	public void setCityLocation(CityLocation cityLocation) {
		this.cityLocation = cityLocation;
	}

	public List<CityStateZip> getCityStateZips() {
		return this.cityStateZips;
	}

	public void setCityStateZips(List<CityStateZip> cityStateZips) {
		this.cityStateZips = cityStateZips;
	}

	public CityStateZip addCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().add(cityStateZip);
		cityStateZip.setCity(this);

		return cityStateZip;
	}

	public CityStateZip removeCityStateZip(CityStateZip cityStateZip) {
		getCityStateZips().remove(cityStateZip);
		cityStateZip.setCity(null);

		return cityStateZip;
	}

}