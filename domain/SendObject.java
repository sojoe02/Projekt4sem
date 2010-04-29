/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class SendObject implements Serializable {

    String metodeChoose, startLoc, endLoc;
    Date endDate;
    String volume, weight;

public SendObject(String metodeChoose, String startLoc, String endLoc,
	Date endDate, String volume, String weight)	{
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
public String getstartLoc() {
    return startLoc;
}
public String getendLoc ()  {
    return endLoc;
}
public Date getendDate ()   {
    return endDate;
}
public String getvolume ()   {
    return volume;
}
public String getweight ()   {
    return weight;
}

}