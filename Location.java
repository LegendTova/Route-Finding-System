
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
	
	public Route cheapestRoute(Location loc, String day) {
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()]; 
		
		makeArrays(costPerStep, minStep, shortestDistance, days);
		
		floydWarshall(costPerStep, shortestDistance, minStep, days, day);
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		if(costPerStep[start][end] != INF) {
			Route cheap = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return cheap;
		}
		
		return null;
	}
	
	public Route minStepsRouteTo(Location loc, String day) {
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()];
	
		makeArrays(costPerStep, minStep, shortestDistance, days);
		
		floydWarshall(minStep, shortestDistance, costPerStep, days, day);
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		if(minStep[start][end] != INF) {
			Route minStepRoute = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return minStepRoute;
		}
		
		return null;
	}
	
	public Route shortestKmRouteTo(Location loc, String day) {
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()];
		
		makeArrays(costPerStep, minStep, shortestDistance, days); 
		
		floydWarshall(shortestDistance, costPerStep, minStep, days, day);
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		if(costPerStep[start][end] != INF) {
			Route shortRoute = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return shortRoute;
		}
		
		return null;
	}
	
	private void makeArrays(double [] [] costPerStep, double [] [] minStep, double [] [] shortestDistance, String [] [] days) {
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
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					costPerStep[i][j] = INF;
				}
			}
			
			
			
		}
		
		
		
		for(int i = 0;i < minStep.length;i++) {
			hasLeg = false;
			for(int j = 0;j < minStep[i].length;j++) {
				if(j == i) {
					minStep[i][j] = 0;
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						minStep[i][j] = 1;
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					minStep[i][j] = INF;
				}
			}
		}
		
		
		
		for(int i = 0;i < shortestDistance.length;i++) {
			hasLeg = false;
			for(int j = 0;j < shortestDistance[i].length;j++) {
				if(j == i) {
					shortestDistance[i][j] = 0;
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						shortestDistance[i][j] = SystemManager.legs.get(k).getDistance();
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					shortestDistance[i][j] = INF;
				}
			}
		}
		
		
		for(int i = 0;i < days.length;i++) {
			hasLeg = false;
			for(int j = 0;j < days[i].length;j++) {
				if(j == i) {
					days[i][j] = "";
				}
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						days[i][j] = SystemManager.legs.get(k).getDays();
						hasLeg = true;
					}
				}
				
				if(hasLeg == false) {
					days[i][j] = "";
				}
			}
		}
		
	}
	
	
	// Floyd Warshall Algorithm in Java

    // Implementing floyd warshall algorithm
    public void floydWarshall(double matrix [] [], double [] [] arrOne, double [] [] arrTwo, String days[] [], String day) {
    	int V = SystemManager.locations.size();
    	
        //double matrix[][] = new double[V][V];
        int i, j, k;

    /*for (i = 0; i < V; i++) {
        for (j = 0; j < V; j++) {
            matrix[i][j] = graph[i][j];
        }
    }*/
    // Adding vertices individually
    for (k = 0; k < V; k++) {
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (matrix[i][k] + matrix[k][j] < matrix[i][j] && days[i][k].contains(day) && days[k][j].contains(day) && days[i][j].contains(day)) {
                    matrix[i][j] = matrix[i][k] + matrix[k][j];
                	arrOne[i][j] = arrOne[i][k] + arrOne[k][j];
                	arrTwo[i][j] = arrTwo[i][k] + arrTwo[k][j];
                }	
            }
        }
    }
  }
}
