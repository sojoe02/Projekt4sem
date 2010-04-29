/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Mats l
 */
public class JavaToMySQL {

    private static Connection con;
    private static String url = "jdbc:mysql://" + "localhost" + ":3306/" + "sas";
    private static String databaseUser = "root";
    private static String password = "abc";

    public static void main(String[] arg) throws Exception {







	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("MySQL Driver Found");
	} catch (java.lang.ClassNotFoundException e) {
	    System.out.println("MySQL JDBC Driver not found ... ");
	}

//-----------------Kontakter MySQL databasen her---------------------------------------------
	try {

	    con = DriverManager.getConnection(url, databaseUser, password);

	    System.out.println("Connection established to " + url + "...");
	} catch (java.sql.SQLException e) {
	    System.out.println("Connection couldn't be established to " + url + e.toString());
	}





	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE kunde;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! in the metode to drop sas tabel.1 ");
	    System.err.println(e.getMessage());
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	try {
	    Statement statement = con.createStatement();
	    String dropString = "CREATE TABLE kunde (" +
		    "`kunde_id` int(5) NOT NULL," +
  "kunde_navn varchar(25)," +
  "chef varchar(25)," +
  "PRIMARY KEY (`kunde_id`))";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! in the metode to drop sas tabel.1 ");
	    System.err.println(e.getMessage());
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    }

}






