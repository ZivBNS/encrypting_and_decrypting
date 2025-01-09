package Steganography;

import java.io.*;
import java.net.*;
import java.util.*;
public class Server 
{
	private ServerSocket serversocket;//the ServerSocket we'll use for accepting new connections
	private Hashtable outputStream = new Hashtable();// a mapping from sockets to DataOutputStreams. this will help us avoid having to create a DataOutputStream each time we want to write to a stream
	public Server(int port) throws IOException
	{
		listen(port);
	}
	private void listen(int port) throws IOException
	{
		serversocket = new ServerSocket(port);//create the ServerSocket
		System.out.println("Listening on: " + serversocket);//Listening started
		//keep accepting connections forever
		while(true)
		{
			Socket socket = serversocket.accept();//get the next incoming connection
			System.out.println("connection from" + socket);//connection confirmed
			DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());//create a DataOutputStream for writing data to other side
			outputStream.put(socket, dataout);//save this stream so we don't need to make it again
			//create a new thread for this connection, and then forget about it
			new ServerThread(this, socket);//send the server and the socket
		}
	}
	//get an enumeration of all the outputStreams, one for each client connected to us
	Enumeration getOutputStreams()
	{
		return outputStream.elements();//החרת מספר הסטרימים
	}
	//send a message to all client (utility routine)
	void sendToAll(String message)
	{
		//we synchronize on this because another thread might be calling removeConnection() and this could make problems when trying to walk through the list
		synchronized(outputStream)
		{//התחל ממקום ראשון בטבלת ההאש והמשך לסטרים הבא
			for(Enumeration e = getOutputStreams(); e.hasMoreElements();)//for each client
			{
				DataOutputStream dataout = (DataOutputStream)e.nextElement();//get the output stream
				//and send a message
				try
				{
					dataout.writeUTF(message);
				}
				catch(IOException ex)
				{
					System.out.println(ex);
				}
			}
		}
	}
	//remove a socket, and it's corresponding output stream, from our list. this is usually called by a connection thread that has discovered that the connection to the client is dead
	void removeConnection(Socket socket)
	{
		synchronized(outputStream)
		{
			System.out.println("removing connection to: " + socket);
			outputStream.remove(socket);//remove from hashtable
			//make sure it's closed
			try
			{
				socket.close();
			}
			catch(IOException ex)
			{
				System.out.println("error closing: " + socket);
				ex.printStackTrace();
			}
		}
	}
	//main routine
	static public void main(String args[]) throws Exception
	{
		int port = 6000;
		new Server(port);
	}

}

