
public class BikeDeliveryLocation extends Location{
	
	BikeDeliveryLocation(String n){
		super(n);
	}//BikeDeliverLocation constructor

	@Override
	String type() {
		return "BikeDeliveryLocation";
	}
}//BikeDeliveryLocation
