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
}//Leg
