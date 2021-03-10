
public class Airport extends Location{
	Airport(String n){
		super(n);
	}//Airport Constructor

	@Override
	String type() {
		return "Airport";
	}
}//Airport
