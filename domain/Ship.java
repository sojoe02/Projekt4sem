package domain;

import java.util.ArrayList;
import java.util.Map;

public class Ship {


    private String shipID, Name, Captain, Totalvolume, Totalweight;
    private ArrayList<String> DestinationList = new ArrayList<String>();
    private ArrayList<String> DatoList = new ArrayList<String>();

    private Cargo cargo;	//skibets Last

    public Ship(int shipID, String Name, String Captain, String Totalvolume,
	    String Totalweight, ArrayList DestinationList, ArrayList DatoList) {
	this.shipID = Integer.toString(shipID);
	this.Name = Name;
	this.Captain = Captain;
	this.Totalvolume = Totalvolume;
	this.Totalweight = Totalweight;
	this.DestinationList = DestinationList;
	this.DatoList = DatoList;


	//cargo = new Cargo(currentvolume, currentweight);
	//Opret en last til et skib
    }

    public String getShipID() {

	return shipID;

    }

    public Cargo getCargo() {
	return cargo;
    }

    public boolean availCargo(String volume, String weight) {
	if (cargo.availCargo(volume, weight) == true) {
	    return true;
	}
	return false;

    }

    public void updateShip(String volume, String weight) {
	cargo.updateShip(volume, weight);
    }

    public String toString() {
	System.out.println("lort");
	return "Skibs id:" + shipID + Name + Captain + Totalvolume + Totalweight;

    }
}
	
	
