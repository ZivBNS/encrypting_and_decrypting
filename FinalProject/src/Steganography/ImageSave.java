package Steganography;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageSave 
{
	String ImageFormat;//���� ����� �� �����
	public ImageSave(BufferedImage img,File path,String format)//���� ����� ����� ������ ������
	{
		ImageFormat=format;//���� �� ������ ������ ������
		File OutputFile=getFileName(path);//����� ������ ������ �� ������ ������
	    try
	    {
		    ImageIO.write(img, ImageFormat, OutputFile);//���� ����� ����� ���� ��� ������ ������
		    System.out.println("Saved");//���� �� �� ����� ������
		} 
		catch (IOException e) //�����
		{
			e.printStackTrace();
			System.out.println("NOT Saved");//������ �� �����
		}
	}
	/**
	 *<h3> getFileName </h3> 
		</br>
	 *private File getFileName(File p)
	 * <blockquote> Return the same path that was send plus suffix ImageFormat.  <br> </br>
	 * This function avoid the user inserting different formats than "png" or "gif",in addition 
	 * the user don't have to insert format,the png suffix is saved automatically </blockquote>
	 * @param p-a path of image we want to save.
	 * @return Same path and suffix ImageFormat.
	 */
	private File getFileName(File p)//����� ����� ����� �� �����
	{
		File Path; //����� ��
		String ImageName = p.toString(); //�� ������ �� �� ������
		int period=ImageName.indexOf('.'); //get the index of "." if there is no "." the function returns -1

		if(period!=-1)
		{
			ImageName=ImageName.substring(0,period);
			System.out.println("Image Name "+ImageName);
		}
		Path=new File(ImageName+"."+ImageFormat);
		return Path;
	}

}
