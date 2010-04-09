package domain;


public class Ship {

	private String shipID;
	
	
	public Ship (String shipID, String currentvolume, String currentweight)	{
	this.shipID = shipID;
	Cargo cargo = new Cargo(currentvolume, currentweight);
	}
	public String getShipsID()	{
		return shipID;
	}
	
	/*	public boolean availCargo(String volume, String weight)	{
			if (cargo.availCargo(volume, weight)== true) {
			return true;	
		}
		
		
	}
	
	*/
	
	public String toString()	{
		return	shipID;
	}
}
