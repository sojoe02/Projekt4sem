/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.Entity;

/**
 *
 * @author Mats l
 */
public class EHarbour {
    private String harbour;
    private String coordinate;
    private String nationally;

    public EHarbour (String harbour, String coordinate, String nationally)	{
	this.harbour = harbour;
	this.coordinate = coordinate;
	this.nationally = nationally;
    }
}
