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
	private PrivateKey PrivateKey;//���� ����
	private PublicKey PublicKey;//���� ������
	public GenerateKeys()
	{
        try
        {
        	Security.addProvider(new BouncyCastleProvider());//���� �� ��� �� ��� ���� ���� ������ ����
        	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");//����� ������ ����� �� �� ���
        	kpg.initialize(1024, new java.security.SecureRandom());//����� ������
        	
	        
	        KeyPair kp = kpg.generateKeyPair();//���� �� �������
	        PublicKey = kp.getPublic();//���� ���� �����
	        PrivateKey = kp.getPrivate();//���� ���� ����
	
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Keys/Public Key.dat",false ) );//����� ���� ���� ������
	        oos.writeObject(PublicKey);//����� ����� �����
	        oos.close();//����� ����
	        
	        oos = new ObjectOutputStream(new FileOutputStream("Keys/Private Key.dat", false ) );//����� ���� ���� ����
	        oos.writeObject(PrivateKey);//����� ����� �����
	        oos.flush();//����� ������ ���� ����� �����

	        oos.close();//����� ����
	
        }
        catch( Exception e )//�����
        {
            e.printStackTrace( System.out );
        }
	}
	public PrivateKey getPriavtekey()//���� ���� ����
	{
		return PrivateKey;
	}
	public PublicKey getPublickey()//���� ���� ������
	{
		return PublicKey;
	}
}
