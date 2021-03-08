import java.util.ArrayList;

public abstract class Location{
	//instance variables
	String name = "";
	ArrayList connectingLegs;
	final static int INF = 9999;
	
	Location(String n){
		name = n;
	}//Location constructor

	public void addConnection (Leg connection) {
		connectingLegs.add(connection);
	}//addConnection
	
	public Route cheapestRoute(Location loc, String day) {
		double [] [] costPerStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] shortestDistance = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		double [] [] minStep = new double [SystemManager.locations.size()] [SystemManager.locations.size()];
		String [] [] days = new String[SystemManager.locations.size()][SystemManager.locations.size()]; 
		
		makeArrays(costPerStep, minStep, shortestDistance, days);
	
		for(int i = 0;i < costPerStep.length;i++) {
			for(int j = 0;j < costPerStep.length;j++) {
				System.out.print(costPerStep[i][j] + ", ");
			}//inner for
			System.out.println();
		}//outer for
		
		floydWarshall(costPerStep, shortestDistance, minStep, days, day);
	
		System.out.println();
		System.out.println();
		
		for(int i = 0;i < costPerStep.length;i++) {
			for(int j = 0;j < costPerStep.length;j++) {
				System.out.print(costPerStep[i][j] + ", ");
			}//inner for
			System.out.println();
		}//outer for
		
		int start = SystemManager.locations.indexOf(this);
		int end = SystemManager.locations.indexOf(loc);
		
		
		if(costPerStep[start][end] != INF) {
			Route cheap = new Route(this, loc, costPerStep[start][end], shortestDistance[start][end], minStep[start][end]);
			
			return cheap;
		}//if
		
		return null;
	}//cheapestRoute
	
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
		}//if
		
		return null;
	}//minStepsRouteTo
	
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
		}//if
		
		return null;
	}//shortestKmRouteTo
	
	private void makeArrays(double [] [] costPerStep, double [] [] minStep, double [] [] shortestDistance, String [] [] days) {
		setInf(costPerStep);
		setInf(minStep);
		setInf(shortestDistance);
		
		for(int i = 0;i < costPerStep.length;i++) {
			for(int j = 0;j < costPerStep[i].length;j++) {
				if(j == i) {
					costPerStep[i][j] = 0;
				}//if
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
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
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
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
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						shortestDistance[i][j] = SystemManager.legs.get(k).getDistance();
					}//if
				}//second inner for loop	
			}//inner for loop
		}//outer for loop
		
		for(int i = 0;i < days.length;i++) {
			for(int j = 0;j < days.length;j++) {
				days[i][j] = "";
			}//inner for loop
		}//outer for loop
		
		for(int i = 0;i < days.length;i++) {
			for(int j = 0;j < days[i].length;j++) {
				for(int k = 0;k < SystemManager.legs.size();k++) {
					if((SystemManager.legs.get(k).getOrigin()).equals(SystemManager.locations.get(i)) && (SystemManager.legs.get(k).getDestination()).equals(SystemManager.locations.get(j))) {
						days[i][j] = SystemManager.legs.get(k).getDays();
					}//if
				}//second inner for loop
			}//inner for loop
		}//outer for loop
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
					if (matrix[i][k] + matrix[k][j] < matrix[i][j] /*&& days[i][k].contains(day) && days[k][j].contains(day)*/) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						arrOne[i][j] = arrOne[i][k] + arrOne[k][j];
						arrTwo[i][j] = arrTwo[i][k] + arrTwo[k][j];
					}//if	
				}//second inner for loop
			}//inner for loop
		}//outer for loop
	}//floydWarshall
}//Location
