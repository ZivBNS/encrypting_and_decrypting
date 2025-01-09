package Steganography;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import javax.swing.text.PlainDocument;

public class Interface extends JFrame implements ActionListener, MouseListener, DocumentListener
{
	private static final long serialVersionUID = 1L;
	JLabel TitleLabel,ChooseActionCommand,ChooseFileCommand,DirectoryLabel,KeysInsert,PublicKey;//כותרת, בחר בפעולה שתרצה, בחר תמונה, תיקיה, הכנס מפתח, מפתח
	JPanel TitlePanel,ActionPanel,FilePanel,TextPanel;
	JRadioButton[] Action;
	ButtonGroup group;
	String ActioName;
	JTextField TextPath;//מסלול תמונה
	JTextArea Text;//המסר
	CoustomizedButton Browse;//Browse button for image
	JButton emailButton;
	TitledBorder Title1,Title2;//Message to Encrypt: , Notes left:
	CompoundBorder border1,border2,border3;
	Border a,a2,b;
	JScrollPane AreaJScrollPane;
	int MaxLengthOfNotes=5000000,LenOfWritten,LenOfDeleted;
	int NotesLeft=MaxLengthOfNotes;
	
	Container contentPane;
	SpringLayout layout;
	JTextField PublicBox;
	CoustomizedButton BrowsePublic,Encode,Decode;
	Checks checks;
	Object buttonEntered,ButtonPressed;
	MenuBar Bar;
	Help Help_W = null;
	public Interface()
	{
		//Window properties
		super("Steganography");
		setSize(726,238);

		//Experiment***
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		int ScreenWidth,ScreenHeight;
		
		setLocationRelativeTo(null);
		ScreenWidth=(int)getLocation().x;
		ScreenHeight=(int) (scrnsize.getHeight()*0.1);
		
		setLocation(ScreenWidth, ScreenHeight);
		//**
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//Create a menu bar
	    Bar = new MenuBar();
		 
	    //create headers buttons 
	    Menu FileMenu = new Menu("File");
	    Menu HelpMenu = new Menu("Help");
	 
	    FileMenu.add(new MenuItem("New Keys"));
	    FileMenu.addSeparator();
	    FileMenu.add(new MenuItem("Exit"));
	    
	    HelpMenu.add(new MenuItem("View Help"));
	    HelpMenu.addSeparator();
	    HelpMenu.add(new MenuItem("About Steganography"));
	 
	    //add the headers buttons to the bar
	    Bar.add(FileMenu);
	    Bar.add(HelpMenu);
	 
	    this.setMenuBar(Bar);
	    
	    FileMenu.addActionListener(this);
	    HelpMenu.addActionListener(this);
		
		
		layout = new SpringLayout();
		setLayout(layout);

		//Create Background Color
		Color Background=new Color(196,219,223);
		//Create Foreground Color
		Color Forground=new Color(49,133,135);
		//commands foreground color
		Color fg=new Color(184,66,102);
		
		URL url = getClass().getResource("../Interface Images/Title.png");
		//create ImageIcon for title
		ImageIcon icon = new ImageIcon(url);
		

		
		//create JLabels
		TitleLabel=new JLabel();
		TitleLabel.setIcon(icon);
		
		
		ChooseActionCommand=new JLabel("Choose your action below:");
		ChooseFileCommand=new JLabel("");
		DirectoryLabel=new JLabel("Directory :");
		
		KeysInsert=new JLabel("Insert a public key of your contact");
		PublicKey=new JLabel("Public Key:");
		

		//create font to JLabels
		Font f = new Font("tahoma", Font.BOLD, 15);
		
		//Set font to JLabels
		ChooseActionCommand.setFont(f);
		ChooseFileCommand.setFont(f);
		DirectoryLabel.setFont(f);
		KeysInsert.setFont(f);
		PublicKey.setFont(f);
		ChooseActionCommand.setForeground(fg);

		emailButton = new JButton(""); // Send email button
		emailButton.setBackground(Color.white);
		emailButton.setIcon(new ImageIcon(getClass().getResource("../Interface Images/SendMail.png")));
		emailButton.addActionListener(new ActionListener() { //הוזפת מאזין 
			public void actionPerformed(ActionEvent e) {
			 new EmailUserInterface().main();;	}//יצירה של איימיל
		});
		
		//Create JRadioButtons
		Action=new JRadioButton[2];
		Action[0]=new JRadioButton("Encode");
		Action[1]=new JRadioButton("Decode");
		
		//Create ButtonGroup
        group = new ButtonGroup();
        group.add(Action[0]);
        group.add(Action[1]);
        
        //Create JTextField
        TextPath=new JTextField(25);
        TextPath.setEditable(false);
        PublicBox=new JTextField(24);
        PublicBox.setEditable(false);

        //Create CoustomizedButton
        Browse=new CoustomizedButton("Browse");
        BrowsePublic=new CoustomizedButton("Browse");     
        Encode=new CoustomizedButton("Encode");
        Decode=new CoustomizedButton("Decode");
        
        //Add MouseListeners


		//Create JTextArea
		Text=new JTextArea("",5,30);
		Text.setLineWrap(true); //go to the next line after there is no more  space
		Text.setWrapStyleWord(true);

		//Limit the amount of characters the user can insert
		Text.setDocument(new JTextFieldLimit(4));
        
        Text.getDocument().addDocumentListener(this);

		//Create JScrollPane for JTextArea
        AreaJScrollPane = new JScrollPane(Text);
        AreaJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        
        b = AreaJScrollPane.getBorder();
        Title1 = BorderFactory.createTitledBorder(border2,"Message to Encrypt");
        Title1.setTitleColor(fg);
        Title2=BorderFactory.createTitledBorder(b, "Notes left: "+NotesLeft,5 , 5);
        Title2.setTitleColor(fg);
        Title1.setTitleFont(f);
        a = BorderFactory.createEmptyBorder(0,0,0,0);
        border2 = BorderFactory.createCompoundBorder(Title2,a);
        border3 = BorderFactory.createCompoundBorder(Title1,a);
        border1 = BorderFactory.createCompoundBorder(border3,border2);
        

        AreaJScrollPane.setBorder(border1);
        
        //Add Action Listeners
        Action[0].addActionListener(this);
        Action[1].addActionListener(this);
		
		//Add Components to JPanels
        TitleLabel.setOpaque(true);
        
        contentPane = getContentPane(); //Get main panel
        

        
        //SetComponentes to window
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, TitleLabel,5,SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, TitleLabel,5,SpringLayout.NORTH, contentPane);
        
