package Steganography;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.Signature;


public class GenerateSignature 
{
	byte[] Message;//ההודעה של איש הקשר בבתים
	static byte [] sig;//החתימה של איש הקשר בבתים
	public GenerateSignature(byte[] message,PrivateKey PrivateKey)
	{
        try
        {
        	Message=message;//המסר הסודי שהמשתמש הקליד
            Signature signer = Signature.getInstance("RSA");//יוצר חתימה בשיטת אר אס איי
            
            signer.initSign(PrivateKey);//מכניס את המפתח הפרטי לחתימה
            signer.update( Message );//מכנס את הודעת המשתמש לחתימה
            sig = signer.sign();//מכניס את אורך כל החתימה והמסר בבתים
        }
        catch( Exception e )
        {
            e.printStackTrace( System.out );
        }
	}
	public byte[] getSignature()//החזר את החתימה
	{
		return sig;
	}
	static public boolean verification(byte[] Sig,PublicKey PublicKey,byte [] message)//השיטה בודקת התאמה בין המפתחות ומודיע בהתאם
	{
		boolean sigIsOK=false;
		try
		{			
			Signature verifier = Signature.getInstance("RSA");//יוצר חתימה בשיטת אר אס איי
	        verifier.initVerify( PublicKey );//מכניס את המפתח הפרטי לחתימה
	        verifier.update( message );//מקבל את המסר הסודי
	        sigIsOK = verifier.verify( Sig );//בודק תאימות בין המפתחות   
	        //SigIsOK ?(if(true))""(else):
	        System.out.println( (sigIsOK ? "" : "does not ")+"verify" );//הודע אם אושר או לא
		}
        catch( Exception e )
        {
            e.printStackTrace( System.out );
        }
        return sigIsOK;
	}
}
