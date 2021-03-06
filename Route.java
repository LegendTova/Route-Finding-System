
public class Route {
	Location origin;
	Location destination;
	double cost = 0;
	double distance = 0;
	double steps = 0;

	
	Route(Location o, Location des, double c, double d, double s){
		cost = c;
		distance = d;
		steps = s;
	}
	
	public double totalCost(){
		return cost;
		
	}
	
	public double totalDistance(){
		return distance;
		
	}
	
	public double totalSteps(){
		return steps;
		
	}
	
	public void addLeg(Leg added){
		
	}
	public String toString() {
		String legs = "";
		for(int i = 0;i < (int) steps;i++) {
			legs += "Origin: " + origin + "Destination: " + destination + "\n";
		}
		return ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
				"Steps: " + steps + "Distance: " + distance + "Cost: " + cost
				+ "Route Start:"
				+ legs
				+ "Route End."
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	}
}