        contentPane.add(TitleLabel);
        contentPane.add(emailButton);
		ActionPanel=new JPanel();
		
		ActionPanel.setLayout(new BoxLayout(ActionPanel,BoxLayout.Y_AXIS));
		JPanel InnerActionPanel=new JPanel();

		InnerActionPanel.setLayout(new BoxLayout(InnerActionPanel,BoxLayout.Y_AXIS));
		ActionPanel.add(ChooseActionCommand);
		InnerActionPanel.add(Box.createVerticalStrut(0));
		InnerActionPanel.add(Box.createHorizontalStrut(15));
		InnerActionPanel.add(Action[0]);	
		InnerActionPanel.add(Action[1]);
		ActionPanel.add(InnerActionPanel);
		
		
		//Set Background to components
        contentPane.setBackground(Background);
		TitleLabel.setBackground(Background);
		ActionPanel.setBackground(Background);
		InnerActionPanel.setBackground(Background);
		Action[0].setBackground(Background);
		Action[1].setBackground(Background);
        AreaJScrollPane.setBackground(Background);
        
        //Set Foreground to components
        //??
        ChooseActionCommand.setForeground(fg);
		Action[0].setForeground(Forground);
		Action[1].setForeground(Forground);
		ChooseFileCommand.setForeground(fg);
		DirectoryLabel.setForeground(Forground);
		KeysInsert.setForeground(fg);
		PublicKey.setForeground(Forground);

		layout.putConstraint(SpringLayout.WEST, ActionPanel,0,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, ActionPanel,95,SpringLayout.NORTH, TitleLabel);
        contentPane.add(ActionPanel);

		setVisible(true);
		validate();
        Browse.addMouseListener(this);
        BrowsePublic.addMouseListener(this);
        Encode.addMouseListener(this);
        Decode.addMouseListener(this);
        checks=new Checks(this);
        
        
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String Command=e.getActionCommand();//מקבל את הפעולה
		
		if(Command=="Exit")//סוגר חלון
			System.exit(0);
		else
			if(Command=="New Keys")//יצירת מפתחות חדשים
			{
				new Message(this,"Keys");//מודיע עך כך למשתמש
			}
			else
				if(Command=="View Help")//חלון עזרה
				{
					if(Help_W==null)
						Help_W=new Help(this);
					Help_W.setVisible(true);
				}
				else
					if(Command=="About Steganography")//חלון אודות
					{
						new About(this);
					}				
		System.out.println(getSize());
	
		if(e.getSource()== Action[0] || e.getSource()== Action[1])//אם נבחרו אחד מכפתורי הרדיו
		{
			ActioName=e.getActionCommand();//מקבל את שם הפעולה
			ChooseFileCommand.setText("Select an image to "+ActioName+":");//בחר תמונה להצפנה/פענוח

			setFileChoose();
			setComponentsVisibilityToFalse();
			
			TextPath.setText(null);
			PublicBox.setText(null);
			setSize(726,308);
		}
		
