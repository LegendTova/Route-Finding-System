import java.util.*;
public class Leg {
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
	}
	
	public Location getOrigin() {
		return origin;
	}
	
	public Location getDestination() {
		return destination;
	}
	
	public String getDays() {
		return daysAvailable;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getCost() {
		return costPerKm;
	}
}
