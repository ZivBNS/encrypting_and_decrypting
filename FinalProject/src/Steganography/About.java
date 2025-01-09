package Steganography;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class About extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JLabel TitleLabel;
	Container contentPane;
	JButton OK;
	JLabel Text;
	public About(Interface i) 
	{
		//Set window properties
		super(i,"About Steganography",true);
		Point location=i.getLocation();
		setLocation(location.x+50, location.y+70);
		setSize(416,405);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//Set Layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		//Set the About Title
		ImageIcon icon = new ImageIcon(getClass().getResource("../Interface Images/AboutTitle.png"));
		//create JLabels
		TitleLabel=new JLabel();
		//set icon "AboutTitle" to JLabel
		TitleLabel.setIcon(icon);
		Text=new JLabel();
		Text.setFont(new Font("Cambria",Font.PLAIN,14));
		Text.setForeground(Color.black);

        //face=Cambria
		Text.setText("<Html><font  >"+
					"Steganography is the art and the sciense of  <br>"+
				    "hiding a secret messages in a way that <br>"+
					"no body besides the one who recive (in this case) <br>"+
				    "this picture know about <br>"+
					"the secret message.<br>"+
 
					"<hr width=70cm>"+
					"</font><Html>");
		
        OK=new JButton("OK");
        OK.addActionListener(this);
		
        //Get Main Panel
		contentPane=getContentPane();
		
		//Set Components to window
		layout.putConstraint(SpringLayout.WEST, TitleLabel,0,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, TitleLabel,0,SpringLayout.NORTH, contentPane);
        contentPane.add(TitleLabel);
		layout.putConstraint(SpringLayout.WEST, Text,35,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, Text,20,SpringLayout.SOUTH, TitleLabel);
        contentPane.add(Text);
        
		layout.putConstraint(SpringLayout.WEST, OK,330,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, OK,35,SpringLayout.SOUTH, Text);
        contentPane.add(OK);
     
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==OK)
		{
			dispose();
		}
		
	}
}
