package Steganography;
import java.awt.image.BufferedImage;
import java.security.PrivateKey;



public class Encode 
{
	BufferedImage ImageWithMessage;
	public Encode(String Message)
	{
			System.out.println(GlobalClass.bytes.length);
			Hide(GlobalClass.bytes,Message);
	}

	public void Hide(byte[] bytes,String Text)
	{
		byte[] TextInBytes=ConvertTextToBytes(Text);//המר את המסר הסודי לבתים יחד עם החתימה והכל
		if(TextInBytes==null)//הייתה שגיאה ולא צלחה השיטה
		{
			System.out.println("Error accured while converting text to bytes array");
			return;
		}
		int Offset=0;//מיקום בתים בתמונה
		System.out.println(TextInBytes[Offset]);
		System.out.println(bytes.length+" is the bytes length");
		for (int i = 0; i <TextInBytes.length; i++)
			for(int j=7; j>=0; j--)
			{
				byte BitValue=(byte)((TextInBytes[i]>>j) & 1);//לוקח סיבית אחת מתוך בית של הטקסט במיקום סיבית ג'יי
				System.out.println(bytes[Offset]);
				bytes[Offset] = (byte)((bytes[Offset] & 0xFE) | BitValue);//מכניס את סיבית  הטקסט לסיבית הכי פחות משמעותית של התמונה
				Offset++;//מתקדם לבית הבא
			}	
		System.out.println("Offset="+Offset);
	}
	public byte[] ConvertTextToBytes(String Text) //מחזיר את הטקסט + חתימה בבתים
	{

		
		
		byte[] StartSign=("$<").getBytes();//קוד הסימןנים באסקי ההניתנים במספרים 36=$ 60+בפירוט

		
		byte[] MessageLengthInBytes=convertIntToByteArray(Text.length());//אורך המסר בבתים
		byte[] TextInBytes=(Text).getBytes();//המסר עצמו בבתים

		
		//read the private key
		//put it in GenerateSignature
		GetKey Read_Key;//מייצג את מסלול המפתח הפרטי
		PrivateKey PrivateKey;
		if( (Read_Key=new GetKey()).IsFileCanBeRead(Read_Key.getKeyPath()) )//בודק אם המסלול תקין
			 PrivateKey=Read_Key.getPrivateKey();//אם כן מציב את המפתח הפרטי
		else
		{
			GlobalClass.what_message="Private Key";
			return null;
		}
		//Create a signature
		byte [] SigInBytes=new GenerateSignature(TextInBytes,PrivateKey).getSignature();//מחזיר את המסר ומפתח פרטי בתור חתימה
		
		byte[] SigLengthInBytes=convertIntToByteArray(SigInBytes.length);//אורך החתימה בבתים

		byte[] TotalText=new byte[StartSign.length+SigLengthInBytes.length+SigInBytes.length+MessageLengthInBytes.length+TextInBytes.length];//סדר הופעת הנתונים של הטקסט הסופי שיופיע בתמונה
		//סימן ההתחלה והוא $< ולאחריו יופיע אורך החתימה בבתים ואז החתימה עצמה ואז אורך הטקסט בבתים ואז המסר הסודי בעצמו
		
		System.out.println("TotalText Length="+TotalText.length);//אורך כל מה שציינתי מעל
		System.out.println("StartSign.length="+StartSign.length);//אורך סימן ההתחלה והוא תמיד 2 תאים
		System.out.println("SigLengthInBytes.length="+SigLengthInBytes.length);//אורך החתימה בבתים
		System.out.println("SigInBytes.length="+SigInBytes.length);//אורך החתימה
		System.out.println("MessageLengthInBytes.length="+MessageLengthInBytes.length);//אורך המסר בבתים
		System.out.println("TextInBytes.length="+TextInBytes.length);//המסר בבתים
		//Combine the StartSign to the TotalText 
		System.arraycopy(StartSign,0,TotalText,0,StartSign.length);//מוסיף את סימן ההתחלה
		
		//Combine the Length Of Signature to the TotalText 
		System.out.println("Start Copying Length Signature from="+StartSign.length);
		System.arraycopy(SigLengthInBytes,0,TotalText,StartSign.length,SigLengthInBytes.length);//הוספת אורך החתימה
		
		//Combine the Signature to the TotalText 
		System.out.println("Start Copying Signature from="+StartSign.length);
		System.arraycopy(SigInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length),SigInBytes.length);//הוספת החתימה
		
		//Combine the TextLength into TotalText
		System.out.println("Start Copying MessageLengthInBytes from="+(SigInBytes.length+StartSign.length));
		System.arraycopy(MessageLengthInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length+SigInBytes.length),MessageLengthInBytes.length);//הוספת אורך המסר
		//Combine the TextInBytes into TotalText
		System.out.println("Start Copying TextInBytes from="+(StartSign.length+SigInBytes.length+MessageLengthInBytes.length));
		System.arraycopy(TextInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length+SigInBytes.length+MessageLengthInBytes.length),TextInBytes.length);//הוספת המסר הסודי
		
		return TotalText;// מחזיר את מערך הבתים של כל הטקסט שצריך להיות מוחבה
	}
	public static byte[] convertIntToByteArray(int integer)
	{
		System.out.println("Length of Message "+integer);
		byte[] bytes = new byte[GlobalClass.Size_Of_Int_In_Bytes]; 
		bytes[0] = (byte)(integer >> 24);
        bytes[1] = (byte)(integer >> 16);
        bytes[2] = (byte)(integer >> 8 );
        bytes[3] = (byte)(integer);
        
        System.out.println("bytes[0]="+bytes[0]);
        System.out.println("bytes[1]="+bytes[1]);
        System.out.println("bytes[2]="+bytes[2]);
        System.out.println("bytes[3]="+bytes[3]);
        
        
        return bytes;
	}
}
