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
		daysAvailable.add(days.split());
	}
}
