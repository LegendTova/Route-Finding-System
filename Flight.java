
public class Flight extends Leg{
	Location origin;
	Location destination;
	int distance;
	String days;
	public Flight(Location o, Location d, int dKm, String days) {
		super(o, d, dKm, days, 0.23);
	}
}
