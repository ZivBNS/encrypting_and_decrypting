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
		byte[] TextInBytes=ConvertTextToBytes(Text);//��� �� ���� ����� ����� ��� �� ������ ����
		if(TextInBytes==null)//����� ����� ��� ���� �����
		{
			System.out.println("Error accured while converting text to bytes array");
			return;
		}
		int Offset=0;//����� ���� ������
		System.out.println(TextInBytes[Offset]);
		System.out.println(bytes.length+" is the bytes length");
		for (int i = 0; i <TextInBytes.length; i++)
			for(int j=7; j>=0; j--)
			{
				byte BitValue=(byte)((TextInBytes[i]>>j) & 1);//���� ����� ��� ���� ��� �� ����� ������ ����� �'��
				System.out.println(bytes[Offset]);
				bytes[Offset] = (byte)((bytes[Offset] & 0xFE) | BitValue);//����� �� �����  ����� ������ ��� ���� �������� �� ������
				Offset++;//����� ���� ���
			}	
		System.out.println("Offset="+Offset);
	}
	public byte[] ConvertTextToBytes(String Text) //����� �� ����� + ����� �����
	{

		
		
		byte[] StartSign=("$<").getBytes();//��� �������� ����� �������� ������� 36=$ 60+������

		
		byte[] MessageLengthInBytes=convertIntToByteArray(Text.length());//���� ���� �����
		byte[] TextInBytes=(Text).getBytes();//���� ���� �����

		
		//read the private key
		//put it in GenerateSignature
		GetKey Read_Key;//����� �� ����� ����� �����
		PrivateKey PrivateKey;
		if( (Read_Key=new GetKey()).IsFileCanBeRead(Read_Key.getKeyPath()) )//���� �� ������ ����
			 PrivateKey=Read_Key.getPrivateKey();//�� �� ���� �� ����� �����
		else
		{
			GlobalClass.what_message="Private Key";
			return null;
		}
		//Create a signature
		byte [] SigInBytes=new GenerateSignature(TextInBytes,PrivateKey).getSignature();//����� �� ���� ����� ���� ���� �����
		
		byte[] SigLengthInBytes=convertIntToByteArray(SigInBytes.length);//���� ������ �����

		byte[] TotalText=new byte[StartSign.length+SigLengthInBytes.length+SigInBytes.length+MessageLengthInBytes.length+TextInBytes.length];//��� ����� ������� �� ����� ����� ������ ������
		//���� ������ ���� $< ������� ����� ���� ������ ����� ��� ������ ���� ��� ���� ����� ����� ��� ���� ����� �����
		
		System.out.println("TotalText Length="+TotalText.length);//���� �� �� ������� ���
		System.out.println("StartSign.length="+StartSign.length);//���� ���� ������ ���� ���� 2 ����
		System.out.println("SigLengthInBytes.length="+SigLengthInBytes.length);//���� ������ �����
		System.out.println("SigInBytes.length="+SigInBytes.length);//���� ������
		System.out.println("MessageLengthInBytes.length="+MessageLengthInBytes.length);//���� ���� �����
		System.out.println("TextInBytes.length="+TextInBytes.length);//���� �����
		//Combine the StartSign to the TotalText 
		System.arraycopy(StartSign,0,TotalText,0,StartSign.length);//����� �� ���� ������
		
		//Combine the Length Of Signature to the TotalText 
		System.out.println("Start Copying Length Signature from="+StartSign.length);
		System.arraycopy(SigLengthInBytes,0,TotalText,StartSign.length,SigLengthInBytes.length);//����� ���� ������
		
		//Combine the Signature to the TotalText 
		System.out.println("Start Copying Signature from="+StartSign.length);
		System.arraycopy(SigInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length),SigInBytes.length);//����� ������
		
		//Combine the TextLength into TotalText
		System.out.println("Start Copying MessageLengthInBytes from="+(SigInBytes.length+StartSign.length));
		System.arraycopy(MessageLengthInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length+SigInBytes.length),MessageLengthInBytes.length);//����� ���� ����
		//Combine the TextInBytes into TotalText
		System.out.println("Start Copying TextInBytes from="+(StartSign.length+SigInBytes.length+MessageLengthInBytes.length));
		System.arraycopy(TextInBytes,0,TotalText,(StartSign.length+SigLengthInBytes.length+SigInBytes.length+MessageLengthInBytes.length),TextInBytes.length);//����� ���� �����
		
		return TotalText;// ����� �� ���� ����� �� �� ����� ����� ����� �����
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
