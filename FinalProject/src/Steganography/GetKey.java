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
	private String Path="Keys/Private Key.dat";//����� �� ����� �����
	private PrivateKey PrivateKey=null;//���� ����
	private PublicKey PublicKey=null;//���� ������
	private Object Object = null;//���� ������ �� �����
	
	public GetKey(){}//���� ����� ���� ��� ��� ���� ��������� ������, ���� ����� �����
	
	public GetKey(String path)
	{
		Path=path;
	}
	public boolean IsFileCanBeRead(String Path)//��� ����� ���� ����
	{
		File file=new File(Path);//����� �� ������
		if(file.canRead())//��� ����?
		{
			ObjectInputStream inputStream = null;//����� �� ����� �����
			try 
			{
				//??????????
				//threre is a problem when i change the content in the private key
				//i'm trying to get a message that points to the problem
				//when the user tries to insert a private key instead of a public key 
				inputStream = new ObjectInputStream(new FileInputStream(Path));//����� �����
			} 
			catch(EOFException e)
			{
				System.out.println("ends");//��� �����
			}
			catch (Exception e1)
			{
				e1.printStackTrace();//�����
			} 
			
					try 
					{
						Object = inputStream.readObject();//��� ����� �� ����� ������ �� �������� ��� �������� ������
					} 
					catch(Exception e)
					{
						
						System.out.println("EXCEPTION");//�����
						return false;
					}
				
					if(Path!="Keys/Private Key.dat")//�� ������ ��� �� �� ����� �����
					{
						System.out.println("Public Key!!");//�� ���� ������
						try
						{
							PublicKey=(PublicKey)Object;//��� ���� �� ����� ������� ������ ���� ���� ����
						}
						catch(ClassCastException e)//��� ����
						{
							return false;//����� ����� ����� �������� ������ �� ����
						}
					}
					else
					{
						System.out.println("Path="+Path);//���� �� ������
						PrivateKey=(PrivateKey)Object;//��� �� ����� ����� ������
					}
				try 
				{
					
					inputStream.close();//���� �� �����
					return true;
				} 
				catch (IOException e) {e.printStackTrace();}//�����
		}
		else
		{
			System.out.println("A Message window should be open");
			return false;
		}
		return true;
		

	}
	public String getKeyPath()//���� �� ������ �� �����
	{
		return Path;
	}
	public PrivateKey getPrivateKey()//���� �� ����� �����
	{
		return PrivateKey;
	}
	public PublicKey getPublicKey()//���� �� ����� �������
	{
		return PublicKey;
	}	
}
