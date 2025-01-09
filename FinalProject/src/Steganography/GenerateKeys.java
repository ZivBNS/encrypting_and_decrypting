package Steganography;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class GenerateKeys 
{
	private PrivateKey PrivateKey;//מפתח פרטי
	private PublicKey PublicKey;//מפתח ציבורי
	public GenerateKeys()
	{
        try
        {
        	Security.addProvider(new BouncyCastleProvider());//הספק של האר אס איי והוא נקרא באונסי קאסל
        	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");//מייצר מפתחות בשיטת אר אס איי
        	kpg.initialize(1024, new java.security.SecureRandom());//מאתחל מפתחות
        	
	        
	        KeyPair kp = kpg.generateKeyPair();//מיצר את המפתחות
	        PublicKey = kp.getPublic();//קבלת מפתח פומבי
	        PrivateKey = kp.getPrivate();//קבלת מפתח פרטי
	
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Keys/Public Key.dat",false ) );//יצירת קובץ מפתח ציבורי
	        oos.writeObject(PublicKey);//הכנסת הדאטה לקובץ
	        oos.close();//סגירת קובץ
	        
	        oos = new ObjectOutputStream(new FileOutputStream("Keys/Private Key.dat", false ) );//יצירת קובץ מפתח פרטי
	        oos.writeObject(PrivateKey);//הכנסת הדאטה לקובץ
	        oos.flush();//מוודא שהקובץ הולך למקום הנכון

	        oos.close();//סגירת קובץ
	
        }
        catch( Exception e )//חריגה
        {
            e.printStackTrace( System.out );
        }
	}
	public PrivateKey getPriavtekey()//החזר מפתח פרטי
	{
		return PrivateKey;
	}
	public PublicKey getPublickey()//החזר מפתח ציבורי
	{
		return PublicKey;
	}
}
