/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Mats l
 */
public class RunServer {

    ArrayList<String> clientChoose = new ArrayList<String>();
    private final int port = 6789;
    ServerSocket serversocket;

    public RunServer() throws IOException {
	serversocket = new ServerSocket(port);
	System.out.println("Server listening on port " + port + ".");

    }

    public ArrayList getClientChoose() throws Exception {


	try {
	    System.out.println("Waiting for connections.");
	    Socket socket = serversocket.accept();
	    System.out.println("Accepted a connection from: "
		    + socket.getInetAddress());
	    Connect c = new Connect(socket);
	} catch (Exception e) {
	    System.out.println("A exception is catch in server accept. " + e.getMessage());
	}
	return clientChoose;
    }

    class Connect {

	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public Connect(Socket socket) {
	    this.socket = socket;

	    try {
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
	    } catch (Exception e1) {
		try {
		    socket.close();
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		return;
	    }

	    try {
		// read an object from the server
		clientChoose = (ArrayList) ois.readObject();
		System.out.println();
		for (int i = 0; i < clientChoose.size(); i++) {
		    
		    System.out.println((String) (clientChoose.get(i)));
		    
		}
		System.out.println();

		oos.close();
		ois.close();
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }

	}
    }
}














	    

