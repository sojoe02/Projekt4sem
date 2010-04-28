/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class RunServer {

    
    private final int port = 6789;
    SendObject clientChoose;
 String metodeChoose;
  String startLoc;
  String endLoc;
  Date endDate ;
  String volume ;
  String weight;


    public RunServer() throws IOException {
	

    }

    public SendObject getClientChoose() throws Exception {


	System.out.println("Server listening on port " + port + ".");
	 
try {
ServerSocket serversocket = new ServerSocket(port);
  System.out.println("Waiting for connections.");
 Socket socket = serversocket.accept();
 System.out.println("Accepted a connection from: "
		    + socket.getInetAddress());
 InputStream is = socket.getInputStream();
 ObjectInputStream ois = new ObjectInputStream(is);
 //SendObject clientChoose = (SendObject)ois.readObject();
  metodeChoose= (String)ois.readObject();
  startLoc= (String)ois.readObject();
  endLoc= (String)ois.readObject();
  endDate = (Date)ois.readObject();
  volume = (String)ois.readObject();
  weight = (String)ois.readObject();

// if (clientChoose!=null){System.out.println("modtaget");}
 
 is.close();
 socket.close();
 serversocket.close();
}catch(Exception e){System.out.println(e);}

clientChoose = new SendObject(metodeChoose, startLoc, endLoc, endDate, volume, weight);
 
 return clientChoose;
	
 }
}












	/*


	try {
	  
	    Socket socket = serversocket.accept();
	    
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
	 * */















	    

