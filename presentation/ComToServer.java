/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.*;	    // pakker til socket
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class ComToServer {

    private String ipAddress = "localhost";
    private final int port = 6789;
   
    ObjectInputStream ois = null;
    

    public ComToServer() {
    }

    public void connectToServer(SendObject clientChoose) throws Exception {


	try{
 Socket socket = new Socket(ipAddress,port);
 OutputStream os = socket.getOutputStream();
 ObjectOutputStream oos = new ObjectOutputStream(os);
// SendObject sendObject = new SendObject();
// oos.writeObject(clientChoose);
 String metodeChoose = clientChoose.getMetodeChoose();
 String startLoc = clientChoose.getstartLoc();
 String endLoc = clientChoose.getendLoc();
 Date endDate = clientChoose.getendDate();
 String volume = clientChoose.getvolume();
 String weight = clientChoose.weight;
 oos.writeObject(metodeChoose);
 oos.writeObject(startLoc);
 oos.writeObject(endLoc);
 oos.writeObject(endDate);
 oos.writeObject(volume);
 oos.writeObject(weight);
 oos.close();
 os.close();
 socket.close();
  }catch(Exception e){System.out.println(e);}
 }
 }










/*


	socket = new Socket(ipAddress, port);
	System.out.println("\nClient are ready to connect to server");

	try {
	    // open I/O streams for objects
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    ois = new ObjectInputStream(socket.getInputStream());
	} catch (Exception e1) {
	}

	try {
	    // read an object from the server
	    oos.writeObject(clientChoose);
	    oos.flush();
	    System.out.println("\nClient have send a object to server ");
	    // close streams and connections
	    ois.close();
	    oos.close();
	    socket.close();
	    System.out.println("\nClientServer conncection is end");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
}


 *
 */
