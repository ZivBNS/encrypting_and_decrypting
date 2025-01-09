package Steganography;
import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter 
{
	String filter;//����� �����
	public ImageFilter(String f)
	{
		filter=f;
	}
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) //����� �� �� ��� ����
    {
        if (f.isDirectory()) //��� ����� ��� �����
            return true;//�� �� ���� ���
        String extension = Utils.getExtension(f);//���� �� ����� �����
        if(extension==null)//�� ��� ���
        {
        	System.out.println("There is no extention");//����� �� ���� ����� �����
        }
        else//����
	        if(filter=="Images")//����� ��� ����� ���� ������
			        if (extension.equals(Utils.gif) ||extension.equals(Utils.png)) //�� ��� ���� ������ ��� �� �� �� ��
		                return true;//���� ���
			        else {}
	        else//����
	        	if(filter=="PublicKey")//���� ��� ����� ���� �����
			        if (extension.equals(Utils.dat)) //��� ����� ����� �� ���� ����� ��
		                return true;//���� ���� ���
	
	        return false;//�� ����� ��� ���� ��� �� ����� ���� ���
    }

    //The description of this filter
    public String getDescription() 
    {

        return null; //"Just Images"
    }
}
