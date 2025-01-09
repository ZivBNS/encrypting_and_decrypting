package Steganography;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Vector;

public class EmailUserInterface extends JFrame implements ActionListener{
	private JTextField fromField = new JTextField(); 
	private JTextField toField = new JTextField();
	private JTextField subjectField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton chooseimageButton = new JButton();
	private JButton removeimageButton = new JButton();
	DefaultListModel<String> l1 = new DefaultListModel<>();  
	private JList<String> list = new JList<>(l1);
	public String filename = "Empty file \n";
	public String filenamePath = "Empty file \n";
	Vector<String> attachFiles = new Vector<String>();
    public int addFileNum =0;

	
	
	JPanel paneledit;
	 MenuBar mbar;
     Menu file,edit,format,font,font1,font2;
     MenuItem item1,item2,item3,item4;
     MenuItem item5,item6,item7,item8,item9,item10;
     MenuItem fname1,fname2,fname3,fname4;
     MenuItem fstyle1,fstyle2,fstyle3,fstyle4;
     MenuItem fsize1,fsize2,fsize3,fsize4;
 	TextArea text;

 	Font f;
 	String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

 	GregorianCalendar gcalendar;

 	String command = " ";
 	String str = " ";

 	String str1 = " ", str2 = " ", str3 = " ";
 	String str4 = " ";

 	String str6 = " ";
 	String str7 = " ", str8 = " ", str9 = " ";
 	int len1;
 	int i = 0;
	int pos1;
	int len;
	
	public boolean ImageChoose = false;

	 EmailUserInterface() {
		InitializeUI();
	}
	public static void main() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				EmailUserInterface client = new EmailUserInterface();
				client.setVisible(true);
			}
		});
	}

	private void InitializeUI() {
		setTitle("Send E-mail Client");
		setDefaultCloseOperation(WindowConstants. DISPOSE_ON_CLOSE);
		setSize(new Dimension(800, 800));
		setLocation(300, 100);
		getContentPane().setLayout(new BorderLayout());
		
		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(6, 2));
		headerPanel.add(new JLabel("Your Mail:"));
		headerPanel.add(fromField);

		headerPanel.add(new JLabel("Password:"));
		headerPanel.add(passwordField);

		headerPanel.add(new JLabel("Your Friend Mail:"));
		headerPanel.add(toField);

		headerPanel.add(new JLabel("Subject:"));
		headerPanel.add(subjectField);

		// Body Panel
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BorderLayout());
		bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
		bodyPanel.add(list,BorderLayout.CENTER);	
		//add file button
		chooseimageButton.setIcon(new ImageIcon(getClass().getResource("../Interface Images/AddFile.png")));
		//chooseimageButton.setText("Add File");
		bodyPanel.add(chooseimageButton, BorderLayout.WEST);
		chooseimageButton.addActionListener(new chooseimageButtonActionListener());
		//remove file button
		removeimageButton.setIcon(new ImageIcon(getClass().getResource("../Interface Images/Remove.png")));
		//removeimageButton.setText("Remove");
		bodyPanel.add(removeimageButton, BorderLayout.EAST);
		removeimageButton.addActionListener(new removeimageButtonActionListener());

		JPanel footerPanel = new JPanel();
		footerPanel.setLayout(new BorderLayout());
		JButton sendMailButton = new JButton("Send E-mail");
		sendMailButton.addActionListener(new SendEmailActionListener());
		footerPanel.add(sendMailButton, BorderLayout.SOUTH);
