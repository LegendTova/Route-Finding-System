
public class TruckLeg extends Leg{
	//instance variables
	Location origin;
	Location destination;
	int distance;
	String days;
	public TruckLeg(Location o, Location d, int dKm, String days) {
		super(o, d, dKm, days, 0.58);
	}//TruckLeg constructor
}//TruckLeg
