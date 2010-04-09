package domain;


public class Ship {


	private String shipID;
	Cargo cargo;
	
	public Ship (String shipsID, String currentvolume, String currentweight)	{
	this.shipID = shipsID;
	cargo = new Cargo(currentvolume, currentweight);
	}
	public String getShipID()	{
		return shipID;

	}
	//public String getShipsID()	{
	//	return shipsID;
	//}
	
	public boolean availCargo(String volume, String weight)	{
			if (cargo.availCargo(volume, weight)== true) {
			return true;	
		}
		return false;
		
	}

	public void updateShip(String volume, String weight)	{
	    cargo.updateShip(volume,weight);
	}
	
	
	public String toString(){
		return	shipID;

	}
}
	
	
