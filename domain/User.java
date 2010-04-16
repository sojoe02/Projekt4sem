package domain;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String userID;
    private String Name;
    private String Adress;
    private Map<String, Order> orders = new HashMap<String, Order>();
    private Map<String, Ship> Ships = new HashMap<String, Ship>();

    public User(String userID, String Name, String Adress) {
	this.userID = userID;
	this.Name = Name;
	this.Adress = Adress;
    }

    public String toString()
    {
	return "UserID: " + userID + "\nName: " + Name + "\nAdress: " + Adress;
    }

    public String getUserID()
    {
	return userID;
    }

}

