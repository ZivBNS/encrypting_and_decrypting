package Steganography;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client extends Panel implements Runnable
{
	//Components for the visual display of the chat window
	private TextField textfeild = new TextField();//��� ������ ����� ������ ��'��
	private TextArea textarea = new TextArea();//��� ����� �� �����
	private String username;//������ ����� ������
	private Button Files;//����� ������ �� ������ ����� ������/������
	private Socket socket;//the socket connecting us to the server
	//the streams we communicate to the server, these come from the socket
	private DataOutputStream dataout;//������ �� ����� ���� ���
	private DataInputStream datain;//������ �� ����� ���� ����
	
	public Client(String  host, int port)
	{
		username = JOptionPane.showInputDialog("Enter user name");//���� ������� �� ������ ���� ���� ������ �� �����
		if(username.equals(""))//����� ��� ���� ��
			username = "Annonymous";//���� ������� ��'��
		Files = new Button("Files");//���� �� ����� ������ ������/������
		//set up the screen
		setLayout(new BorderLayout());//����� �����
		add("North", textfeild);//���� ������ ����� �����
		add("Center", textarea);//���� ����� ���� �����
		add(BorderLayout.SOUTH, Files);//����� ������/������ ����� ����
		//Anonymous class is used as callback to see the message when someone types a line and press send
		Files.addActionListener(new ActionListener()//����� ������
		{
			public void actionPerformed(ActionEvent e)//�� ����� ����� ����
			{
				new Interface();//���� ����� ������/�����
			}
		});
		textfeild.addActionListener(new ActionListener()//����� ������ ������
		{
			public void actionPerformed(ActionEvent e)//����� �����
			{
				processMessage(e.getActionCommand());//���� �� ���� ������
			}
		});
		
		//connect to the server
		try
		{
			//initiate the connection
			socket = new Socket(host, port);//����� ���� ������
			//connection confirmed
			System.out.println("connected to: " + socket);
			//create DataInput and DataOutput
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());
			//start a background thread for message receiving
			new Thread(this).start();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
		}
		//gets called when the user types something
		private void processMessage(String message)//
		{
			try
			{
				//send it to the server
				dataout.writeUTF(username + ": " + message);//���� ���� �� ���� ������
				//clear out text input field
				textfeild.setText("");//���� �� ���� ������ ���� ����� �����
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}
		}
		//show message from the other window (runs by the background thread)
		public void run()//���� ������ �������� �����
		{
			try
			{
				//Receive messeges one-by-one, forever
				while(true)
				{
					//get the next message
					String message = datain.readUTF();//���� �� ������ ������ ����
					//print it to the next window
					textarea.append(message + "\n");//������ ���� ����� �����
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}
		}
	}

