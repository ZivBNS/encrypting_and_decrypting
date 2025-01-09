package Steganography;
import javax.swing.JOptionPane;


public class Message 
{
	int Command;
	public Message(Interface MainWindow,String Operation)//הודעות בהתאם לאירועים
	{
		
		if(Operation=="ImageError")
			JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4>  Image can't be read   </font>  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//כשקובץ ריק ולא ניתן לקריאה
		else
		if(Operation=="SmallImage")
			JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4>  Image is too small</font></html>","Pay attention",JOptionPane.WARNING_MESSAGE);//כשתמונה קטנה מדי בשביל להכניס בה מסר
		else
			if(Operation=="KeyError")
				JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4> Check your contact's publick key </font>  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//כשהמפתח לא חוקי
			else
				if(Operation=="Private Key")
					JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4> Keys are damaged.</font> <br> Create new ones,go to menu-bar: File-->New keys  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//מפתחות פגומים
				else
			if(Operation=="Keys")
			{
				Command=JOptionPane.showConfirmDialog(MainWindow,"<html> <Font size=4 color=red> Creating new keys will replace the previous ones </Font> </html> \n Are you sure? ","Pay Attenation",JOptionPane.YES_NO_OPTION);//החלפת מפתחות במפתחות חדשים
				CheckCommand();//יצירה של מפתחות חדשים בהתאם
			}
			else
				if(Operation=="Encode")
				{
					JOptionPane.showMessageDialog(MainWindow,"Message has been encrypted","Confrim saving",JOptionPane.INFORMATION_MESSAGE);//צלחה ההצפנה
				}
				else
					//Decode
					if(Operation=="false")
						JOptionPane.showMessageDialog(MainWindow,"<html> Message <font size=4> not found </font><br> Check your image/contac't public key.</html>","Pay attention",JOptionPane.WARNING_MESSAGE);//לא נמצא מסר בתמונה
					else
					{
						System.out.println("\n"+Operation);
						JOptionPane.showMessageDialog(MainWindow,"<html> Message <font size=4>  found </font> <br>Path:" +Operation +"  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//נמצא מסר
					}
	}
	public boolean CheckCommand()
	{
		if(Command==JOptionPane.YES_OPTION)//אם כן יצר מפתחות חדשים
		{
			JOptionPane.showMessageDialog(null,"Keys have been created successfully","Confrim generation",JOptionPane.INFORMATION_MESSAGE);//המפתחות נוצרו בהצלחה
			System.out.println("New Keys Created");//הודעה על כך
			new GenerateKeys();//יצר מפתחות
			return true;//אמת נוצרו מפתחות
		}
		else
			if(Command==JOptionPane.NO_OPTION)//אם לא
				System.out.println("Keys generation canceled");//תודיע שהמשתמש ביטל את היצירה של מפתחות חדשים
		
		return false;//החזר שקר לא נוצרו מפתחות חדשים
	}
}
