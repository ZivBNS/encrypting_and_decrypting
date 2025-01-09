package Steganography;

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;

public class ClientApp extends JFrame 
{
	public ClientApp(String caption)
	{
		super(caption);
		setLayout(new BorderLayout());
		add("Center", new Client("LocalHost", 6000));
		setSize(500, 500);
		setVisible(true);
	}

	public static void main(String args[])
	{
		new ClientApp("Chat");
	}

}
