import java.util.ArrayList;

public abstract class Location{
	//instance variables
	String name = "";
	ArrayList connectingLegs = new ArrayList();
	public static ArrayList reconstruction;
	final static int INF = 9999;
	
	Location(String n){
		name = n;
	}//Location constructor

	public void addConnection (Leg connection) {
		connectingLegs.add(connection);
	}//addConnection
	
	public void getConnections() {
        for(int i = 0;i < connectingLegs.size();i++) {
            System.out.println(connectingLegs.get(i) + " connection");
        }
    }
	
	public Route cheapestRoute(Location loc, String day) {
		Route cheap =  new Route(this, loc, 0, 0, 0);
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		int [] [] path = new int [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()]; 
		
		makeArrays(costPerStep, minStep, shortestDistance, day);
		
		floydWarshall(costPerStep, shortestDistance, minStep, path);

		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		path(start, end, path);
		
		if(costPerStep[start][end] != INF) {
			cheap = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return cheap;
		}//if
		
		return cheap;
	}//cheapestRoute
	
	public Route minStepsRouteTo(Location loc, String day) {
		Route minStepRoute = new Route(this, loc, 0, 0, 0);
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		int [] [] path = new int [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()];
		
		makeArrays(costPerStep, minStep, shortestDistance, day);
		
		floydWarshall(minStep, shortestDistance, costPerStep, path);
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		path(start, end, path);
		
		if(minStep[start][end] != INF) {
			minStepRoute = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return minStepRoute;
		}//if
		
		return minStepRoute;
	}//minStepsRouteTo
	
	public Route shortestKmRouteTo(Location loc, String day) {
		Route shortRoute = new Route(this, loc, 0, 0, 0);
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		int [] [] path = new int [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()];
		
		makeArrays(costPerStep, minStep, shortestDistance, day); 
		
		floydWarshall(shortestDistance, costPerStep, minStep, path);
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		path(start, end, path);
		
		if(costPerStep[start][end] != INF) {
			shortRoute = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return shortRoute;
		}//if
		
		return shortRoute;
	}//shortestKmRouteTo
	
	private void makeArrays(double [] [] costPerStep, double [] [] minStep, double [] [] shortestDistance, String day) {
		setInf(costPerStep);
		setInf(minStep);
		setInf(shortestDistance);
		
		for(int i = 0;i < costPerStep.length;i++) {
			for(int j = 0;j < costPerStep[i].length;j++) {
				if(j == i) {
					costPerStep[i][j] = 0;
				}//if
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j)) && SystemManager.legs.get(k).getDays().contains(day)) {
						costPerStep[i][j] = SystemManager.legs.get(k).getDistance() * SystemManager.legs.get(k).getCost();
					}//if
				}//second inner for loop
			}//inner for loop	
		}//outer for loop
		
		
		
		for(int i = 0;i < minStep.length;i++) {
			for(int j = 0;j < minStep[i].length;j++) {
				if(j == i) {
					minStep[i][j] = 0;
				}//if
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j)) && SystemManager.legs.get(k).getDays().contains(day)) {
						minStep[i][j] = 1;
					}//if
				}//second inner for loop
			}//inner for loop
		}//outer for loop

		
		for(int i = 0;i < shortestDistance.length;i++) {
			for(int j = 0;j < shortestDistance[i].length;j++) {
				if(j == i) {
					shortestDistance[i][j] = 0;
				}//if
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j)) && SystemManager.legs.get(k).getDays().contains(day)) {
						shortestDistance[i][j] = SystemManager.legs.get(k).getDistance();

					}//if
				}//second inner for loop	
			}//inner for loop
		}//outer for loop
		
		/*for(int i = 0;i < days.length;i++) {
			for(int j = 0;j < days.length;j++) {
				days[i][j] = "";
			}//inner for loop
		}//outer for loop
		*/
		
		/*for(int i = 0;i < days.length;i++) {
			for(int j = 0;j < days[i].length;j++) {
				
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						days[i][j] = SystemManager.legs.get(k).getDays();
						days[j][i] = SystemManager.legs.get(k).getDays();
					}//if
				}//second inner for loop	
			}//inner for loop
		}//outer for loop*/
		
	}//makeArrays
	
	public void setInf(double [][] arr) {
		for(int i = 0;i < arr.length;i++) {
			for(int j = 0;j < arr[i].length;j++) {
				arr[i][j] = INF;
			}//inner for loop
		}//outer for loop
	}//setInf
	
	// Floyd Warshall Algorithm in Java

	// Implementing floyd warshall algorithm
	public void floydWarshall(double matrix [] [], double [] [] arrOne, double [] [] arrTwo, int [] [] path) {
		int V = SystemManager.locations.size();
    	
		int i, j, k;

        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                path[i][j] = j;
            }
        }
        
        for (i = 0; i < V; i++) {
            path[i][i] = i;
        }
        
	// Adding vertices individually
		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					
					if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						arrOne[i][j] = arrOne[i][k] + arrOne[k][j];
						arrTwo[i][j] = arrTwo[i][k] + arrTwo[k][j];
						path[i][j] = path[i][k];
					}//if	
				}//second inner for loop
			}//inner for loop
		}//outer for loop
	}//floydWarshall
	
	 public void path(int u, int v, int [] [] next) {
		    
			reconstruction = new ArrayList();
			
			if(next[u][v] == INF) {
			
			}else{
				while(u != v) {
			        u = next[u][v];
			        reconstruction.add(u);
			    }
			}
	 }
	
	public String toString() {
		return name;
	}//toString

}//Location
