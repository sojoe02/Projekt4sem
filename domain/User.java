package domain;


public class User {

	
	public static void main (String[] args)throws NullPointerException	{
	Sas sas = new Sas();
	sas.setUp();
	
	
	String endLoc = "23-04-2010";
	String startLoc = "14-04-2010";
	String endDate ="23-04-2010";
	String volume = "4000";
	String weight = "850";
	String price = sas.placeOrder(endLoc,startLoc,endDate,volume,weight);

	System.out.println(price);

	String Date = "24-04-2010";
	
	String confirm = sas.chooseDate(Date);

	System.out.println(confirm);
	}
	}

