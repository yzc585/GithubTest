/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author zhichao
 */
public class CutTest {

    private int x = 0, y = 0, m = 0, n = 0;
    private int xx = 0, yy = 0, mm = 0, nn = 0;

    public CutTest() throws IOException {
        BufferedImage bi = ImageIO.read(new File("H:/いろいろな図片/壁纸/二值化后_无压缩.bmp"));//通过imageio将图像载入    
        int h = bi.getHeight();//获取图像的高   
        int w = bi.getWidth();//获取图像的宽   
        System.out.println(new Color(0,0,0).getRGB());
        System.out.println(new Color(255,255,255).getRGB());
//        for (int x = 0; x < w; x++) {
//            for (int y = 0; y < h; y++) {
//                int rgb = bi.getRGB(x, y);
//                System.out.println(rgb);
//            }
//        }
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = bi.getRGB(x, y);
                if (rgb == new Color(0, 0, 0).getRGB() && this.x == 0 && this.y == 0) {
                    this.x = x;
                    this.y = y;
                    break;
                }
            }
        }
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgb = bi.getRGB(x, y);
                if (rgb == new Color(0, 0, 0).getRGB() && this.m == 0 && this.n == 0) {
                    this.m = x;
                    this.n = y;
                    break;
                }
            }
        }
        for (int x =w - 1; x >= 0; x--) {
            for (int y = 0; y <h; y++) {
                int rgb = bi.getRGB(x,y);
                if (rgb == new Color(0, 0, 0).getRGB() && mm == 0 && nn == 0) {
                    this.mm = x;
                    this.nn = y;
                    break;
                }
            }
        }
        for (int y = h - 1; y >= 0; y--) {
            for (int x = 0; x < w; x++) {
                int rgb = bi.getRGB(x, y);
                if (rgb == new Color(0, 0, 0).getRGB() && xx == 0 && yy == 0) {
                    this.xx = x;
                    this.yy = y;
                    break;
                }
            }
        }
       System.out.println(m+"  "+y+"  "+mm+"   "+yy);
       File file=new File("H:/いろいろな図片/壁纸/二值化后_无压缩.bmp");
       new ImageTools().cutting(file, m, y, mm-m, yy-y);//  裁剪
       new ImageTest();//缩放
       //BufferedImage bi1=new BufferedImage(mm-m, yy-y,BufferedImage.TYPE_BYTE_BINARY);
     //  Graphics g =bi1.getGraphics();
      // g.copyArea(m, y, mm-m, yy-y,0, 0);
    //  g.drawImage(bi, 0,0, null);
    //  ImageIO.write(bi1, "BMP", new File("H:/いろいろな図片/壁纸/二值化后_无压缩2.bmp"));
    }

    public static void main(String args[]) throws IOException {
       new CutTest();
    }
}
