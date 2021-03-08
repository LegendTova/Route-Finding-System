import java.util.ArrayList;

public class Route {
	//instance variables
	Location origin;
	Location destination;
	double cost = 0;
	double distance = 0;
	double steps = 0;
	public static ArrayList<Leg> listOfLegs = new ArrayList<Leg>();

	
	Route(Location o, Location des, double c, double d, double s){
		origin = o;
		destination = des;
		cost = c;
		distance = d;
		steps = s;
	}//Route constructor
	
	public double totalCost(){
		return cost;
	}//totalCost
	
	public double totalDistance(){
		return distance;
	}//totalDistance
	
	public double totalSteps(){
		return steps;
	}//totalSteps
	public Leg getLeg(int i) {
		return listOfLegs.get(i);
	}//getLeg
	
	public void addLeg(Leg added){
		steps++;
		distance += added.getDistance();
		cost += added.getCost();
		listOfLegs.add(added);
	}//addLeg
	public String toString() {
		String legs = "";
		/*for(int i = 0;i < (int) steps;i++) {
			legs += "Origin: " + listOfLegs.get(i).getOrigin() + "Destination: " + listOfLegs.get(i).getDestination() + "\n";
		}*/
		return ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
				"Steps: " + steps + " Distance: " + distance + " Cost: " + cost
				+ "\nRoute Start:"
				+ legs
				+ "\nRoute End."
				+ "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	}//toString
}//Route
