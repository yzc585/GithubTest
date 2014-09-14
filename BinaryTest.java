/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BinaryTest {

    public BinaryTest() throws IOException {
        BufferedImage bi = ImageIO.read(new File("H:/いろいろな図片/壁纸/222.jpg"));//通过imageio将图像载入   
        int h = bi.getHeight();//获取图像的高   
        int w = bi.getWidth();//获取图像的宽   
        int rgb = bi.getRGB(0, 0);//获取指定坐标的ARGB的像素值   
        int[][] gray = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                gray[x][y] = getGray(bi.getRGB(x, y));//  得到该点的平均像素 
            }
        }

        BufferedImage nbi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
        int SW = 160;  //  设置阈值
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (getAverageColor(gray, x, y, w, h) > SW) {  ///  改点和周围八个点共同比较比较
                    int max = new Color(255, 255, 255).getRGB();
                    nbi.setRGB(x, y, max);
                } else {
                    int min = new Color(0, 0, 0).getRGB();
                    nbi.setRGB(x, y, min);
                }
            }
        }

        ImageIO.write(nbi, "BMP", new File("H:/いろいろな図片/壁纸/二值化后_无压缩.bmp"));
    }

    public static void main(String[] args) throws IOException {
        new BinaryTest();
    }

    public static int getGray(int rgb) {
        String str = Integer.toHexString(rgb);
        int r ;
        int g ;
        int b; 
        //or 直接new个color对象   
        Color c = new Color(rgb);
        r = c.getRed();
        g = c.getGreen();
        b = c.getBlue();
        int top = (r + g + b) / 3;
        return (int) (top);
    }

    /**
     * 自己加周围8个灰度值再除以9，算出其相对灰度值
     *
     * @param gray
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    public static int getAverageColor(int[][] gray, int x, int y, int w, int h) {
        int rs = gray[x][y]
                + (x == 0 ? 255 : gray[x - 1][y])//  左边界
                + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])//  左下角
                + (y == 0 ? 255 : gray[x][y - 1])
                + (y == h - 1 ? 255 : gray[x][y + 1])
                + (x == w - 1 ? 255 : gray[x + 1][ y])
                + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])//  右边界
                + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }
}
