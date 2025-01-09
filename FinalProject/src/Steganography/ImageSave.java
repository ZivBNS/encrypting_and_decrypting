package Steganography;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageSave 
{
	String ImageFormat;//מקבל פורמט של תמונה
	public ImageSave(BufferedImage img,File path,String format)//מקבל תמונה טעונה ומסלול ופורמט
	{
		ImageFormat=format;//מציב את הפורמט במשתנה המחלקה
		File OutputFile=getFileName(path);//מסלול התמונה הכוללת שם התמונה וסיומת
	    try
	    {
		    ImageIO.write(img, ImageFormat, OutputFile);//מנסה ליצור תמונה חדשה בשם שהתקבל וסיומת
		    System.out.println("Saved");//הודע לי על הצלחת השמירה
		} 
		catch (IOException e) //חריגה
		{
			e.printStackTrace();
			System.out.println("NOT Saved");//התמונה לא נשמרה
		}
	}
	/**
	 *<h3> getFileName </h3> 
		</br>
	 *private File getFileName(File p)
	 * <blockquote> Return the same path that was send plus suffix ImageFormat.  <br> </br>
	 * This function avoid the user inserting different formats than "png" or "gif",in addition 
	 * the user don't have to insert format,the png suffix is saved automatically </blockquote>
	 * @param p-a path of image we want to save.
	 * @return Same path and suffix ImageFormat.
	 */
	private File getFileName(File p)//מחזיר מסלול תמונה עם סיומת
	{
		File Path; //מסלול חש
		String ImageName = p.toString(); //שם התמונה עם כל המסלול
		int period=ImageName.indexOf('.'); //get the index of "." if there is no "." the function returns -1

		if(period!=-1)
		{
			ImageName=ImageName.substring(0,period);
			System.out.println("Image Name "+ImageName);
		}
		Path=new File(ImageName+"."+ImageFormat);
		return Path;
	}

}
