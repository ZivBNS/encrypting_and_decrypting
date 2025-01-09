package Steganography;
import javax.swing.JOptionPane;


public class Message 
{
	int Command;
	public Message(Interface MainWindow,String Operation)//������ ����� ��������
	{
		
		if(Operation=="ImageError")
			JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4>  Image can't be read   </font>  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//������ ��� ��� ���� ������
		else
		if(Operation=="SmallImage")
			JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4>  Image is too small</font></html>","Pay attention",JOptionPane.WARNING_MESSAGE);//������� ���� ��� ����� ������ �� ���
		else
			if(Operation=="KeyError")
				JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4> Check your contact's publick key </font>  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//������� �� ����
			else
				if(Operation=="Private Key")
					JOptionPane.showMessageDialog(MainWindow,"<html><font color=red size=4> Keys are damaged.</font> <br> Create new ones,go to menu-bar: File-->New keys  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//������ ������
				else
			if(Operation=="Keys")
			{
				Command=JOptionPane.showConfirmDialog(MainWindow,"<html> <Font size=4 color=red> Creating new keys will replace the previous ones </Font> </html> \n Are you sure? ","Pay Attenation",JOptionPane.YES_NO_OPTION);//����� ������ ������� �����
				CheckCommand();//����� �� ������ ����� �����
			}
			else
				if(Operation=="Encode")
				{
					JOptionPane.showMessageDialog(MainWindow,"Message has been encrypted","Confrim saving",JOptionPane.INFORMATION_MESSAGE);//���� ������
				}
				else
					//Decode
					if(Operation=="false")
						JOptionPane.showMessageDialog(MainWindow,"<html> Message <font size=4> not found </font><br> Check your image/contac't public key.</html>","Pay attention",JOptionPane.WARNING_MESSAGE);//�� ���� ��� ������
					else
					{
						System.out.println("\n"+Operation);
						JOptionPane.showMessageDialog(MainWindow,"<html> Message <font size=4>  found </font> <br>Path:" +Operation +"  </html>","Pay attention",JOptionPane.WARNING_MESSAGE);//���� ���
					}
	}
	public boolean CheckCommand()
	{
		if(Command==JOptionPane.YES_OPTION)//�� �� ��� ������ �����
		{
			JOptionPane.showMessageDialog(null,"Keys have been created successfully","Confrim generation",JOptionPane.INFORMATION_MESSAGE);//������� ����� ������
			System.out.println("New Keys Created");//����� �� ��
			new GenerateKeys();//��� ������
			return true;//��� ����� ������
		}
		else
			if(Command==JOptionPane.NO_OPTION)//�� ��
				System.out.println("Keys generation canceled");//����� ������� ���� �� ������ �� ������ �����
		
		return false;//���� ��� �� ����� ������ �����
	}
}
