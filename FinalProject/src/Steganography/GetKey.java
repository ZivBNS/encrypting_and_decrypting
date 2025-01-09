package Steganography;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;


public class GetKey 
{
	private String Path="Keys/Private Key.dat";//מסלול של המפתח הפרטי
	private PrivateKey PrivateKey=null;//מפתח פרטי
	private PublicKey PublicKey=null;//מפתח ציבורי
	private Object Object = null;//משמש לטעינה של הקובץ
	
	public GetKey(){}//כאשר הבנאי נקרא ריק כמו באחת הפונקציות באנקוד, נקרא המפתח הפרטי
	
	public GetKey(String path)
	{
		Path=path;
	}
	public boolean IsFileCanBeRead(String Path)//האם הקובץ מפתח קריא
	{
		File file=new File(Path);//הקובץ עם המסלול
		if(file.canRead())//האם קריא?
		{
			ObjectInputStream inputStream = null;//איפוס של טעינה לקובץ
			try 
			{
				//??????????
				//threre is a problem when i change the content in the private key
				//i'm trying to get a message that points to the problem
				//when the user tries to insert a private key instead of a public key 
				inputStream = new ObjectInputStream(new FileInputStream(Path));//טעינה לקובץ
			} 
			catch(EOFException e)
			{
				System.out.println("ends");//סוף הקובץ
			}
			catch (Exception e1)
			{
				e1.printStackTrace();//חריגה
			} 
			
					try 
					{
						Object = inputStream.readObject();//נסה לקרוא את הקובץ ולהציב את האובייקט שלו באובייקט המחלקה
					} 
					catch(Exception e)
					{
						
						System.out.println("EXCEPTION");//חריגה
						return false;
					}
				
					if(Path!="Keys/Private Key.dat")//אם המסלול הוא לא של המפתח הפרטי
					{
						System.out.println("Public Key!!");//זה מפתח ציבורי
						try
						{
							PublicKey=(PublicKey)Object;//נסה לקבל את המפתח הציבורי מהקובץ אותו טענו קודם
						}
						catch(ClassCastException e)//אין תוכן
						{
							return false;//הייתה שגיאה במהלך הקאסטינג והמפתח לא נמצא
						}
					}
					else
					{
						System.out.println("Path="+Path);//הדפס את המסלול
						PrivateKey=(PrivateKey)Object;//טען את המפתח הפרטי מהקובץ
					}
				try 
				{
					
					inputStream.close();//סוגר את הקובץ
					return true;
				} 
				catch (IOException e) {e.printStackTrace();}//חריגה
		}
		else
		{
			System.out.println("A Message window should be open");
			return false;
		}
		return true;
		

	}
	public String getKeyPath()//החזר את מסלולו של המפתח
	{
		return Path;
	}
	public PrivateKey getPrivateKey()//החזר את המפתח הפרטי
	{
		return PrivateKey;
	}
	public PublicKey getPublicKey()//החזר את המפתח הציבורי
	{
		return PublicKey;
	}	
}
