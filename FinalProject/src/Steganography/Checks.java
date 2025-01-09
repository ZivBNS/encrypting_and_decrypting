package Steganography;

import java.awt.Color;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;



import javax.swing.JFileChooser;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.ModernBalloonStyle;
import net.java.balloontip.utils.TimingUtils;

public class Checks 
{
	BalloonTip balloonTip;
	JFileChooser filechooser1,filechooser2,filechooser3;
	Interface window;
	BufferedImage ImageWithMessage;
	public Checks(Interface w)
	{
		window=w;
	}
	public void MouseEntered(String Name)//בודק מעבר עכבר על הכפתורים ומציג הודעות טיפ בהתאם
	{
		Color c=new Color(7,75,118);//צבע הבלון
		ModernBalloonStyle Modern = new ModernBalloonStyle(10,10,c,Color.white,Color.black);//רוחב, אורך,מילוי עליון, מילוי תחתון, צבע גבול
		
		Modern.setBorderThickness(2);//עובי הבורדר של הבלון
		Modern.enableAntiAliasing(true);
		//טיפול במעבר על כפתורים בחלון
		if(Name=="Browse")//אם נבחר עיון
			//Click this button in order to  Choose an image
			balloonTip = new BalloonTip(window.Browse, "<html><font color=black> Choose an image.</font></html>",Modern,false);//רכיב חלון, ההודעה, סוג עיצוב, אפשרות סגירה
		else
			if(Name=="BrowsePublic")//אם נבחר עיון מפתח
				balloonTip = new BalloonTip(window.BrowsePublic, "<html><font color=black> Click this button in order to choose a public key.</font></html>",Modern,false);//רכיב חלון, ההודעה, סוג עיצוב, אפשרות סגירה
			else
		balloonTip.setPadding(0);
		TimingUtils.showTimedBalloon(balloonTip, 5000);
	}
	public void mouseExited()//ברגע שאין מעבר עכבר על הכפתורים
	{
		balloonTip.closeBalloon();//סגור חלון טיפ
	}

