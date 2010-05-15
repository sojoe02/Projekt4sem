package domain.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EShip {

    private ECargo cargo;
 
    private int shipID;
    private String shipName;
    private String Captain;
//-----------------------------------------------------------------------------
    //Konstruktor, hvor ECargo oprettes.
    public EShip(int shipID, String shipName, String shipType,
	    String Captain, int maxContainer) {
	this.shipID = shipID;
	this.shipName = shipName;
	this.Captain = Captain;
	cargo = new ECargo(maxContainer);
    }
//-----------------------------------------------------------------------------
// Kalder ECargo, som mapper Containerne.
public void mapContainer(int ContainerID, String Content, String Status)    {
    cargo.mapContainer(ContainerID, Content, Status);
}
//-----------------------------------------------------------------------------
public EContainer getContainer(int ContainerID)	{
    return cargo.getContainer(ContainerID);
}

    public void setShipID(int shipID) {
	this.shipID = shipID;
    }

    public int getShipID() {

	return shipID;

    }

    public String getShipName() {
	return shipName;
    }


}


	
	
