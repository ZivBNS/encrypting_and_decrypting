package Steganography;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFileView extends FileView {
    ImageIcon gifIcon = Utils.createImageIcon("../Icon Format/gifIcon.gif");//������ ������ ���
    ImageIcon pngIcon = Utils.createImageIcon("../Icon Format/pngIcon.png");//������ ������ �� �� ��
    

    
    public String getName(File f) {//���� �� �� �����
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File f) {//���� �� ����� �����
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {//��� ����� �� �� �����
        return null; //let the L&F FileView figure this out
    }

    public String getTypeDescription(File f) {//����� �� ����� ������ ����� �� ���� ���
        String extension = Utils.getExtension(f);//���� ����� ���� �� ��� ����� ������
        String type = null;//����� �� ��� ������

        if (extension != null) //�� ����� ����� �����
        {
        	if (extension.equals(Utils.gif))//���� ���� ������ ���
        	{
        		type = "GIF Image";//��� ������ ��� ���
        	} 
        	else 
    			if (extension.equals(Utils.png))//�� ������ ���� �������
    			{
    				type = "PNG Image";//��� ������ ��� �� �� ��
    			}
        }
        return type;//���� �� ��� ������
    }

    public Icon getIcon(File f) 
    {
        String extension = Utils.getExtension(f);//����� �� ����� ������
        Icon icon = null;//������� ������

        if (extension != null) //�� ���� �����
        {
            	if (extension.equals(Utils.gif)) //���� ���� ������ ���
            	{
            		icon = gifIcon;//������� �� ������ ���� ������ ���
            	} 
            	else 

	            		if (extension.equals(Utils.png)) //���� �� ���� ������ ��� �� ��
	            		{
	            			icon = pngIcon;//������� �� ������ ���� �� �� ��
	            		}
        }
        return icon;//���� �� �������
    }
}