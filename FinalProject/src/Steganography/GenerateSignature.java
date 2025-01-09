package Steganography;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.Signature;


public class GenerateSignature 
{
	byte[] Message;//������ �� ��� ���� �����
	static byte [] sig;//������ �� ��� ���� �����
	public GenerateSignature(byte[] message,PrivateKey PrivateKey)
	{
        try
        {
        	Message=message;//���� ����� ������� �����
            Signature signer = Signature.getInstance("RSA");//���� ����� ����� �� �� ���
            
            signer.initSign(PrivateKey);//����� �� ����� ����� ������
            signer.update( Message );//���� �� ����� ������ ������
            sig = signer.sign();//����� �� ���� �� ������ ����� �����
        }
        catch( Exception e )
        {
            e.printStackTrace( System.out );
        }
	}
	public byte[] getSignature()//���� �� ������
	{
		return sig;
	}
	static public boolean verification(byte[] Sig,PublicKey PublicKey,byte [] message)//����� ����� ����� ��� ������� ������ �����
	{
		boolean sigIsOK=false;
		try
		{			
			Signature verifier = Signature.getInstance("RSA");//���� ����� ����� �� �� ���
	        verifier.initVerify( PublicKey );//����� �� ����� ����� ������
	        verifier.update( message );//���� �� ���� �����
	        sigIsOK = verifier.verify( Sig );//���� ������ ��� �������   
	        //SigIsOK ?(if(true))""(else):
	        System.out.println( (sigIsOK ? "" : "does not ")+"verify" );//���� �� ���� �� ��
		}
        catch( Exception e )
        {
            e.printStackTrace( System.out );
        }
        return sigIsOK;
	}
}