		validate();
	}
	public void setFileChoose()//השיטה מסדרת עבור ההצפנה והפענוח את הרכיבים הבאים:
	{
		contentPane.remove(ChooseFileCommand);//לייבל של בחר תמונה להצפנה/פענוח
		contentPane.remove(DirectoryLabel);//לייבל תיקיה
		contentPane.remove(TextPath);//מסלול התמונה שנבחרה
		contentPane.remove(Browse);//כפתור עיון לבחירת תמונה
		
		//the X:           side of contentPane                  the distance    side of ChooseFileCommand
		layout.putConstraint(SpringLayout.WEST, ChooseFileCommand,7,SpringLayout.WEST, contentPane);//מסדר את לייבל בחירת קובץ בצד שמאל של המיכל
		//the Y:           side of ActionPanel                  the distance    side of ChooseFileCommand
		layout.putConstraint(SpringLayout.NORTH, ChooseFileCommand,10,SpringLayout.SOUTH, ActionPanel);//ממקם את לייבל בחירת קובץ מתחת פאנל הפעולה
        contentPane.add(ChooseFileCommand);
        
		layout.putConstraint(SpringLayout.WEST, DirectoryLabel,23,SpringLayout.WEST, contentPane);//מסדר את לייבל התיקריה בצידו השמאלי של המיכל
        layout.putConstraint(SpringLayout.NORTH, DirectoryLabel,25,SpringLayout.NORTH, ChooseFileCommand);//
        contentPane.add(DirectoryLabel);
        
		layout.putConstraint(SpringLayout.WEST, TextPath,85,SpringLayout.WEST, DirectoryLabel);
        layout.putConstraint(SpringLayout.NORTH, TextPath,27,SpringLayout.NORTH, ChooseFileCommand);//מסלול של התמונה בתיקייה
        contentPane.add(TextPath);
        
		layout.putConstraint(SpringLayout.WEST, Browse,300,SpringLayout.WEST, TextPath);
        layout.putConstraint(SpringLayout.NORTH, Browse,14,SpringLayout.NORTH, ChooseFileCommand);
        contentPane.add(Browse);
	}
	public void setComponentsVisibilityToFalse()
	{
		AreaJScrollPane.setVisible(false);
		KeysInsert.setVisible(false);
		PublicKey.setVisible(false);
		PublicBox.setVisible(false);
		BrowsePublic.setVisible(false);
		Encode.setVisible(false);
		Decode.setVisible(false);	
	}
	public void SetMessageBoxOrKeys()//השיטה מסדרת רכיבים על פי הפעולה שנבחרה
	{
		
		if(ActioName=="Encode")//אם נבחר הצפנה
		{

			contentPane.remove(AreaJScrollPane);//מוחק את אזור הגלילה הקיים 
			//מסדר רכיבים לאיפשור כתיבת המסר
			layout.putConstraint(SpringLayout.WEST, AreaJScrollPane,23,SpringLayout.WEST, contentPane);
	        layout.putConstraint(SpringLayout.NORTH, AreaJScrollPane,60,SpringLayout.NORTH, Browse);
	        contentPane.add(AreaJScrollPane);
	        
	        AreaJScrollPane.setVisible(true);
	        
			KeysInsert.setVisible(false);
			PublicKey.setVisible(false);
			PublicBox.setVisible(false);
			PublicBox.setText(null);
			BrowsePublic.setVisible(false);
			Encode.setVisible(false);
			Decode.setVisible(false);
		}
		else//אם נבחר פענוח
		{
			if(AreaJScrollPane.isVisible())
			{
				AreaJScrollPane.setVisible(false);
			}
	        SetKeys(DirectoryLabel);//סדר רכיבים לאיפשור בחירת מפתחות
		}
		validate();
		
	}
	public void SetKeys(Component comp)
	{


		contentPane.remove(KeysInsert);//Insert a public key of your contact
		contentPane.remove(PublicKey);//Public key:
		contentPane.remove(PublicBox);//[----------] path of key
		contentPane.remove(BrowsePublic);//browse button
		
		layout.putConstraint(SpringLayout.NORTH, KeysInsert,15,SpringLayout.SOUTH, comp);
		layout.putConstraint(SpringLayout.WEST, KeysInsert,7,SpringLayout.WEST, contentPane);
		contentPane.add(KeysInsert);

        
        layout.putConstraint(SpringLayout.WEST, PublicKey,23,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, PublicKey,25,SpringLayout.NORTH, KeysInsert);
        contentPane.add(PublicKey);
        
        layout.putConstraint(SpringLayout.WEST, PublicBox,96,SpringLayout.WEST, PublicKey);
        layout.putConstraint(SpringLayout.NORTH, PublicBox,27,SpringLayout.NORTH, KeysInsert);
        contentPane.add(PublicBox);
        
		layout.putConstraint(SpringLayout.WEST, BrowsePublic,288,SpringLayout.WEST, PublicBox);
        layout.putConstraint(SpringLayout.NORTH, BrowsePublic,14,SpringLayout.NORTH, KeysInsert);
        contentPane.add(BrowsePublic);
        

		KeysInsert.setVisible(true);
		PublicKey.setVisible(true);
		PublicBox.setVisible(true);
		BrowsePublic.setVisible(true);
		
		validate();
	}
	public void SetEncodeOrDecode()//מסדר כפתור הצפנה/פענוח בהתאם
	{
		if(!PublicBox.getText().isEmpty())
		{

			if(ActioName=="Encode")
				SetButton(Encode);
			else
				SetButton(Decode);
			}
			
	}
	public void SetButton(CoustomizedButton button)//סידור כפתור מעוצב הצפנה/פענוח
	{
		contentPane.remove(button);
		Component comp;
		if(ActioName=="Encode")//במצב הצפנה
		{
			comp=AreaJScrollPane;//מקום כתיבת המסר
	        layout.putConstraint(SpringLayout.WEST, button,205,SpringLayout.EAST, comp);//מסדר בצד הימני
	        layout.putConstraint(SpringLayout.NORTH, button,25,SpringLayout.SOUTH, comp);//התחתון מתחת לאיזור הכתיבה
	        contentPane.add(button);
		}
		else//במצב פענוח
		{
	        layout.putConstraint(SpringLayout.EAST, button,250,SpringLayout.WEST, BrowsePublic);//מסדר בצד הימני
	        layout.putConstraint(SpringLayout.NORTH, button,25,SpringLayout.SOUTH, PublicKey);//מתחת לשורת המפתח הציבורי
	        contentPane.add(button);
		}
        button.setVisible(true);
		validate();
	}
	class JTextFieldLimit extends PlainDocument 
	{
		private static final long serialVersionUID = 1L;
		private int limit;
	    JTextFieldLimit(int limit)//מקבל את מספר התווים שניתן להכניס
	    {
	        this.limit = limit;
	    }

	    public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException 
	    {
	    	    String oldText=Text.getText();//המסר שהוקלד בעבר עבור המשתמש
		    	System.out.println(str.charAt(0));
	    		super.insertString(offset, str, attr);
		    	if(checks.CheckTextMessage())//האם נכתב מסר
		    		if (Text.getText().length() > limit) //אם כמות הטקסט גדולה מכמות התווים שמותר להכניס
			    		Text.setText(oldText);//השאר בטקסט הישן
		    	else
		    	{
		    		if(!Encode.isVisible())//אם כפתור ההצפנה לא היה מוצג
		    		{
		    			Encode.setVisible(true);//הצג אותו
		    			SetButton(Encode);
		    			setSize(726,508);
		    		}
		    	}
	    }
	}
	public void removeUpdate(DocumentEvent e) //כשנמחק תו
    {
		if(Text.getText().length()==0)
		{
			Encode.setVisible(false);
			Decode.setVisible(false);
			
		}
		System.out.println("remove");

		NotesLeft+=e.getLength();
		Title2.setTitle("Notes left: "+NotesLeft);
		
		AreaJScrollPane.setBorder(b);
        AreaJScrollPane.setBorder(border1);
        
        validate();
		}
	public void changedUpdate(DocumentEvent e) {}
	public void insertUpdate(DocumentEvent e)//כשנכתב תו 
	{
		System.out.println("add");

		
		NotesLeft-=e.getLength();
		Title2.setTitle("Notes left: "+NotesLeft);
		
		System.out.println(NotesLeft);
		AreaJScrollPane.setBorder(b);
        AreaJScrollPane.setBorder(border1);
        validate();
	}
	
	public void mouseClicked(MouseEvent e) {}
	//Balloon tool tip
	public void mouseEntered(MouseEvent e) 
	{
		buttonEntered=e.getSource();
		if(e.getSource()==Browse)
			checks.MouseEntered("Browse");
		else
			if(e.getSource()==BrowsePublic)
				checks.MouseEntered("BrowsePublic");
	}

	public void mouseExited(MouseEvent e) 
	{
		buttonEntered=null;
		checks.mouseExited();
		
	}
	public void mousePressed(MouseEvent e) 
	{
		ButtonPressed=e.getSource();
	}
	public void mouseReleased(MouseEvent e) 
	{
		if(ButtonPressed==buttonEntered)
			if(e.getSource()==Browse)
				checks.mouseReleased("Browse");
				else
					if(e.getSource()==BrowsePublic)
						checks.mouseReleased("BrowsePublic");
					else
						if(e.getSource()==Encode)
							checks.mouseReleased("Encode");
						else
							if(e.getSource()==Decode)
								checks.mouseReleased("Decode");
	}
}

