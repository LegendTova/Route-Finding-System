import java.util.*;
public class SystemManager {
	
	public static ArrayList<Location> locations = new ArrayList<Location>();
	public static ArrayList<Leg> legs = new ArrayList<Leg>();
	public static void addLocation(Location l) {
		locations.add(l);
	}
	public static void addLeg(Leg l){
		legs.add(l);
	}
	public static String findLocation(String location) {
		return "";
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
	
}
