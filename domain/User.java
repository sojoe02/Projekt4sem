
public class User {

	
	public static void main (String[] args)throws NullPointerException	{
	Sas sas = new Sas();
	sas.setUp();
	
	
	String endLoc = "23-04-2010";
	String startLoc = "14-04-2010";
	String endDate ="23-04-2010";
	String volume = "4000";
	String weight = "850";
	sas.placeOrder(endLoc,startLoc,endDate,volume,weight);
	}
	}

