package domain;

import java.util.ArrayList;
import java.util.Map;

public class Ship {

    private String shipID;

    private Cargo cargo;	//skibets Last

    public Ship(String shipID, String currentvolume, String currentweight) {
	this.shipID = shipID;
	cargo = new Cargo(currentvolume, currentweight);
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
	return "Skibs id:" + shipID;

    }
}
	
	
