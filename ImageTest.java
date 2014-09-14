/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.image.*;


/**
 *
 * @author ylin
 */








/**
 *
 * @author ylin
 */


public class ImageTest {

    /**
     * @param args the command line arguments
     */
    public  ImageTest() throws IOException{
        File picture=new File("H:/いろいろな図片/壁纸/二值化后_无压缩.bmp");
        BufferedImage bi=ImageIO.read(picture);
        BufferedImage bi1=new BufferedImage(60, 60, BufferedImage.TYPE_BYTE_BINARY);
   
      //  ImageIcon icon=new ImageIcon();
        
        int width = bi.getWidth();  
        int height = bi.getHeight(); 
 
        Graphics g = bi1.getGraphics(); 
     //   Graphics g1= bi1.getGraphics();
        g.drawImage(bi, 0,0,60,60,0,0,width, height,null); 
      //  g.dispose(); 
         
        
        JFrame fr=new JFrame();
        JLabel l=new JLabel(new ImageIcon(bi1));
        fr.add(l, BorderLayout.CENTER);
      //  fr.add();
        fr.setSize(400, 400);
        fr.setLocation(200, 200);
       // fr.setSize(bi1.getWidth(),bi1.getHeight());
        fr.setVisible(false);///  暂时性隐藏
        fr.addWindowListener(new WindowAdapter() {
               public  void windowsClosed(){
                   System.exit(1);
               }
        });
        

        ImageIO.write(bi1, "BMP", new File("H:/いろいろな図片/壁纸/二值化后压缩.bmp"));

    }
    
            
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here
        new ImageTest();
}
}
