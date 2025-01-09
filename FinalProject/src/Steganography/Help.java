package Steganography;
import java.awt.Color;
import java.awt.Container;

import java.awt.Font;

import java.awt.Point;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;



public class Help extends JFrame  //implements ComponentListener
{
	private static final long serialVersionUID = 1L;
	Container contentPane;
	JEditorPane editorPane;
	JScrollPane ScrollPane;
	JLabel TitleLabel,Text;
	public Help(Interface i)
	{
		super("Help and support");
		Point location=i.getLocation();
		setLocation(location.x+50, location.y+70);
		
		setSize(550,423);
		//setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //Get Main Panel
		contentPane=getContentPane();
		//Set Layout
		setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		//Create ImageIcon for Title
		ImageIcon icon = new ImageIcon(getClass().getResource("../Interface Images/Working with keys.png"));
		//create JLabels
		TitleLabel=new JLabel();
		Text=new JLabel();
		//set icon "AboutTitle" to TitleLabel
		TitleLabel.setIcon(icon);
		
		Text.setText("<Html><font><br>"+
				"<b>1.<u>What  keys are?</u></b><br>" +
				"<b>2.<u>Why do we need keys?</u></b><br>" +
				"<b>3.<u>How to use keys?</u></b><br><br>" +

				"<u>What  keys are?</u><br>" +
				"Keys are files with ''.dat'' extension which are used for security.<br><br>" +
				
				"<u>Why do we need keys?</u><br>" +
				"Every user have a pair of keys:<br>"+
				"• Public key-used by the user on decode operation.<br>"+
				"• Private key-used by the software on encode operation.<br>"+
				"The two keys are used for security which prevents:<br>"+
				"• Sending a message by  impersonation to another person.<br>"+
				"• Decoding a message by a person who doesn't have permission.<br><br>"+
				"<u>How to use keys?</u><br>" +
				"In order to create a new pair of keys, go to File-->New Keys.<br>"+
				"<b><u color=red>Note</u></b>: Creating a new pair of keys will replace the previous pair.<br>"+

				"After the keys have been created, inform your contacts about<br>" +
				"the <b>new</b> public key which they will need in order to decode the message.<br><br>"+

				"<u>Decode operation</u><br>"+
				"You need to have the public key of the user who sent you the picture<br>"+
				"when you are asked to insert a contact's public key.<br><br>"
				);
		Text.setFont(new Font("Cambria",Font.PLAIN,16));
		Text.setForeground(Color.white);
		Text.setForeground(Color.black);

		//Create JPanels
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		//Create ScrollPane
		ScrollPane = new JScrollPane(p2);

		ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		ScrollPane.setBorder(null);
		//Add components to JPanels
		p1.add(TitleLabel);
		p2.add(Text);
		//Add JPanel to window
		add(p1);
		//Add scrollPane to window
		add(ScrollPane);
	
		setVisible(true);
	}
}
