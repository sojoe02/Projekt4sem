package domain;

import java.util.ArrayList;


public class Ship {

	private String shipID;

	private Cargo cargo;	//skibets Last

	
	
	public Ship (String shipID, String currentvolume, String currentweight)
	{
	    this.shipID = shipID;

	    cargo = new Cargo(currentvolume, currentweight);
	}

	public String getShipsID(){
		return shipID;
	}
	public void getCargo()
	{

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
