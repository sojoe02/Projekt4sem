/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.*;	    // pakker til socket
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Mats l
 */
public class ComToServer {

    private String ipAddress = "localhost";
    private final int port = 6789;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    Socket socket = null;

    public ComToServer() {
    }

    public void connectToServer(ArrayList clientChoose) throws Exception {


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


