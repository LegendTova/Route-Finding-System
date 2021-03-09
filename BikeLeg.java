
public class BikeLeg extends Leg{
	//instance variables
	Location origin;
	Location destination;
	int distance;
	String days;
	public BikeLeg(Location o, Location d, int dKm, String days) {
		super(o, d, dKm, days, 1);
	}//BikeLeg constructor
}//BikeLeg
