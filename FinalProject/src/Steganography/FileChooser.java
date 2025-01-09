package Steganography;
import java.io.File;
import javax.swing.JFileChooser;



public class FileChooser //extends JPanel 
{
	JFileChooser fc;//����� �� ���� ������
	int returnVal;//��� ������
	File ImageFile;//���� �� ���� ������
	public FileChooser(String Operation)
	{	
		ImageFile=null;//����� ����
		fc = new JFileChooser();//���� ���� �����
		if(Operation.equals("Open"))//���� ����
			returnVal=fc.showOpenDialog(null);//��� ��� �� ������
		else//���� ����
			returnVal=fc.showSaveDialog(null);//��� ��� �����
	}
	public boolean IsFileChooserClosed()//��� ���� ���� �����
	{
		System.out.println(returnVal);
        if (returnVal == JFileChooser.APPROVE_OPTION)//�� �� ���� 
        {
        	return false;//��� ���� ����� ����
        } 
        else//�� ���� 
        {
        	System.out.println("Attachment cancelled by user.");//���� �� ��� ������
        }

        //Reset the file chooser for the next time it's shown.
        //don't have to reset it because it can be more comfortable
        return true;
	}
	public void setImageFile()//���� �� ����� �����
	{
		ImageFile = fc.getSelectedFile();//���� ���� ����� �� ��� ������ ����� ���� ������ ��
	}
	public File getInputFile()//���� �� �����
	{
		return ImageFile;
	}
	
}
