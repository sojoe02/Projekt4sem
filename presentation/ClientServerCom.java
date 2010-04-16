/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;


/**
 *
 * @author Mats l
 */
public class ClientServerCom {
Socket sock;
  String str;

  public ClientServerCom(Socket socket) throws Exception {
    sock = socket;
  }

  public ClientChoose readFromSocket() throws Exception {
	//	read an object other side
	InputStream is = sock.getInputStream();
	ObjectInputStream ois = new ObjectInputStream(is);

	ClientChoose clientChoose = (ClientChoose)ois.readObject();

    return clientChoose;
  }

  public void writeToSocket(ArrayList clientChoose) throws Exception {
    //	send an object to other side
	OutputStream os = sock.getOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(os);
	oos.writeObject(clientChoose);
	oos.flush();
	oos.close();

  }
}