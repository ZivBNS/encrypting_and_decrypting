package Steganography;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import java.security.PublicKey;


public class Decode 
{
	int Offset;//היסט בבתי התמונה
	BufferedImage Image;//טעינת התמונה
	String PathOfPublicKey;//יהווה את המסלול של המפתח הציבורי
	String Path;//יהווה את מסלול התמונה בעלת המסר הסודי
	File InputFile;//ישמש לקריאת קובץ התמונה המוצפנת
	String IsThereMessage;//לבדיקה אם יש מסר בתמונה או לא
	String SavedFileName;//משמש כשם לקובץ
	File file;//קובץ הטקסט שאליו ישמר המסר הסודי
	public Decode(String ImagePath,String PathOfPublicKey)//מחלקה מקבלת את מסלול התמונה ואת מסלול המפתח הציבורי
	{
		Path=ImagePath;//מקבל את מסלול התמונה
		InputFile=new File(Path);//מקבל את הקובץ של התמונה על פי המסלול שצויין
		Image=GlobalClass.ReadImage(InputFile);//מחזיר את התמונה שנטענה מהקובץ
		this.PathOfPublicKey=PathOfPublicKey;//מקבל את מסלול המפתח הציבורי
		Offset=0;//מהתחל את ההיסט ל0
		Reveal(GlobalClass.bytes);
	}
	public void Reveal(byte[] bytes)//טענת כניסה: הפונקציה מקבלת את מערך הבתים של התמונה בה מוצפן המסר
	//פעולת הפונקציה: בודקת אם קיימת בכלל הודעה, אם כן מחלצת את החתימה ובודקת תאימות בין המפתחות ואז מחלצת את ההודעה לקובץ טקסט, אם לא מחזירה ערך שקר במשתנה "יש הודעה"
	{
		byte[] StartSign=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_StartSign);//מקבל את המערך של התמונה המוצפנת ואת גודל התווים הבודקים האם הוצפנה בכלל הודעה
		if(StartSign[0]!='$' && StartSign[1]!='<')//הסימנים שנקבעו מראש לשם בדיקה האם הודעה מוצפנת או לא (הוגדר במחלקת הצפנה
		{
			System.out.println("NO message FOUND");//אם לא נמצא בתמונה מערך זה אין כניראה מסר
			IsThereMessage="false";//אין מסר
		}
		else//אם כן נמצאו סימנים אלו במערך שחולץ מהתמונה יש מסר מוצפן
		{
				
			System.out.println("Threre is a message");//מודיע על מציאת מסר
		
			byte[] SignLengthInBytes=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_Int_In_Bytes);//מחלץ את גודל מערך החתימה

			int SignLength=convertByteArrayToInt(SignLengthInBytes);//מכניס למשצתנה זה את הגודל
			
			byte [] Signature=ExtractHiddenBytes(bytes,SignLength);//מחלץ את החתימה מהתמונה
			

			byte[] LengthInBytes=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_Int_In_Bytes);//חלץ את גודל מערך המסר
			int Length=convertByteArrayToInt(LengthInBytes);//מכניס למשתנה זה את הגודל

			byte Message[]=ExtractHiddenBytes(bytes,Length);//מחלץ מהתמונה את המסר הסודי
			
			GetKey Read_Key;//
			PublicKey PublicKey;//פה נציב את המפתח הציבורי שיתקבל
			if( (Read_Key=new GetKey(PathOfPublicKey)).IsFileCanBeRead(PathOfPublicKey) )//בדוק האם המסלול חוקי והאם קיים מפתח
				PublicKey=Read_Key.getPublicKey();//הצב את המפתח הציבורי שנמצא
			else//מסלול לא תקין
			{
				new Message(null,"Public Key Error");//הודעה שיש בעיה במפתח הציבורי
				return;
			}

			
			if(GenerateSignature.verification(Signature,PublicKey,Message))//בודק שהמפתחות תואמים ומדפיס את המסר
			{
				IsThereMessage="true";//יש מסר
				System.out.print("The Encode Message is: ");//והמסר הוא
				for(int i=0; i<Length; i++)
					System.out.print((char)Message[i]);//ממיר את הבתים לתווים ומדפיס אותם לפי סדר הופעתם
				
				
				//Save Message To File
				try
		        {
					int EndIndex=InputFile.getName().indexOf(".");//מחשב את כמות התווים מהנקודה וסיומת התמונה
					SavedFileName=InputFile.getName().substring(0, EndIndex);//מוריד את הנקודה והסיומת בשביל לשמור את שם קובץ הטקסט בשם קובץ התמונה
					
					file=new File("Decoded Messages/"+SavedFileName+".txt");//יוצר את קובץ הטקסט לתיקיה של מסרים שפוענחו ושומר אותו על פי שם התמונה שהתקבלה
					FileOutputStream OutputFileStream = new FileOutputStream("Decoded Messages/"+SavedFileName+".txt" );//יוצר את ההזרמה לכתיבת תוכן לקובץ
					OutputFileStream.write(Message);//כותב את ההודעה לתוך הקובץ
					OutputFileStream.close();//סגירה
					
		        }
		      catch( Exception e )//שגיאה במהלך יצירת הקובץ או כתיבה אליו
		        {
		            e.printStackTrace( System.out );
		            System.out.println("Could not save the decoded message to the txt file");
		        }
			}
			else
				IsThereMessage="false";//אין הודעה
		}
	}
	public byte[] ExtractHiddenBytes(byte[] bytes,int Size)//מחלץ את הסיבית הכי פחות משמעותית בכל בית בתמונה ומחזיר מערך בתים של מה שחולץ
	{
		byte[] HiddenBytes=new byte[Size];//מערך הבתים שיוחזר
		for(int i=0; i<Size; i++)//מיקום במערך שיוחזר
			for(int j=0; j<8; j++)//הכנסת סיביות
			{
				HiddenBytes[i] = (byte) ((HiddenBytes[i] << 1) | (bytes[Offset] & 1)); //this part: (b[i] << 1)  comes in order to mov to bit to the higher place
				Offset++;//התקדמות לבית הבא
			}
		return HiddenBytes;//מחזיר את מערך הבתים שחולץ
	}
    public static int convertByteArrayToInt(byte[] integerInBytes)
	{
    	int[] byteToInt;
    	int result;
    	byteToInt = new int[GlobalClass.Size_Of_Int_In_Bytes]; 
		byteToInt[0] = (  integerInBytes[0] <<  24 );
		byteToInt[1] = ( (integerInBytes[1]& 0xff) << 16);
		byteToInt[2] = ( (integerInBytes[2]& 0xff) << 8 );
		byteToInt[3] = (  integerInBytes[3]& 0xff );	
		
		System.out.println("byteToInt[0]="+byteToInt[0]);
		System.out.println("byteToInt[1]="+byteToInt[1]);
		System.out.println("byteToInt[2]="+byteToInt[2]);
		System.out.println("byteToInt[3]="+byteToInt[3]);
		
		result=byteToInt[0]|byteToInt[1]|byteToInt[2]|byteToInt[3];
		return result;
	}
    public String IsThereAMssage()
    {
    	if(IsThereMessage=="true")
    		return file.getAbsolutePath(); ;
    	return "false";
    }
}
