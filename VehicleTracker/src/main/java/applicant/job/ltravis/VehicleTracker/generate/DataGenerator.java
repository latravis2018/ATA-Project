package applicant.job.ltravis.VehicleTracker.generate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;

import applicant.job.ltravis.VehicleTracker.domain.City;
import applicant.job.ltravis.VehicleTracker.domain.TripLocation;
import applicant.job.ltravis.VehicleTracker.domain.Vehicle;

public class DataGenerator {

	public static void main(String[] args) {
		
		if (args.length != 5) {
			System.err.println("Usage: DataGenerator <cityStateZipFile> <vehiclesFile> <outputTripsFile> locationsCount");
			System.err.println("Found " + args.length + " arguments");
			for (String s : args) {
				System.err.println(s);
			}
			
			return;
		}
		
		City[] cities = LoadCityCSV(args[1], -1);
		Vehicle[] vehicles = LoadVehicleCSV(args[2], -1);
		int requestedTripLocationCount = Integer.parseInt(args[4]);

		TripLocation[] tripLocations = GenerateLocations(cities, vehicles, requestedTripLocationCount);
		
		SaveTripLocationsCSV(args[3], tripLocations);
	}

	private static int safeParseInt(String input) throws NumberFormatException {
		int output;
		
		if (input.length() == 0 || input.trim().length() == 0) {
			output = 0;
		}
		else {
			output = Integer.parseInt(input);
		}
		
		return output;
	}
	
	private static float safeParseFloat(String input) throws NumberFormatException {
		float output;
		
		if (input.length() == 0 || input.trim().length() == 0) {
			output = 0;
		}
		else {
			output = Float.parseFloat(input);
		}
		
		return output;
	}
	
	public static City[] LoadCityCSV(String csvFile, int limit) {
		City city = null;
		String[] cols = null;
		ArrayDeque<City> ad = new ArrayDeque<City>();

		if (limit == -1) {
			limit = Integer.MAX_VALUE;
		}
		
		int zip;
		float lat;
		float lng;
		
		try {
			CSVLoadFileIterator it = new CSVLoadFileIterator(csvFile);
			while ((cols = it.next()) != null && limit-- > 0) {
				
				zip = safeParseInt(cols[0]);
				lat = safeParseFloat(cols[5]);
				lng = safeParseFloat(cols[6]);

				try {
					city = new City(zip, cols[2], cols[3], lat, lng);
					ad.add(city);
					
				} catch (NumberFormatException e) {
					// Want to be forgiving if there is a bad row 
					e.printStackTrace();
				}
			}
			
			try {
				// Close file at the end
				it.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ad.toArray(new City[0]);
	}
	
	public static Vehicle[] LoadVehicleCSV(String csvFile, int limit) {
		Vehicle vehicle = null;
		String[] cols = null;
		ArrayDeque<Vehicle> ad = new ArrayDeque<Vehicle>();

		if (limit == -1) {
			limit = Integer.MAX_VALUE;
		}
		
		int year;
		
		try {
			CSVLoadFileIterator it = new CSVLoadFileIterator(csvFile);
			while ((cols = it.next()) != null && limit-- > 0) {
				
				year = safeParseInt(cols[6]);

				try {
					vehicle = new Vehicle(cols[2], cols[3], year);
					ad.add(vehicle);
					
				} catch (NumberFormatException e) {
					// Want to be forgiving if there is a bad row 
					e.printStackTrace();
				}
			}
			
			try {
				// Close file at the end
				it.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ad.toArray(new Vehicle[0]);
	}
	
	
	public static void SaveTripLocationsCSV(String csvFile, TripLocation[] tripLocations) {
		String[] cols = new String[9];	// Want 9 columns in the trip locations output file

		try {
			CSVSaveFileIterator it = new CSVSaveFileIterator(csvFile);
			
			for (TripLocation trip : tripLocations) {
				cols[0] = trip.getVehMake();
				cols[1] = trip.getVehModel();
				cols[2] = String.valueOf(trip.getVehYear());
				cols[3] = String.valueOf(trip.getTripLat());
				cols[4] = String.valueOf(trip.getTripLng());
				cols[5] = trip.getCity();
				cols[6] = trip.getState();
				cols[7] = String.valueOf(trip.getZip());
				cols[8] = String.valueOf(trip.getTripGroupNumber());
				
				it.next(cols);
			}
			
			try {
				// Close file at the end
				it.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private static float nextLatLongIncrement(Random r)
	{
		double d = r.nextDouble();
		
		d = ((r.nextDouble() * 2F - 1F) / 100F) * ((r.nextDouble() + 1F) * 2.5F); // 0:1 => -1:1 => -0.01:0.01 => -0.025:0.025
		
		return (float)d;
	}
	
	/**
	 * For a given trip, select random vehicles, cities, and number of locations per trip.
	 * @param cities
	 * @param vehicles
	 * @param numberToGen
	 * @return an array of locations for all trips
	 */
	public static TripLocation[] GenerateLocations(City[] cities, Vehicle[] vehicles, int numberToGen) {
		ArrayDeque<TripLocation> ad = new ArrayDeque<TripLocation>();
		
		int tripGroup = 1;
		int tripCount = 0;
		int locationsPerTrip = 5;
		
		Random r = new Random();
		
		Vehicle vehicle = vehicles[r.nextInt(vehicles.length-1)];
		City city = cities[r.nextInt(cities.length-1)];
		
		// More work can be done to make the trips look more intentional rather than wandering.
		float latitude = city.getLat() + nextLatLongIncrement(r);
		float longitude = city.getLng() + nextLatLongIncrement(r);
				
		for (int locationCount = 1; tripCount++ < numberToGen; locationCount++) {
			
			if (locationCount >= locationsPerTrip) {
				tripGroup++;			// Next trip
				locationsPerTrip = r.nextInt(10) + 2;	// Minimum of 2 locations per trip (2-11)
				locationCount = 0;	// For a new trip, reset location counter
				
				// Each new trip is in a different vehicle and city
				vehicle = vehicles[r.nextInt(vehicles.length-1)];
				city = cities[r.nextInt(cities.length-1)];
			}
			
			latitude = city.getLat() + nextLatLongIncrement(r);
			longitude = city.getLng() + nextLatLongIncrement(r);
			
			ad.add(new TripLocation(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
							latitude, longitude, city.getCity(), city.getState(), city.getZip(), tripGroup));
		}
		
		return ad.toArray(new TripLocation[0]);
	}
}
