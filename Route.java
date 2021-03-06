
public class Route {
	double cost = 0;
	double distance = 0;
	double steps = 0;
	
	Route(double c, double d, double s){
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
		return "sadsad";
	}
}
