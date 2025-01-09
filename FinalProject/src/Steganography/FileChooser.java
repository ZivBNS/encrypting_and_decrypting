package Steganography;
import java.io.File;
import javax.swing.JFileChooser;



public class FileChooser //extends JPanel 
{
	JFileChooser fc;//מאפשר את חלון הבחירה
	int returnVal;//מצב הפעולה
	File ImageFile;//שומר את קובץ התמונה
	public FileChooser(String Operation)
	{	
		ImageFile=null;//איפוס קובץ
		fc = new JFileChooser();//יוצר חלון בחירה
		if(Operation.equals("Open"))//חלון נפתח
			returnVal=fc.showOpenDialog(null);//קבל ערך של הפתיחה
		else//נבחר קובץ
			returnVal=fc.showSaveDialog(null);//קבל ערך שמירה
	}
	public boolean IsFileChooserClosed()//האם נסגר חלון העיון
	{
		System.out.println(returnVal);
        if (returnVal == JFileChooser.APPROVE_OPTION)//אם לא סגור 
        {
        	return false;//שקר חלון העיון פתוח
        } 
        else//כן סגור 
        {
        	System.out.println("Attachment cancelled by user.");//נסגר על ידי המשתמש
        }

        //Reset the file chooser for the next time it's shown.
        //don't have to reset it because it can be more comfortable
        return true;
	}
	public void setImageFile()//מציב את הקובץ הנבחר
	{
		ImageFile = fc.getSelectedFile();//לוקח קובץ שנבחר על ידי המשתמש ומציב אותו במשתנה זה
	}
	public File getInputFile()//החזר את הקובץ
	{
		return ImageFile;
	}
	
}
