package domain;

import java.util.ArrayList;

public class Ship {

    private String shipID;

    private Cargo cargo;	//skibets Last

    public Ship(String shipID, String currentvolume, String currentweight) {
	this.shipID = shipID;

	cargo = new Cargo(currentvolume, currentweight);
    }

    public String getShipID() {

	return shipID;

    }

    public void getCargo() {
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
	return shipID;

    }
}
	
	
