package Steganography;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import java.security.PublicKey;


public class Decode 
{
	int Offset;//���� ���� ������
	BufferedImage Image;//����� ������
	String PathOfPublicKey;//����� �� ������ �� ����� �������
	String Path;//����� �� ����� ������ ���� ���� �����
	File InputFile;//���� ������ ���� ������ �������
	String IsThereMessage;//������ �� �� ��� ������ �� ��
	String SavedFileName;//���� ��� �����
	File file;//���� ����� ����� ���� ���� �����
	public Decode(String ImagePath,String PathOfPublicKey)//����� ����� �� ����� ������ ��� ����� ����� �������
	{
		Path=ImagePath;//���� �� ����� ������
		InputFile=new File(Path);//���� �� ����� �� ������ �� �� ������ ������
		Image=GlobalClass.ReadImage(InputFile);//����� �� ������ ������ ������
		this.PathOfPublicKey=PathOfPublicKey;//���� �� ����� ����� �������
		Offset=0;//����� �� ����� �0
		Reveal(GlobalClass.bytes);
	}
	public void Reveal(byte[] bytes)//���� �����: �������� ����� �� ���� ����� �� ������ �� ����� ����
	//����� ��������: ����� �� ����� ���� �����, �� �� ����� �� ������ ������ ������ ��� ������� ��� ����� �� ������ ����� ����, �� �� ������ ��� ��� ������ "�� �����"
	{
		byte[] StartSign=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_StartSign);//���� �� ����� �� ������ ������� ��� ���� ������ ������� ��� ������ ���� �����
		if(StartSign[0]!='$' && StartSign[1]!='<')//������� ������ ���� ��� ����� ��� ����� ������ �� �� (����� ������ �����
		{
			System.out.println("NO message FOUND");//�� �� ���� ������ ���� �� ��� ������ ���
			IsThereMessage="false";//��� ���
		}
		else//�� �� ����� ������ ��� ����� ����� ������� �� ��� �����
		{
				
			System.out.println("Threre is a message");//����� �� ����� ���
		
			byte[] SignLengthInBytes=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_Int_In_Bytes);//���� �� ���� ���� ������

			int SignLength=convertByteArrayToInt(SignLengthInBytes);//����� ������� �� �� �����
			
			byte [] Signature=ExtractHiddenBytes(bytes,SignLength);//���� �� ������ �������
			

			byte[] LengthInBytes=ExtractHiddenBytes(bytes,GlobalClass.Size_Of_Int_In_Bytes);//��� �� ���� ���� ����
			int Length=convertByteArrayToInt(LengthInBytes);//����� ������ �� �� �����

			byte Message[]=ExtractHiddenBytes(bytes,Length);//���� ������� �� ���� �����
			
			GetKey Read_Key;//
			PublicKey PublicKey;//�� ���� �� ����� ������� ������
			if( (Read_Key=new GetKey(PathOfPublicKey)).IsFileCanBeRead(PathOfPublicKey) )//���� ��� ������ ���� ���� ���� ����
				PublicKey=Read_Key.getPublicKey();//��� �� ����� ������� �����
			else//����� �� ����
			{
				new Message(null,"Public Key Error");//����� ��� ���� ����� �������
				return;
			}

			
			if(GenerateSignature.verification(Signature,PublicKey,Message))//���� �������� ������ ������ �� ����
			{
				IsThereMessage="true";//�� ���
				System.out.print("The Encode Message is: ");//����� ���
				for(int i=0; i<Length; i++)
					System.out.print((char)Message[i]);//���� �� ����� ������ ������ ���� ��� ��� ������
				
				
				//Save Message To File
				try
		        {
					int EndIndex=InputFile.getName().indexOf(".");//���� �� ���� ������ ������� ������ ������
					SavedFileName=InputFile.getName().substring(0, EndIndex);//����� �� ������ ������� ����� ����� �� �� ���� ����� ��� ���� ������
					
					file=new File("Decoded Messages/"+SavedFileName+".txt");//���� �� ���� ����� ������ �� ����� ������� ����� ���� �� �� �� ������ �������
					FileOutputStream OutputFileStream = new FileOutputStream("Decoded Messages/"+SavedFileName+".txt" );//���� �� ������ ������ ���� �����
					OutputFileStream.write(Message);//���� �� ������ ���� �����
					OutputFileStream.close();//�����
					
		        }
		      catch( Exception e )//����� ����� ����� ����� �� ����� ����
		        {
		            e.printStackTrace( System.out );
		            System.out.println("Could not save the decoded message to the txt file");
		        }
			}
			else
				IsThereMessage="false";//��� �����
		}
	}
	public byte[] ExtractHiddenBytes(byte[] bytes,int Size)//���� �� ������ ��� ���� �������� ��� ��� ������ ������ ���� ���� �� �� �����
	{
		byte[] HiddenBytes=new byte[Size];//���� ����� ������
		for(int i=0; i<Size; i++)//����� ����� ������
			for(int j=0; j<8; j++)//����� ������
			{
				HiddenBytes[i] = (byte) ((HiddenBytes[i] << 1) | (bytes[Offset] & 1)); //this part: (b[i] << 1)  comes in order to mov to bit to the higher place
				Offset++;//������� ���� ���
			}
		return HiddenBytes;//����� �� ���� ����� �����
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
