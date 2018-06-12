package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city_location database table.
 * 
 */
@Entity
@Table(name="city_location")
@NamedQuery(name="CityLocation.findAll", query="SELECT c FROM CityLocation c")
public class CityLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_loc_id")
	private Integer cityLocId;

	@Column(name="city_loc_lat")
	private float cityLocLat;

	@Column(name="city_loc_long")
	private float cityLocLong;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="cityLocation")
	private List<City> cities;

	public CityLocation() {
	}

	public Integer getCityLocId() {
		return this.cityLocId;
	}

	public void setCityLocId(Integer cityLocId) {
		this.cityLocId = cityLocId;
	}

	public float getCityLocLat() {
		return this.cityLocLat;
	}

	public void setCityLocLat(float cityLocLat) {
		this.cityLocLat = cityLocLat;
	}

	public float getCityLocLong() {
		return this.cityLocLong;
	}

	public void setCityLocLong(float cityLocLong) {
		this.cityLocLong = cityLocLong;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCityLocation(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCityLocation(null);

		return city;
	}

}