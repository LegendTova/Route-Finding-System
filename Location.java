
import java.util.ArrayList;

public abstract class Location{
	String name = "";
	ArrayList connectingLegs;
	
	
	Location(String n){
		name = n;
	}

	public void addConnection (Leg connection) {
		connectingLegs.add(connection);
	}
	
	public String cheapestRoute(Location loc, String day) {
		
		return null;
	}
	
	public String minStepsRouteTo(Location loc, String day) {
		
		return null;
	}
	
	public String shortestKmRouteTo(Location loc, String day) {
		
		return null;
	}
}
