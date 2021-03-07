
public class testDriver {

	public static void main (String [] args) {
	    SystemManager sm = new SystemManager();
	    Location b1 = new BikeDeliveryLocation("BikeDepot1");
	    Location b2 = new BikeDeliveryLocation("BikeDepot2");
	    Location b3 = new BikeDeliveryLocation("BikeDepot3");

	    Location t1 = new TruckDepot("TRUCK001");
	    Location t2 = new TruckDepot("TRUCK002");
	    Location t3 = new TruckDepot("TRUCK003");
	    Location t4 = new TruckDepot("TRUCK004");
	    
	    Location a1 = new Airport("AAA");
	    Location a2 = new Airport("BBB");
	 

	    sm.addLocation(b1);
	    sm.addLocation(b2);
	    sm.addLocation(b3);
	    sm.addLocation(t1);
	    sm.addLocation(t2);
	    sm.addLocation(t3);
	    sm.addLocation(t4);
	    sm.addLocation(a1);
	    sm.addLocation(a2);
	   

	    sm.addLeg(new BikeLeg(b1, t3, 1, "MTWRFSU"));
	    sm.addLeg(new BikeLeg(t1, b2, 8, "MTWRFSU"));
	    sm.addLeg(new BikeLeg(b2, t2, 2, "MTWRFSU"));
	    sm.addLeg(new BikeLeg(t4, b3, 2, "MTWRFSU"));
	    
	    sm.addLeg(new TruckLeg(t1, t2, 11, "MTWRFSU"));
	    sm.addLeg(new TruckLeg(t2, a2, 36, "MTWRFSU"));
	    sm.addLeg(new TruckLeg(a2, t4, 42, "MTWRFSU"));
	    sm.addLeg(new TruckLeg(t2, t4, 120, "MTWRFSU")); 
	    sm.addLeg(new TruckLeg(t3, a1, 19, "MTWRFSU"));
	    
	    sm.addLeg(new Flight(a1, a2, 221, "MTWRFSU"));
	   
	    
	    // TruckDepot t1 to TruckDepot t4
	    System.out.println("------- t1 to t4 ----------");
	    Route rA = sm.findCheapestRoute(sm.findLocation("TRUCK001"), sm.findLocation("TRUCK004"), "M");
	    System.out.println("\n\nCheapest Route: ");
	    System.out.println(rA);

	    Route rB = sm.findShortestKmRoute(sm.findLocation("TRUCK001"), sm.findLocation("TRUCK004"), "M");
	    System.out.println("\n\nShortest Route:\n " + rB);

	    System.out.println("");

	    

	  } // main

}
