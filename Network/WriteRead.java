/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;
import java.io.*;
import java.net.*;
/**
 *
 * @author Mats l
 */
public class WriteRead {
  Socket sock;
  String str;

  public WriteRead(Socket socket) throws Exception {
    sock = socket;
  }

  public SendObject readFromSocket() throws Exception {
	//	read an object other side
	InputStream is = sock.getInputStream();
	ObjectInputStream ois = new ObjectInputStream(is);

	SendObject sObject = (SendObject)ois.readObject();

    return sObject;
  }

  public void writeToSocket(SendObject sObject) throws Exception {
    //	send an object to other side
	OutputStream os = sock.getOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(os);
	oos.writeObject(sObject);
	oos.flush();
	oos.close();

  }
}
