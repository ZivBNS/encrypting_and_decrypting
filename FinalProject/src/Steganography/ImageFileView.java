package Steganography;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFileView extends FileView {
    ImageIcon gifIcon = Utils.createImageIcon("../Icon Format/gifIcon.gif");//אייקון לפורמט גיף
    ImageIcon pngIcon = Utils.createImageIcon("../Icon Format/pngIcon.png");//אייקון לפורמט פי אן גי
    

    
    public String getName(File f) {//החזר את שם הקובץ
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File f) {//החזר את תיאור הקובץ
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {//האם הצליח או לא הצליח
        return null; //let the L&F FileView figure this out
    }

    public String getTypeDescription(File f) {//מחזיר את תיאור התמונה כלומר מה הסוג שלה
        String extension = Utils.getExtension(f);//מקבל סיומת קובץ על ידי מחלקת יוטילס
        String type = null;//יחזיר את סוג התמונה

        if (extension != null) //אם קיימת סיומת לקובץ
        {
        	if (extension.equals(Utils.gif))//והוא שווה לפורמט גיף
        	{
        		type = "GIF Image";//סוג התמונה הוא גיף
        	} 
        	else 
    			if (extension.equals(Utils.png))//אם הסיומת שווה לפיאנגי
    			{
    				type = "PNG Image";//סוג התמונה הוא פי אן גי
    			}
        }
        return type;//החזר את סוג התמונה
    }

    public Icon getIcon(File f) 
    {
        String extension = Utils.getExtension(f);//מחזיר את סיומת התמונה
        Icon icon = null;//האייקון שיוחזר

        if (extension != null) //אם נמצא סיומת
        {
            	if (extension.equals(Utils.gif)) //והיא שווה לפורמט גיף
            	{
            		icon = gifIcon;//האייקון של התמונה יהיה אייקון גיף
            	} 
            	else 

	            		if (extension.equals(Utils.png)) //אחרת אם שווה הסיומת לפי אן גי
	            		{
	            			icon = pngIcon;//האייקון של התמונה יהיה פי אן גי
	            		}
        }
        return icon;//החזר את האייקון
    }
}