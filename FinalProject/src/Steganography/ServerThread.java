package Steganography;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread 
{
	
	private Server server;//the server that spawned us
	private Socket socket;//the socket connected to our client
	
	public ServerThread(Server server, Socket socket)
	{
		//save the parameters
		this.server = server;
		this.socket = socket;
		//start up the thread
		start();
	}
	//this runs in a separate thread when start() is called in the constructor
	public void run()
	{
		try
		{
			DataInputStream datain = new DataInputStream(socket.getInputStream());//create a DataInputStream for communication, the client is using a DataOutputStream to write to the server
			while(true)
			{
				String message = datain.readUTF();//read the next message
				System.out.println("Sending: " + message);//message confirmed
				//send the message to all the clients
				server.sendToAll(message);
			}
		}
		catch(EOFException ex)
		{
			//no need an error message
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally{
			//the connection is closed for one reason or another, so have the server dealing with it
			server.removeConnection(socket);
		}
	}
}

