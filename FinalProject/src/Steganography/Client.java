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
	private TextField textfeild = new TextField();//שדה לכתיבת הודעה למשתמש בצ'אט
	private TextArea textarea = new TextArea();//שדה המציג את השיחה
	private String username;//סטרינג להצגת המשתמש
	private Button Files;//כפתור שיעביר את המשתמש לחלון ההצפנה/פיענוח
	private Socket socket;//the socket connecting us to the server
	//the streams we communicate to the server, these come from the socket
	private DataOutputStream dataout;//תקשורת עם הסרבר כלפי חוץ
	private DataInputStream datain;//תקשורת עם הסרבר כלפי פנים
	
	public Client(String  host, int port)
	{
		username = JOptionPane.showInputDialog("Enter user name");//כאשר מעפילים את התוכנה יוצג חלון להכנסת שם משתמש
		if(username.equals(""))//במקרה שלא נכתב שם
			username = "Annonymous";//יוצג אנונימי בצ'אט
		Files = new Button("Files");//יוצר את כפתור ההעברה להצפנה/פיענוח
		//set up the screen
		setLayout(new BorderLayout());//ארגון החלון
		add("North", textfeild);//שורת הכתיבה תיהיה למעלה
		add("Center", textarea);//חלון השיחה יוצג באמצע
		add(BorderLayout.SOUTH, Files);//כפתור ההצפנה/פיענוח יופיע למטה
		//Anonymous class is used as callback to see the message when someone types a line and press send
		Files.addActionListener(new ActionListener()//האזנה לכפתור
		{
			public void actionPerformed(ActionEvent e)//אם בוצעה לחיצה עליו
			{
				new Interface();//יכנס לחלון ההצפנה/פענוח
			}
		});
		textfeild.addActionListener(new ActionListener()//ההזנה לאיזור ההקלדה
		{
			public void actionPerformed(ActionEvent e)//בוצעה פעולה
			{
				processMessage(e.getActionCommand());//מעבד את המסר שהוקלד
			}
		});
		
		//connect to the server
		try
		{
			//initiate the connection
			socket = new Socket(host, port);//אתחול סוקט החיבור
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
				dataout.writeUTF(username + ": " + message);//שולח לשרת את הקלט שהתקבל
				//clear out text input field
				textfeild.setText("");//מנקה את שורת ההקלדה אחרי שליחת הודעה
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}
		}
		//show message from the other window (runs by the background thread)
		public void run()//מציג הודעות ממשתמשים אחרים
		{
			try
			{
				//Receive messeges one-by-one, forever
				while(true)
				{
					//get the next message
					String message = datain.readUTF();//מקבל את ההועות שנשלחו לשרת
					//print it to the next window
					textarea.append(message + "\n");//ומוסיף אותם לחלון השיחה
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}
		}
	}

