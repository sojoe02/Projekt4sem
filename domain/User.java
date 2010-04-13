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
	sas.placeOrder(endLoc,startLoc,endDate,volume,weight);
	}


	private String ID, Name, Adress;

	public User(String ID, String Name, String Adress){
	    this.ID = ID;
	    this.Name = Name;
	    this.Adress = Adress;
	}

	public String toString()
	{
	    String str = "ID: " + ID + "\n Name: " + Name + "\n Adress:"
		    + Adress;

	    return str;
	}
}

