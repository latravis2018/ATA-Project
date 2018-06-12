package applicant.job.ltravis.VehicleTracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import applicant.job.ltravis.VehicleData.City;

public class JPACities {

    private static EntityManagerFactory factory;
	
	public static List<City> getAllCities() {
        factory = Persistence.createEntityManagerFactory("VehicleData");
        EntityManager em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT c FROM City c");
        @SuppressWarnings("unchecked")
		List<City> cityList = q.getResultList();
        for (City city : cityList) {
             System.out.println(city.getCityName());
        }
        System.out.println("Size: " + cityList.size());
		return cityList;
	}
	
}