/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class SendObject implements Serializable{

    ArrayList<String> ShipDates = new ArrayList<String>();

    String metodeChoose, startLoc, endLoc, volume, weight, ShipID, DepartureDate, ArrivalDate;
    Date endDate;

public SendObject(String metodeChoose, String startLoc,
		    String endLoc, Date endDate, String volume,
		   String weight)  {
    this.metodeChoose = metodeChoose;
    this.startLoc = startLoc;
    this.endLoc = endLoc;
    this.endDate = endDate;
    this.volume = volume;
    this.weight = weight;
}

public String getMetodeChoose()	{
    return metodeChoose;
}
public String getStartLoc()	{
    return startLoc;
}
public String getEndLoc()	{
    return endLoc;
}
public Date getEndDate()	{
    return endDate;
}

public void putShipDates (ArrayList ShipDates)   {
    this.ShipDates = ShipDates;
}

public ArrayList getShipDates()  {
    return ShipDates;
}

public void putShipID (String ShipID)   {
    this.ShipID = ShipID;
}
public void putDepartureDate (String DepartureDate)   {
    this.DepartureDate = DepartureDate;
}
public void putArrivalDate (String ArrivalDate)   {
    this.ArrivalDate = ArrivalDate;
}

public String getShipID()   {
    return ShipID;
}

public String getDepartureDate()   {
    return DepartureDate;
}

public String getArrivalDate()   {
    return ArrivalDate;
}
}
