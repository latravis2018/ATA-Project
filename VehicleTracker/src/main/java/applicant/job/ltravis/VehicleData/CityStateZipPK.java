package applicant.job.ltravis.VehicleData;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the city_state_zip database table.
 * 
 */
@Embeddable
public class CityStateZipPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="csz_city_id", insertable=false, updatable=false)
	private Integer cszCityId;

	@Column(name="csz_state_id", insertable=false, updatable=false)
	private Integer cszStateId;

	@Column(name="csz_zip_id", insertable=false, updatable=false)
	private Integer cszZipId;

	public CityStateZipPK() {
	}
	public Integer getCszCityId() {
		return this.cszCityId;
	}
	public void setCszCityId(Integer cszCityId) {
		this.cszCityId = cszCityId;
	}
	public Integer getCszStateId() {
		return this.cszStateId;
	}
	public void setCszStateId(Integer cszStateId) {
		this.cszStateId = cszStateId;
	}
	public Integer getCszZipId() {
		return this.cszZipId;
	}
	public void setCszZipId(Integer cszZipId) {
		this.cszZipId = cszZipId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CityStateZipPK)) {
			return false;
		}
		CityStateZipPK castOther = (CityStateZipPK)other;
		return 
			this.cszCityId.equals(castOther.cszCityId)
			&& this.cszStateId.equals(castOther.cszStateId)
			&& this.cszZipId.equals(castOther.cszZipId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cszCityId.hashCode();
		hash = hash * prime + this.cszStateId.hashCode();
		hash = hash * prime + this.cszZipId.hashCode();
		
		return hash;
	}
}