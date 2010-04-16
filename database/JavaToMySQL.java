/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
/*
	try {
Statement statement = con.createStatement();
statement.executeUpdate("INSERT INTO shipinfo " + "VALUES ('3', 'Simpson')");

	}
	 catch (Exception e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	 }
*/
try {

//Create table called my_table
    Statement statement = con.createStatement();
	String sql = "CREATE TABLE sas2(destination VARCHAR(254))";
	statement.executeUpdate(sql);
} 
catch (SQLException e) {
}


	/*
	


	    Statement st = con.createStatement();
	    st.executeUpdate("INSERT INTO shipinfo "
		    + "VALUES (`1001`");
	   

	    con.close();
	} catch (Exception e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}
	/*

	 */
    
}
}
/*
Connection conn = DriverManager.getConnection(
"jdbc:mysql://localhost/PersonDB", "lb", "bl");
String sqlStmt = "SELECT name,no FROM person";
Statement stmt;
stmt = conn.createStatement();
ResultSet resultSet;
resultSet = stmt.executeQuery(sqlStmt);
String name;
int no;
while (resultSet.next()) {
name = resultSet.getString(”name");
no = resultSet.getInt(”no”);
System.out.println("Person [name(no)]= " + name + "(" + no + ")");
}
}
 */


