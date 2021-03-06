import java.util.ArrayList;

public class Route {
	Location origin;
	Location destination;
	double cost = 0;
	double distance = 0;
	double steps = 0;
	public static ArrayList<Leg> listOfLegs = new ArrayList<Leg>();

	
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
	public Leg getLeg(int i) {
		return listOfLegs.get(i);
	}
	
	public void addLeg(Leg added){
		steps++;
		distance += added.getDistance();
		cost += added.getCost();
		listOfLegs.add(added);
	}
	public String toString() {
		String legs = "";
		for(int i = 0;i < (int) steps;i++) {
			legs += "Origin: " + listOfLegs.get(i).getOrigin() + "Destination: " + listOfLegs.get(i).getDestination() + "\n";
		}
		return ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
				"Steps: " + steps + "Distance: " + distance + "Cost: " + cost
				+ "Route Start:"
				+ legs
				+ "Route End."
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	}
}
