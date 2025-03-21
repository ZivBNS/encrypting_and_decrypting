package Steganography;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utils 
{
    public final static String gif = "gif";
    public final static String png = "png";
    public final static String dat = "dat";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {//���� �� ����� �����
        String ext = null;//�� ���� ��� �����
        String s = f.getName();//�� �����
        int i = s.lastIndexOf('.');//���� ������ ��� �������
        if (i > 0 &&  i < s.length() - 1) //�� ���� ���� ���� ������
        {
            ext = s.substring(i+1).toLowerCase();//��� �� ��� ������� ����� �� ������
        }
        return ext;/// �� ����� �� ���� ������ �� ������ �� ���� �� �����
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) 
    {
        URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
