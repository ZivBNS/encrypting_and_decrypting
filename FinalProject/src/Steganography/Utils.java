package Steganography;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utils 
{
    public final static String gif = "gif";
    public final static String png = "png";
    public final static String dat = "dat";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {//בודק את סיומת הקובץ
        String ext = null;//פה ישמר סוג הקובץ
        String s = f.getName();//שם הקובץ
        int i = s.lastIndexOf('.');//כמות התווים שיש מהנקודה
        if (i > 0 &&  i < s.length() - 1) //אם קיים משהו אחרי הנקודה
        {
            ext = s.substring(i+1).toLowerCase();//חסר את השם והנקודה והחזר מה שנישאר
        }
        return ext;/// מה שנשאר זה בעצם מחרוזת של הסיומת של קובץ או תמונה
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) 
    {
        URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
