
import java.util.ArrayList;

public abstract class Location{
	String name = "";
	ArrayList connectingLegs;
	final static int INF = 9999;
	
	Location(String n){
		name = n;
	}

	public void addConnection (Leg connection) {
		connectingLegs.add(connection);
	}
	
	public String cheapestRoute(Location loc, String day) {
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		boolean hasLeg;
		
		for(int i = 0;i < costPerStep.length;i++) {
			hasLeg = false;
			for(int j = 0;j < costPerStep[i].length;j++) {
				if(j == i) {
					costPerStep[i][j] = 0;
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						costPerStep[i][j] = SystemManager.legs.get(k).getDistance() * SystemManager.legs.get(k).getCost();
						costPerStep[j][i] = SystemManager.legs.get(k).getDistance() * SystemManager.legs.get(k).getCost();
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
	
	public String minStepsRouteTo(Location loc, String day) {
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		boolean hasLeg;
		
		for(int i = 0;i < minStep.length;i++) {
			hasLeg = false;
			for(int j = 0;j < minStep[i].length;j++) {
				if(j == i) {
					minStep[i][j] = 0;
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						minStep[i][j] = 1;
						minStep[j][i] = 1;
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					minStep[i][j] = INF;
					minStep[j][i] = INF;
				}
			}
		}
		return null;
	}
	
	public String shortestKmRouteTo(Location loc, String day) {
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		boolean hasLeg;
		
		for(int i = 0;i < shortestDistance.length;i++) {
			hasLeg = false;
			for(int j = 0;j < shortestDistance[i].length;j++) {
				if(j == i) {
					shortestDistance[i][j] = 0;
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						shortestDistance[i][j] = SystemManager.legs.get(k).getDistance();
						shortestDistance[j][i] = SystemManager.legs.get(k).getDistance();
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					shortestDistance[i][j] = INF;
					shortestDistance[j][i] = INF;
				}
			}
		}
		return null;
	}
	
	// Floyd Warshall Algorithm in Java

    // Implementing floyd warshall algorithm
    public void floydWarshall(int graph[][]) {
    	int V = SystemManager.locations.size();
    	
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
