package Steganography;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public  class CoustomizedButton extends JLabel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	ImageIcon RegularImg=null; //התמונה במצב רגיל, ללא לחיצה או מעבר עכשבר
	ImageIcon PressedImg=null; //התמונה אחרי שנלחצה ע"י העכבר
	ImageIcon OnMouseOverImg=null; //התמונה במעבר עכבר
	boolean Pressed;//משמש לבדיקת לחיצה
	public CoustomizedButton(String Name)
	{
		if(Name=="Browse")//אם נבחר לחיצה/מעבר עכבר לכפתור עיון
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/BrowseRegular.png"));//רגיל
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/BrowseOnMouseOver.png"));//מעבר עכבר
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/BrowsePressed.png"));//לחוץ
			
		}
		if(Name=="Encode")//אם נבחר לחיצה/מעבר עכבר לכפתור הצפנה
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/EncodeRegular2.png"));//רגיל
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/EncodeOnMouseOver2.png"));//מעבר עכבר
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/EncodePressed.png"));//לחוץ
		}
		if(Name=="Decode")//אם נבחר לחיצה/מעבר עכבר לכפתור פיענוח
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/DecodeRegular.png"));//רגיל
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/DecodeOnMouseOver.png"));//מעבר עכבר
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/DecodePressed.png"));//לחוץ
		}
		if(Name=="New keys")//hoאם נבחר לחיצה/מעבר עכבר לכפתור מפתחות חדש
		{
			RegularImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysRegular2.png"));//רגיל
			OnMouseOverImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysOnMouseOver2.png"));//מעבר עכבר
			PressedImg=new ImageIcon(getClass().getResource("../Interface Images/NewKeysPressed.png"));//לחוץ
		}
		setIcon(RegularImg);//מצב תמונה התחלתי = רגיל
		addMouseListener(this);//הוסף מאזין לכפתורים
	}
	public void mouseEntered(MouseEvent e) //בדיקת לחיצת/מעבר העכבר
	{
		if(Pressed)//אם נלחץ
			setIcon(PressedImg);//שנה לתמונה לחוצה
		else
			setIcon(OnMouseOverImg);//אם לא נלחץ שנה תמונה למעבר עכבר	
	}
	public void mouseExited(MouseEvent e)//בדיקה אם אין מעבר ולחיצת עכבר כלל
	{
		setIcon(RegularImg);//משנה למצב תמונה רגיל	
	}
	public void mousePressed(MouseEvent e)//בדיקת לחיצת העכבר
	{
		Pressed=true;//נלחץ
		setIcon(PressedImg);//משנה תמונה למצב לחוץ
	}
	public void mouseReleased(MouseEvent e)//שחרור לחיצה
	{
		Pressed=false;//לא לחוץ
	}
	public void mouseClicked(MouseEvent e)//לאחר לחיצה 
	{ 
		setIcon(RegularImg);//העכבר חוזר למצב רגיל
	}
 
}
