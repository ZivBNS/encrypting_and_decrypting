package Steganography;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GlobalClass 
{
	static BufferedImage img;
	static String what_message="Encode";
	static byte[] bytes;
	static final int Size_Of_StartSign = 2; //תמיד 2 כי משתמש בשני תווים בלבד
	
	static final int Size_Of_One_Char_In_EncodeText = 8; //the size of one char //1 char (8 bits) stored in 8 bytes. each last bit in each byte presents 1 bit in a char respectively 
	static final int Size_Of_Int_In_Bytes = 4; //the size of integer in bytes

	public static void ConvertImageToBytes(Image i)
	{
		img=(BufferedImage)i;//טוען את התמונה
		bytes=accessBytes(img);//הופך את התמונה למערך פיקסלים בבתים
	}
	private static byte[] accessBytes(BufferedImage image)
	{
		WritableRaster raster = image.getRaster(); //צורת טעינה של פיקסלים מתוך תמונה טעונה
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();//טעינה של הפיקסלים והפיכתם למערך בתים על ידי קאסטינג לדטה בית
		return buffer.getData();//מחזיר את מערך הביתים של הפיקסלים של התמונה	
	}
	static public BufferedImage ReadImage(File Path)//טוען את התמונה על פי המסלול
	{
		BufferedImage ImageRead=null;
			try 
			{
				ImageRead=ImageIO.read(Path);//מנסה לטעון את התמונה
			} 	
			catch(IllegalArgumentException e)
			{
				System.out.println("Path is null!");///לא נמצאה תמונה במסלול
			}
			catch (IOException e) 
			{
				System.out.println("Error occured during reading!");///חריגה
			}
			return ImageRead;//מחזיר תמונה טעונה
	}
	public static boolean ImageValidation(BufferedImage img)//בודק אם התמונה ריקה 
	{
		if(img!=null)
			return true;
		System.out.println("false");
		return false;
	}
}
