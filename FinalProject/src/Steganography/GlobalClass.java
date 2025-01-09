package Steganography;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GlobalClass 
{
	static BufferedImage img;
	static String what_message="Encode";
	static byte[] bytes;
	static final int Size_Of_StartSign = 2; //���� 2 �� ����� ���� ����� ����
	
	static final int Size_Of_One_Char_In_EncodeText = 8; //the size of one char //1 char (8 bits) stored in 8 bytes. each last bit in each byte presents 1 bit in a char respectively 
	static final int Size_Of_Int_In_Bytes = 4; //the size of integer in bytes

	public static void ConvertImageToBytes(Image i)
	{
		img=(BufferedImage)i;//���� �� ������
		bytes=accessBytes(img);//���� �� ������ ����� ������� �����
	}
	private static byte[] accessBytes(BufferedImage image)
	{
		WritableRaster raster = image.getRaster(); //���� ����� �� ������� ���� ����� �����
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();//����� �� �������� ������� ����� ���� �� ��� ������� ���� ���
		return buffer.getData();//����� �� ���� ������ �� �������� �� ������	
	}
	static public BufferedImage ReadImage(File Path)//���� �� ������ �� �� ������
	{
		BufferedImage ImageRead=null;
			try 
			{
				ImageRead=ImageIO.read(Path);//���� ����� �� ������
			} 	
			catch(IllegalArgumentException e)
			{
				System.out.println("Path is null!");///�� ����� ����� ������
			}
			catch (IOException e) 
			{
				System.out.println("Error occured during reading!");///�����
			}
			return ImageRead;//����� ����� �����
	}
	public static boolean ImageValidation(BufferedImage img)//���� �� ������ ���� 
	{
		if(img!=null)
			return true;
		System.out.println("false");
		return false;
	}
}
