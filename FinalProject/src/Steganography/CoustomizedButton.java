package Steganography;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public  class CoustomizedButton extends JLabel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	ImageIcon RegularImg=null; //������ ���� ����, ��� ����� �� ���� �����
	ImageIcon PressedImg=null; //������ ���� ������ �"� �����
	ImageIcon OnMouseOverImg=null; //������ ����� ����
	boolean Pressed;//���� ������ �����
	public CoustomizedButton(String Name)
	{
		if(Name=="Browse")//�� ���� �����/���� ���� ������ ����
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/BrowseRegular.png"));//����
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/BrowseOnMouseOver.png"));//���� ����
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/BrowsePressed.png"));//����
			
		}
		if(Name=="Encode")//�� ���� �����/���� ���� ������ �����
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/EncodeRegular2.png"));//����
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/EncodeOnMouseOver2.png"));//���� ����
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/EncodePressed.png"));//����
		}
		if(Name=="Decode")//�� ���� �����/���� ���� ������ ������
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/DecodeRegular.png"));//����
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/DecodeOnMouseOver.png"));//���� ����
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/DecodePressed.png"));//����
		}
		if(Name=="New keys")//ho�� ���� �����/���� ���� ������ ������ ���
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysRegular2.png"));//����
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysOnMouseOver2.png"));//���� ����
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysPressed.png"));//����
		}
		setIcon(RegularImg);//��� ����� ������ = ����
		addMouseListener(this);//���� ����� ��������
	}
	public void mouseEntered(MouseEvent e) //����� �����/���� �����
	{
		if(Pressed)//�� ����
			setIcon(PressedImg);//��� ������ �����
		else
			setIcon(OnMouseOverImg);//�� �� ���� ��� ����� ����� ����	
	}
	public void mouseExited(MouseEvent e)//����� �� ��� ���� ������ ���� ���
	{
		setIcon(RegularImg);//���� ���� ����� ����	
	}
	public void mousePressed(MouseEvent e)//����� ����� �����
	{
		Pressed=true;//����
		setIcon(PressedImg);//���� ����� ���� ����
	}
	public void mouseReleased(MouseEvent e)//����� �����
	{
		Pressed=false;//�� ����
	}
	public void mouseClicked(MouseEvent e)//���� ����� 
	{ 
		setIcon(RegularImg);//����� ���� ���� ����
	}
 
}
