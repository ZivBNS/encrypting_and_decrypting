package Steganography;

import java.awt.Color;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;



import javax.swing.JFileChooser;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.ModernBalloonStyle;
import net.java.balloontip.utils.TimingUtils;

public class Checks 
{
	BalloonTip balloonTip;
	JFileChooser filechooser1,filechooser2,filechooser3;
	Interface window;
	BufferedImage ImageWithMessage;
	public Checks(Interface w)
	{
		window=w;
	}
	public void MouseEntered(String Name)//���� ���� ���� �� �������� ����� ������ ��� �����
	{
		Color c=new Color(7,75,118);//��� �����
		ModernBalloonStyle Modern = new ModernBalloonStyle(10,10,c,Color.white,Color.black);//����, ����,����� �����, ����� �����, ��� ����
		
		Modern.setBorderThickness(2);//���� ������ �� �����
		Modern.enableAntiAliasing(true);
		//����� ����� �� ������� �����
		if(Name=="Browse")//�� ���� ����
			//Click this button in order to  Choose an image
			balloonTip = new BalloonTip(window.Browse, "<html><font color=black> Choose an image.</font></html>",Modern,false);//���� ����, ������, ��� �����, ������ �����
		else
			if(Name=="BrowsePublic")//�� ���� ���� ����
				balloonTip = new BalloonTip(window.BrowsePublic, "<html><font color=black> Click this button in order to choose a public key.</font></html>",Modern,false);//���� ����, ������, ��� �����, ������ �����
			else
		balloonTip.setPadding(0);
		TimingUtils.showTimedBalloon(balloonTip, 5000);
	}
	public void mouseExited()//���� ���� ���� ���� �� ��������
	{
		balloonTip.closeBalloon();//���� ���� ���
	}

