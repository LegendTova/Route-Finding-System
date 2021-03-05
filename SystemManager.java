import java.util.*;
public class SystemManager {
	
	final static int INF = 9999;
	
	public static ArrayList<Location> locations = new ArrayList<Location>();
	public static ArrayList<Leg> legs = new ArrayList<Leg>();
	public void printSystemDetails(){
		System.out.println("List of Locations:");
		for(int i = 0;i < locations.size();i++) {
			System.out.println(printLocation(locations.get(i)));
		}
		System.out.println("List of Legs:");
		for(int i = 0;i < legs.size();i++) {
			System.out.println(printLeg(legs.get(i)));
		}
		System.out.println("\n");
	}
	public void addLocation(Location l) {
		locations.add(l);
	}
	public void addLeg(Leg l){
		legs.add(l);
	}
	public static Location findLocation(String location) {
		for(int i = 0;i < locations.size();i++) {
			if(locations.get(i).name.equals(location)) {
				return locations.get(i);
			}
		}
		return null;
	}
	public static Route findCheapestRoute(Location origin, Location destination, String day) {
		double [] [] costPerStep = new double [locations.size()] [locations.size()];
		boolean hasLeg;
		
		for(int i = 0;i < costPerStep.length;i++) {
			hasLeg = false;
			for(int j = 0;j < costPerStep[i].length;j++) {
				if(j == i) {
					costPerStep[i][j] = 0;
				}
				for(int k = 0;k < legs.size();k++) {
					if((legs.get(k).getOrigin()).equals(locations.get(i)) && (legs.get(k).getDestination()).equals(locations.get(j))) {
						costPerStep[i][j] = legs.get(k).getDistance() * legs.get(k).getCost();
						costPerStep[j][i] = legs.get(k).getDistance() * legs.get(k).getCost();
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					costPerStep[i][j] = INF;
					costPerStep[j][i] = INF;
				}
			}
		}
		
		return null;
	}
	public static Route findMinStepsRoute(Location origin, Location destination, String day) {
		return null;
	}
	public static Route findMinKmRoute(Location origin, Location destination, String day) {
		return null;
	}
	public static String printLocation(Location l) {
		return l.name;
	}
	public static String printLeg(Leg l) {
		return printLocation(l.origin) + " to " + printLocation(l.destination) + ". A trip of " + l.distance + "km with a cost of " + l.costPerKm + "$ per km";
	}
	
	// Floyd Warshall Algorithm in Java

	    // Implementing floyd warshall algorithm
	    public void floydWarshall(int graph[][]) {
	    	int V = locations.size();
	    	
	        double matrix[][] = new double[V][V];
	        int i, j, k;

	    for (i = 0; i < V; i++) {
	        for (j = 0; j < V; j++) {
	            matrix[i][j] = graph[i][j];
	        }
	    }
	    // Adding vertices individually
	    for (k = 0; k < V; k++) {
	        for (i = 0; i < V; i++) {
	            for (j = 0; j < V; j++) {
	                if (matrix[i][k] + matrix[k][j] < matrix[i][j])
	                    matrix[i][j] = matrix[i][k] + matrix[k][j];
	            }
	        }
	    }
	  }
}
