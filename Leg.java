import java.util.*;
public class Leg {
	//instance variables
	Location origin;
	Location destination;
	String daysAvailable = "";
	int distance;
	double costPerKm;
	
	public Leg(Location o, Location d, int dKm, String days, double costKm) {
		origin = o;
		destination = d;
		distance = dKm;
		costPerKm = costKm;
		daysAvailable = days;
		
		if((origin.type().equals("Airport") && destination.type().equals("BikeDeliveryLocation"))) {
			System.out.println("Airports Can't Go To Bike Delivery Stations");
			
			System.exit(0);
		}else if((origin.type().equals("BikeDeliveryLocation") && destination.type().equals("Airport"))) {
			System.out.println("A Bike Delivery Locations Can't Go To Airports");
			
			System.exit(0);
		}
		
		origin.addConnection(this);
	}//Leg constructor
	
	public Location getOrigin() {
		return origin;
	}//getOrigin
	
	public Location getDestination() {
		return destination;
	}//getDestination
	
	public String getDays() {
		return daysAvailable;
	}//getDays
	
	public double getDistance() {
		return distance;
	}//getDistance
	
	public double getCost() {
		return costPerKm;
	}//getCost
	
	public String toString() {
        return "origin: " + origin.toString() + " destination: " + destination.toString() + "\ndistance: " + distance + "\ncost per km: " + costPerKm;
	}

}//Leg
