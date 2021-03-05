import java.util.*;
public class Leg {
	Location origin;
	Location destination;
	ArrayList<String> daysAvailable = new ArrayList<String>();
	int distance;
	double costPerKm;
	
	public Leg(Location o, Location d, int dKm, String days, double costKm) {
		origin = o;
		destination = d;
		distance = dKm;
		costPerKm = costKm;
		daysAvailable.addAll(Arrays.asList(days.split("")));
	}
	
	public Location getOrigin() {
		return origin;
	}
	
	public Location getDestination() {
		return destination;
	}
	
	public ArrayList<String> getDays() {
		return daysAvailable;
	}
	
	public double getCost() {
		return costPerKm;
	}
}