	public void mouseReleased(String Name)//������ �����
	{
		int returnVal=0;//��� ���� �����
		File path;//����� �����/����
		if(Name=="Browse")//���� ����� ����� �� ������
		{
			if(filechooser1==null)
			{
				filechooser1=new JFileChooser();//���� ���� ���� ��� ������ �������
				System.out.println("NEW");
			}
			
			filechooser1.setAcceptAllFileFilterUsed(false);//�� ����� ���� �� ����
			
			
			filechooser1.setFileFilter(new ImageFilter("Images"));//������ ����� �� �� ����� �� �� ������ �'�� ��� �� �'�
			filechooser1.setFileView(new ImageFileView());//����� ��������� ����� ����� �� �� ��� ������ �� �� ����� ������ImageFileView 
			
			filechooser1.setDialogTitle("Open an image");//����� �����
			returnVal=filechooser1.showOpenDialog(window);//���� ���� ��� �� �� ���� ����� ������ �� ����/����/����� ����� �����
			
			if(!IsFileChooserClosed(returnVal))//�� ����� �� ����
			{
				path=filechooser1.getSelectedFile();//����� ����� ����� �� ����� ���� ������ �����
				//path.exists()
				if(path.canRead())//�� ������ ���� ������
				{
					ImageWithMessage=GlobalClass.ReadImage(path);//���� �� ����� ����� ������ ������
					if(GlobalClass.ImageValidation(ImageWithMessage))//�� ������ ����� ������
					{
						GlobalClass.ConvertImageToBytes(ImageWithMessage);//���� �� ������ ����� ������� ����� ����� �� ���� �� ����� bytes 
						window.TextPath.setText(path.toString());//
						if(window.ActioName=="Encode")
						{
							//check if the image is not too small,if it does than don't set the notes and the text box
							if(SetNoets(ImageWithMessage))//����� �� ���� ������ ����� ����
								window.setSize(726,457);
							else
								return;
						}
						else
							window.setSize(726,404);
						window.SetMessageBoxOrKeys();
						
					}
					else//�� ������ ����� ������
					{
						window.setComponentsVisibilityToFalse();//����� ����� ����
						window.setSize(726,298);
						new Message(window,"ImageError");//���� ����� ������� ���� �����
						System.out.println("File Is Damaged");//����� ��� ���� ����� ������ �����
						return;
					}

				}
				else//�� ���� ����� �� ������ �� �����
				
					System.out.println("File not exsistence");//����� �� ����� �� ����
			}
		}
		else
				if(Name=="BrowsePublic")//���� ���� ����� ���� ������
				{
					if(filechooser2==null)
						filechooser2=new JFileChooser("");//���� ���� ���� ���
					
					filechooser2.setAcceptAllFileFilterUsed(false);//�� ���� �� �� ��������
					filechooser2.setFileFilter(new ImageFilter("PublicKey"));//��� �� ���� ������, ����� ���� DAT
					//?
					filechooser2.setDialogTitle("Open a public key");//����� �����
					returnVal=filechooser2.showOpenDialog(window);// �����:��� ������ ����� �����
					if(!IsFileChooserClosed(returnVal))//�� �� ���� ����� �� ��� ������
					{
						path=filechooser2.getSelectedFile();//���� �� ����� ���� �����
						window.PublicBox.setText(path.toString());
	
						window.SetEncodeOrDecode();//����� ����� �����/����� �����
						window.setSize(726,404);
					}
				}
				else
					if(Name=="Encode")//����� ����� ������
					{
						if(filechooser3==null)
							filechooser3=new JFileChooser();//���� ���� ���� ��� ������ ���� ������
						filechooser3.setDialogTitle("Save image");//����� �����
						filechooser3.setFileView(new ImageFileView());//���� �� ��������� �� �� ����� �����
						returnVal=filechooser3.showSaveDialog(window);//�����: ��� ������ ����� �����
						if(!IsFileChooserClosed(returnVal))//�� ����� �� ����
						{
							FirstUse();//����� ������ ����� �� �� ������ ���� 
							String ImageForamt=getImageFormat(window.TextPath.getText());//���� ����� ����� �� ����� ������
							new File(window.TextPath.getText());
							new Encode(window.Text.getText());//���� ����� �� ������ ������ ����� ������ ������ �����
							path=filechooser3.getSelectedFile();//���� ����� ����� �� ������ ���� �����
					
							if(GlobalClass.what_message=="Encode")//�� ������ ������ ����� �����
								new ImageSave(ImageWithMessage,path,ImageForamt);//���� �� ���� ������ �ImageWithMessage
							new Message(window,GlobalClass.what_message);//������ ������ ������� ������
						}
						
					}
					else
						if(Name=="Decode")//�� ���� ����� ������
						{
							Decode decode=new Decode(window.TextPath.getText(),window.PublicBox.getText());//���� �� ����� ������ ������ ����� �������
							String MessageExistent=decode.IsThereAMssage();//���� �� ����� ���
							
							
							new Message(window,MessageExistent);//����� ����� �����
							if(MessageExistent!="false")//�� ����� �����
							try 
							{
								//System.out.println(MessageExistent);
								Runtime.getRuntime().exec("notepad "+ MessageExistent+"");//���� ����� ���� ������ ������ ������ ������ ���� ���� ����
								//Runtime.getRuntime().exec("notepad C:\\Users\\zivbs\\Documents\\��� ���\\FinalProject\\Decoded Messages\\"+decode.SavedFileName+".txt");//���� ����� ���� ������ ������ ������ ������ ���� ���� ����
							} 
							catch (IOException e)//�� ����� ����� ����� ����� ���� ����� 
							{
								e.printStackTrace();
							}
						}
	}
	public Boolean IsFileChooserClosed(int returnVal)//���� �� ���� ����
	{
        if (returnVal == JFileChooser.APPROVE_OPTION)//���� �� ����� ���� 
        	return false;//����� ���        
        else//���� ���� �� ��� ������ 
        	System.out.println("Attachment cancelled by user.");//����� ������ ���� ���� �� ��� ������
        return true;//����� ���, ����� ����
	}
	public boolean CheckTextMessage()//���� ������ �� ���� ���
	{
		int MsgLength=window.Text.getText().trim().length();//����� �� ���� ���� ������ �������
		if(MsgLength==0)//���� ��� ���� ���
		{
			System.out.println("sapce");
			return false;//����� ���	
		}
		return true;//�� �����
	}
	public void FirstUse()//����� ������ ����� �� ����� �� ������ ����� ������� ��� ����� ���
	{
		File PublicKey=new File("../Keys/Public Key.dat");//���� �� ����� ����� �������
		if(PublicKey.canRead())//��� ���� ����� ����� ��
		{
			System.out.println("Keys already created");//�� �� ����� ������� ��� ������
		}
		else//�� �� ��� ������ �����
		{
			System.out.println("First use: Keys created automatically");//��� ������ ��� ������� ����� ��������
			new GenerateKeys();//���� ������ �����
		}
	}
	public boolean SetNoets(BufferedImage img)//����� ����� ��� ���� ������ ����� ���� ������ ������� �� ���� ������ ���� ���� ������ ���� 
	{
		int AmountOfNoets=(GlobalClass.bytes.length-1104)/8;//���� ����� ��� ������ �� �� ����� ����

		/*
		                  On Computer(Regular)   After Encoding   
		  
		 	StartSign        -    2 bytes 				    2*8=16    bytes
		 	Signature Length -    4 byte                    4*8=32    bytes
		 	Signature        -   (Signature Length) bytes	(Signature Length)*8  bytes max 128*8
		 	TextLength       -    4 bytes				    4*8=32    bytes
		 	Message          -    n bytes				    n*8=8n    bytes
		 	
		 	Message -   (img.getWidth()*img.getHeight()*3-(16+32+1024+32) )/8    
		 */
		if(AmountOfNoets<=0)//�� �� ���� ����� ���
		{
			window.setComponentsVisibilityToFalse();//����� ������
			window.setSize(726,303);
			new Message(window,"SmallImage");//����� ���� ���
			
			
			System.out.println("Image is too small");//����� ������� ���� ���
			return false;//���-�� ���� ������ ����� ������� ����
		}
		else
			if(AmountOfNoets<5000000)//�� ����� ���� ������ ���
			{
				window.NotesLeft=AmountOfNoets;//���� �� ���� ������ ����� ��� ������
				window.Title2.setTitle("Notes left: "+window.NotesLeft);//���� ����� Notes left: n
				window.Text.setDocument(window.new JTextFieldLimit(AmountOfNoets));//���� �� ���� ������ ����� ��� ������ ����� �������� ����� ������
				window.Text.getDocument().addDocumentListener(window);//����� �����
				window.AreaJScrollPane.setBorder(window.b);
				window.AreaJScrollPane.setBorder(window.border1);
				window.validate();
			}
		return true;//���-�� ����� ���� ������ ����� ������
	
	}
	public String getImageFormat(String p)//����� ����� ����� ����� ������� �� ������ ���
	{
		String format;//��� ���� ������
		String ImageName = p; //�� ������ ���� ��� �����
		int period=ImageName.indexOf('.');//����� ������ ������ ���� ������ �����
		format=ImageName.substring(period+1, ImageName.length());//����� �� �� ������ ������� ��� ����� ��� ��� ������ ������ �� ����� ������
		System.out.println("Format is: "+format);//���� ���� �����
		return format;//���� �����
	}

}