/////////////////////////////
		paneledit=new JPanel();
		paneledit.setLayout(new FlowLayout());


		mbar=new MenuBar();
		 setMenuBar(mbar);

		edit=new Menu("Edit");
		format=new Menu("Format");
		font=new Menu("Font");
		font1=new Menu("Font Style");
		font2=new Menu("Size");

		edit.add(item5=new MenuItem("Cut (Ctrl+X)"));
		edit.add(item6=new MenuItem("Copy (Ctrl+C)"));
		edit.add(item7=new MenuItem("Paste (Ctrl+V)"));
		edit.add(item8=new MenuItem("Delete"));
		edit.add(item10=new MenuItem("Select All (Ctrl+A)"));
		edit.add(item9=new MenuItem("Time/Date"));
		mbar.add(edit);

		format.add(font);
		format.add(font1);
		format.add(font2);

		font.add(fname1=new MenuItem("Arial"));
		font.add(fname2=new MenuItem("Calibri"));
		font.add(fname3=new MenuItem("David"));
		font.add(fname4=new MenuItem("Georgia"));

		font1.add(fstyle1=new MenuItem("Regular"));
		font1.add(fstyle2=new MenuItem("Bold"));
		font1.add(fstyle3=new MenuItem("Italic"));
		font1.add(fstyle4=new MenuItem("Bold Italic"));

		font2.add(fsize1=new MenuItem("12"));
		font2.add(fsize2=new MenuItem("18"));
		font2.add(fsize3=new MenuItem("24"));
		font2.add(fsize4=new MenuItem("28"));

		mbar.add(format);

		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		item10.addActionListener(this);
		fname1.addActionListener(this);
		fname2.addActionListener(this);
		fname3.addActionListener(this);
		fname4.addActionListener(this);
		fstyle1.addActionListener(this);
		fstyle2.addActionListener(this);
		fstyle3.addActionListener(this);
		fstyle4.addActionListener(this);
		fsize1.addActionListener(this);
		fsize2.addActionListener(this);
		fsize3.addActionListener(this);
		fsize4.addActionListener(this);


		text=new TextArea(26,60);
		paneledit.add(text);

		f=new Font("Arial",Font.PLAIN,18);
		text.setFont(f);
		
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		getContentPane().add(bodyPanel, BorderLayout.CENTER);
		footerPanel.add(paneledit);
		getContentPane().add(footerPanel, BorderLayout.SOUTH);
	}
	
	private class SendEmailActionListener implements ActionListener {
		SendEmailActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final String sourceEmail = fromField.getText(); // requires valid Gmail id
			final String password = passwordField.getText(); // correct password for Gmail id
			final String toEmail = toField.getText(); // any destination email id

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			System.out.println("\n2nd ===> create Authenticator object to pass in Session.getInstance argument..");

			Authenticator authentication = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sourceEmail, password);
				}
			};
			
			if(text.getFont().getStyle()==1) {
				Session session = Session.getInstance(props, authentication);
				generateAndSendEmail(session, toEmail, subjectField.getText(), "<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\"><b>" + text.getText() + "</b></font>"); // הוהדעה בגוף של
				System.out.println("<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\">" + text.getText() + "</font>");								// המייל
			}
			else if(text.getFont().getStyle()==2) {
				Session session = Session.getInstance(props, authentication);
				generateAndSendEmail(session, toEmail, subjectField.getText(), "<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\"><i>" + text.getText() + "</i></font>"); // הוהדעה בגוף של
				System.out.println("<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\">" + text.getText() + "</font>");								// המייל
			}
			else if(text.getFont().getStyle()==3) {
				Session session = Session.getInstance(props, authentication);
				generateAndSendEmail(session, toEmail, subjectField.getText(), "<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\"><b><i>" + text.getText() + "</i></b></font>"); // הוהדעה בגוף של
				System.out.println("<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\">" + text.getText() + "</font>");								// המייל
			}
			else{
				Session session = Session.getInstance(props, authentication);
				generateAndSendEmail(session, toEmail, subjectField.getText(), "<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\">" + text.getText() + "</font>"); // הוהדעה בגוף של
				System.out.println("<font face=\""+text.getFont().getName()+"\" style=\""+text.getFont().getStyle()+"\" size=\""+(text.getFont().getSize()-10)+"px\">" + text.getText() + "</font>");								// המייל
			}
			}

		public void generateAndSendEmail(Session session, String toEmail, String subject, String body) {
			try {
				//if mail have attachments
				if (ImageChoose && addFileNum != 0) {
					System.out.println("\n3rd ===> generateAndSendEmail() starts..");

					MimeMessage MainMessage = new MimeMessage(session);
					MainMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
					MainMessage.addHeader("format", "flowed");
					MainMessage.addHeader("Content-Transfer-Encoding", "8bit");

					MainMessage.setFrom(new InternetAddress(fromField.getText(), subjectField.getText()));// נושא של המייל
					MainMessage.setReplyTo(InternetAddress.parse(fromField.getText(), false));
					MainMessage.setSubject(subject, "UTF-8");
					MainMessage.setSentDate(new Date());
					MainMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
					
					 // creates message part
			        MimeBodyPart messageBodyPart = new MimeBodyPart();
			        messageBodyPart.setContent(body, "text/html");
			 
			        // creates multi-part
			        Multipart multipart = new MimeMultipart();
			        multipart.addBodyPart(messageBodyPart); //set message text
			 
			        // adds attachments
			        if (attachFiles != null && addFileNum > 0) {
			        	for (int i = 0; i < addFileNum; i++) {
			                MimeBodyPart attachPart = new MimeBodyPart();
			 
			                try {
			                    attachPart.attachFile(attachFiles.elementAt(i));
			                } catch (IOException ex) {
			                    ex.printStackTrace();
			                }
			 
			                multipart.addBodyPart(attachPart);
			            }
			        }

					DataSource source = new FileDataSource(filenamePath); //file path
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename); // file name
					// Trick is to add the content-id header here
					messageBodyPart.setHeader("Content-ID", "image_id");
					multipart.addBodyPart(messageBodyPart);

					System.out.println("\n4th ===> third part for displaying image in the email body..");
					messageBodyPart = new MimeBodyPart();
					messageBodyPart.setContent("<br>" + "<img src='cid:image_id'>", "text/html");
					multipart.addBodyPart(messageBodyPart);
					MainMessage.setContent(multipart);
					System.out.println("\n4th ===> third part for displaying image in the email body..");

					System.out.println("\n5th ===> Finally Send message..");

					// Finally Send message
					 try {
				         Transport.send(MainMessage);
							System.out.println("\n6th ===> Email Sent Successfully With Image Attachment. Check your email now..");
							System.out.println("\n7th ===> generateAndSendEmail() ends..");
						    JOptionPane.showMessageDialog(null, "Mail send successfully", "Mail", JOptionPane.INFORMATION_MESSAGE);

				      } catch (Exception e) { //error
				         System.err.println("Error Sending: ");
						 JOptionPane.showMessageDialog(null, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
				         e.printStackTrace();
				      }

				}
				//if mail have not attachments
				else {
					
					System.out.println("\n3rd ===> generateAndSendEmail() starts..");

					MimeMessage MainMessage = new MimeMessage(session);
					MainMessage.setFrom(new InternetAddress(fromField.getText(), subjectField.getText()));// נושא של המייל					 
					MainMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
					MainMessage.setSubject(subject);
				 	//MainMessage.setText(body,"text/html");
				 	MainMessage.setContent(body,"text/html");

					System.out.println("\n4th ===> third part for displaying image in the email body..");

					System.out.println("\n5th ===> Finally Send message..");
					
					 try {
				         Transport.send(MainMessage);
							System.out.println("\n6th ===> Email Sent Successfully. Check your email now..");
							System.out.println("\n7th ===> generateAndSendEmail() ends..");
						    JOptionPane.showMessageDialog(null, "Mail send successfully", "Mail", JOptionPane.INFORMATION_MESSAGE);

				      } catch (Exception e) {//error
				         System.err.println("Error Sending: ");
						 JOptionPane.showMessageDialog(null, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
				         e.printStackTrace();
				      }
				}

			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

	}

	private class chooseimageButtonActionListener implements ActionListener { // add item to attached to mail
		chooseimageButtonActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(); // בחירת קובץ שאותו רוצים לשלוח במייל
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(true);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				ImageChoose = true;
				filenamePath = chooser.getSelectedFile().toString(); // get file path
				filename = chooser.getSelectedFile().getParent().toString(); // get file path without the name
				filename= filenamePath.replace(filename, "");//get only the filename
				filename = filename.replace("\\", "");//get only the filename(remove /)
				attachFiles.add(filenamePath);
				l1.add(addFileNum, filename);
				addFileNum++;

			} 
			else {
				System.out.println("No Selection ");
				ImageChoose = false;
				filenamePath = "No file";
			}
			
			System.out.println(filenamePath);
			System.out.println(filename);

		}
	}
	
	private class removeimageButtonActionListener implements ActionListener { // remove selected item from attached
		removeimageButtonActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int indexselected =list.getSelectedIndex();
			if(indexselected == -1)
				System.out.println("Choose File");
			else {
			l1.remove(indexselected);
			attachFiles.remove(indexselected);
			addFileNum--;
			System.out.println("remove " + indexselected );
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent ae) {

		command = (String) ae.getActionCommand();
		
		if (command.equals("Cut (Ctrl+X)")) {
			str = text.getSelectedText();
			i = text.getText().indexOf(str);
			text.replaceRange(" ", i, i + str.length());
		}

		if (command.equals("Copy (Ctrl+C)")) {
			str = text.getSelectedText();
		}

		if (command.equals("Paste (Ctrl+V)")) {
			pos1 = text.getCaretPosition();
			text.insert(str, pos1);
		}
		if (command.equals("Delete")) {
			String msg = text.getSelectedText();
			i = text.getText().indexOf(msg);
			text.replaceRange(" ", i, i + msg.length());
		}
		if (command.equals("Time/Date")) {
			gcalendar = new GregorianCalendar();
			String h = String.valueOf(gcalendar.get(Calendar.HOUR));
			String m = String.valueOf(gcalendar.get(Calendar.MINUTE));
			String s = String.valueOf(gcalendar.get(Calendar.SECOND));
			String date = String.valueOf(gcalendar.get(Calendar.DATE));
			String mon = months[gcalendar.get(Calendar.MONTH)];
			String year = String.valueOf(gcalendar.get(Calendar.YEAR));
			String hms = "Time" + " - " + h + ":" + m + ":" + s + " Date" + " - " + date + " " + mon + " " + year;
			int loc = text.getCaretPosition();
			text.insert(hms, loc);
		}
		if (command.equals("Arial")) {

			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font("Arial", fontStyle, fontSize);
			text.setFont(f);
		}
		if (command.equals("Calibri")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font("Calibri", fontStyle, fontSize);
			text.setFont(f);
		}
		if (command.equals("David")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font("David", fontStyle, fontSize);
			text.setFont(f);
		}

		if (command.equals("Georgia")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font("Georgia", fontStyle, fontSize);
			text.setFont(f);
			System.out.println(f.getFamily());
		}
		if (command.equals("Regular")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, Font.PLAIN, fontSize);
			text.setFont(f);
		}
		if (command.equals("Bold")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, Font.BOLD, fontSize);
			text.setFont(f);
		}
		if (command.equals("Italic")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, Font.ITALIC, fontSize);
			text.setFont(f);
		}
		if (command.equals("Bold Italic")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, Font.BOLD | Font.ITALIC, fontSize);
			text.setFont(f);
		}

		if (command.equals("12")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 12);
			text.setFont(f);
		}

		if (command.equals("18")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 14);
			text.setFont(f);
		}
		if (command.equals("24")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 18);
			text.setFont(f);
		}
		if (command.equals("28")) {
			String fontName = f.getName();
			String fontFamily = f.getFamily();
			int fontSize = f.getSize();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 20);
			text.setFont(f);
		}
		if (command.equals("Select All (Ctrl+A)")) {
			String strText = text.getText();
			int strLen = strText.length();
			text.select(0, strLen);
		}

	}
}