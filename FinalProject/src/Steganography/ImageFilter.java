package Steganography;
import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter 
{
	String filter;//תיאור הקובץ
	public ImageFilter(String f)
	{
		filter=f;
	}
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) //סינון על פי סוג קובץ
    {
        if (f.isDirectory()) //האם הקובץ הוא תיקיה
            return true;//אם כן החזר אמת
        String extension = Utils.getExtension(f);//החזר את סיומת הקובץ
        if(extension==null)//אם אין כזו
        {
        	System.out.println("There is no extention");//תודיע לי שאין סיומת לקובץ
        }
        else//אחרת
	        if(filter=="Images")//תבדוק האם הקובץ שווה לתמונה
			        if (extension.equals(Utils.gif) ||extension.equals(Utils.png)) //אם היא שווה לפורמט גיף או פי אן גי
		                return true;//החזר אמת
			        else {}
	        else//אחרת
	        	if(filter=="PublicKey")//בדוק האם הקובץ שווה למפתח
			        if (extension.equals(Utils.dat)) //האם קיימת סיומת של מפתח לקובץ זה
		                return true;//קיים החזר אמת
	
	        return false;//לא תמונה ולא מפתח וגם לא תיקיה החזר שקר
    }

    //The description of this filter
    public String getDescription() 
    {

        return null; //"Just Images"
    }
}
