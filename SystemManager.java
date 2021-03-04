import java.util.*;
public class SystemManager {
	
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
	public static Route findCheapestRoute() {
		return null;
	}
	public static Route findMinStepsRoute() {
		return null;
	}
	public static Route findMinKmRoute() {
		return null;
	}
	public static String printLocation(Location l) {
		return l.name;
	}
	public static String printLeg(Leg l) {
		return printLocation(l.origin) + " to " + printLocation(l.destination) + ". A trip of " + l.distance + "km with a cost of " + l.costPerKm + "$ per km";
	}
}