	public void mouseReleased(String Name)//התמונה נלחצה
	{
		int returnVal=0;//ערך חלון העיון
		File path;//הקובץ הנבחר/נשמר
		if(Name=="Browse")//נלחץ כפתור העיון של התמונה
		{
			if(filechooser1==null)
			{
				filechooser1=new JFileChooser();//יוצר חלון עיון חדש המותחל במסמכים
				System.out.println("NEW");
			}
			
			filechooser1.setAcceptAllFileFilterUsed(false);//אי אפשור קבלת כל קובץ
			
			
			filechooser1.setFileFilter(new ImageFilter("Images"));//איפשור בחירה רק של תמונה על פי פורמטי ג'יף ופי אן ג'י
			filechooser1.setFileView(new ImageFileView());//שינוי האייקונים בחלון העיון על פי סוג הפורמט על פי שנקבע במחלקתImageFileView 
			
			filechooser1.setDialogTitle("Open an image");//כותרת החלון
			returnVal=filechooser1.showOpenDialog(window);//פותח חלון פופ אפ של עיון קבצים ומחזיר אם נסגר/בוטל/שגיאה בחלון העיון
			
			if(!IsFileChooserClosed(returnVal))//אם החלון לא נסגר
			{
				path=filechooser1.getSelectedFile();//מחזיר מחלון העיון את מסלול קובץ התמונה שנבחר
				//path.exists()
				if(path.canRead())//אם המסלול ניתן לקריאה
				{
					ImageWithMessage=GlobalClass.ReadImage(path);//הופך את הקובץ שנמצא במסלול לתמונה
					if(GlobalClass.ImageValidation(ImageWithMessage))//אם הצליחה טעינת התמונה
					{
						GlobalClass.ConvertImageToBytes(ImageWithMessage);//ממיר את התמונה למערך פיקסלים בבתים ושומר את מערך זה במערך bytes 
						window.TextPath.setText(path.toString());//
						if(window.ActioName=="Encode")
						{
							//check if the image is not too small,if it does than don't set the notes and the text box
							if(SetNoets(ImageWithMessage))//בדיקה אם אפשר להכניס תווים למסר
								window.setSize(726,457);
							else
								return;
						}
						else
							window.setSize(726,404);
						window.SetMessageBoxOrKeys();
						
					}
					else//לא הצליחה טעינת התמונה
					{
						window.setComponentsVisibilityToFalse();//מסתיר רכיבי חלון
						window.setSize(726,298);
						new Message(window,"ImageError");//מציג הודעה שהתמונה בלתי קריאה
						System.out.println("File Is Damaged");//מודיע שיש בעיה בקובץ התמונה הנבחר
						return;
					}

				}
				else//אי אפשר לקרוא את המסלול של הקובץ
				
					System.out.println("File not exsistence");//מודיע כי הקובץ לא נמצא
			}
		}
		else
				if(Name=="BrowsePublic")//נבחר חלון לעיון מפתח ציבורי
				{
					if(filechooser2==null)
						filechooser2=new JFileChooser("");//פותח חלון עיון חדש
					
					filechooser2.setAcceptAllFileFilterUsed(false);//לא מקבל את כל הפורמטים
					filechooser2.setFileFilter(new ImageFilter("PublicKey"));//אלא רק מפתח ציבורי, כלומר קבצי DAT
					//?
					filechooser2.setDialogTitle("Open a public key");//כותרת החלון
					returnVal=filechooser2.showOpenDialog(window);// פתיחה:ערך שהוחזר מחלון העיון
					if(!IsFileChooserClosed(returnVal))//אם לא נסגר החלון על ידי המשתמש
					{
						path=filechooser2.getSelectedFile();//מקבל את מסלול קובץ המפתח
						window.PublicBox.setText(path.toString());
	
						window.SetEncodeOrDecode();//סידור כפתור הצפנה/פענוח בהתאם
						window.setSize(726,404);
					}
				}
				else
					if(Name=="Encode")//בחירת כפתור ההצפנה
					{
						if(filechooser3==null)
							filechooser3=new JFileChooser();//פותח חלון עיון חדש בנקודת תקיה מסמכים
						filechooser3.setDialogTitle("Save image");//כותרת החלון
						filechooser3.setFileView(new ImageFileView());//משנה את האייקונים על פי פורמט תמונה
						returnVal=filechooser3.showSaveDialog(window);//שמירה: ערך שהוחזר מחלון העיון
						if(!IsFileChooserClosed(returnVal))//אם החלון לא נסגר
						{
							FirstUse();//מייצר מפתחות חדשים אם לא קיימים כאלו 
							String ImageForamt=getImageFormat(window.TextPath.getText());//מקבל מטקסט מסלול את פורמט התמונה
							new File(window.TextPath.getText());
							new Encode(window.Text.getText());//מקבל מטקסט את ההודעה שצריכה להיות מוצפנת ומתחיל הצפנה
							path=filechooser3.getSelectedFile();//מקבל מחלון העיון את המסלול קובץ שנשמר
					
							if(GlobalClass.what_message=="Encode")//אם ההצפנה הצליחה מודיע בהתאם
								new ImageSave(ImageWithMessage,path,ImageForamt);//שומר את קובץ התמונה לImageWithMessage
							new Message(window,GlobalClass.what_message);//מודיעה למשתמש שההצפנה הצליחה
						}
						
					}
					else
						if(Name=="Decode")//אם נבחר כפתור הפענוח
						{
							Decode decode=new Decode(window.TextPath.getText(),window.PublicBox.getText());//מקבל את מסלול התמונה ומסלול המפתח הציבורי
							String MessageExistent=decode.IsThereAMssage();//בודק אם הוצפן מסר
							
							
							new Message(window,MessageExistent);//מחזיר הודעה בהתאם
							if(MessageExistent!="false")//אם נמצאה תמונה
							try 
							{
								//System.out.println(MessageExistent);
								Runtime.getRuntime().exec("notepad "+ MessageExistent+"");//יוצר תהליך נפרד לשמירת ההודעה שנמצאה בתמונה לתוך קובץ טקסט
								//Runtime.getRuntime().exec("notepad C:\\Users\\zivbs\\Documents\\בית ספר\\FinalProject\\Decoded Messages\\"+decode.SavedFileName+".txt");//יוצר תהליך נפרד לשמירת ההודעה שנמצאה בתמונה לתוך קובץ טקסט
							} 
							catch (IOException e)//אם נמצאה שגיאה במהלך יצירת קובץ הטקסט 
							{
								e.printStackTrace();
							}
						}
	}
	public Boolean IsFileChooserClosed(int returnVal)//בודק אם חלון נסגר
	{
        if (returnVal == JFileChooser.APPROVE_OPTION)//בודק אם החלון אושר 
        	return false;//מחזיר שקר        
        else//אחרת בוטל על ידי המשתמש 
        	System.out.println("Attachment cancelled by user.");//מודיע שהחלון עיון נסגר על ידי המשתמש
        return true;//מחזיר אמת, החלון נסגר
	}
	public boolean CheckTextMessage()//שיטה הבודקת אם נכתב מסר
	{
		int MsgLength=window.Text.getText().trim().length();//מחזיר את אורך המסר שהתקבל מהמשתמש
		if(MsgLength==0)//נמצא שלא נכתב מסר
		{
			System.out.println("sapce");
			return false;//מחזיר שקר	
		}
		return true;//יש הודעה
	}
	public void FirstUse()//מייצר מפתחות חדשים או משאיר את המסלול המפתח הציבורי כפי שקיים אחד
	{
		File PublicKey=new File("../Keys/Public Key.dat");//מציב את מסלול המפתח הציבורי
		if(PublicKey.canRead())//האם אפשר לקרוא ממפתח זה
		{
			System.out.println("Keys already created");//אם כן הודעה שקיימים כבר מפתחות
		}
		else//אם לא צור מפתחות חדשים
		{
			System.out.println("First use: Keys created automatically");//אין מפתחות לכן מיוצרים חדשים אוטומתית
			new GenerateKeys();//יוצר מפתחות חדשים
		}
	}
	public boolean SetNoets(BufferedImage img)//השיטה בודקת האם ניתן להכניס תווים למסר התמונה ומעדכנת את כמות התווים שעוד ניתן להכניס במסר 
	{
		int AmountOfNoets=(GlobalClass.bytes.length-1104)/8;//כמות הבתים שיש להוריד על פי ההסבר למטה

		/*
		                  On Computer(Regular)   After Encoding   
		  
		 	StartSign        -    2 bytes 				    2*8=16    bytes
		 	Signature Length -    4 byte                    4*8=32    bytes
		 	Signature        -   (Signature Length) bytes	(Signature Length)*8  bytes max 128*8
		 	TextLength       -    4 bytes				    4*8=32    bytes
		 	Message          -    n bytes				    n*8=8n    bytes
		 	
		 	Message -   (img.getWidth()*img.getHeight()*3-(16+32+1024+32) )/8    
		 */
		if(AmountOfNoets<=0)//אם לא ניתן לכתוב דבר
		{
			window.setComponentsVisibilityToFalse();//מסתיר רכיבים
			window.setSize(726,303);
			new Message(window,"SmallImage");//תמונה קטנה מדי
			
			
			System.out.println("Image is too small");//מודיע שהתמונה קטנה מדי
			return false;//שקר-לא ניתן להכניס תווים והתמונה קטנה
		}
		else
			if(AmountOfNoets<5000000)//יש מספיק מקום לכתיבת מסר
			{
				window.NotesLeft=AmountOfNoets;//מציב את מספר התווים שאפשר עוד להכניס
				window.Title2.setTitle("Notes left: "+window.NotesLeft);//מציג בחלון Notes left: n
				window.Text.setDocument(window.new JTextFieldLimit(AmountOfNoets));//משנה את מספר התווים שאפשר עוד להכניס בהתאם לשינויים בחלון הכתיבה
				window.Text.getDocument().addDocumentListener(window);//מוסיף מאזין
				window.AreaJScrollPane.setBorder(window.b);
				window.AreaJScrollPane.setBorder(window.border1);
				window.validate();
			}
		return true;//אמת-יש מספיק מקום להכניס תווים בתמונה
	
	}
	public String getImageFormat(String p)//השיטה מקבלת מסלול תמונה ומחזירה את הפורמט שלה
	{
		String format;//כאן יוצב הפורמט
		String ImageName = p; //כל המסלול נכנס לשם תמונה
		int period=ImageName.indexOf('.');//מיקום הנקודה המייצג מעבר לתאיור פורמט
		format=ImageName.substring(period+1, ImageName.length());//מחסיר את כל התווים מהנקודה ועד התחלת השם וכך מוחזרת מחרוזת של פורמט התמונה
		System.out.println("Format is: "+format);//הודע איזה פורמט
		return format;//החזר פורמט
	}

}
