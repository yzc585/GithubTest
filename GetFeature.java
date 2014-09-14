/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author zhichao
 */
public class GetFeature {

    File picture = new File("H:/いろいろな図片/壁纸/二值化后压缩.bmp");
    BufferedImage bi;
    private int h;
    private int w;
    private int counter = 0;
    private int counter_y = 0;
    private int counter_x = 0;
    private String feature[][] = new String[60][20];
    private   int fileCount =1;
    public GetFeature() throws IOException {
        bi = ImageIO.read(picture);
        h = bi.getHeight();//获取图像的高 
        w = bi.getWidth();//获取图像的宽 
        File f = new File("H:/いろいろな図片/壁纸/特征"+AllNew.fileSh+".txt");
        fileCount++;
        //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        // ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
        out.write(""+AllNew.xunlianzi+"\n");
        //  BufferedWriter in =new BufferedWriter
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        for (int y = 0; y < h; y++) {//   由上到下一行行检索
            for (int x = 0; x < w; x++) {
                int rgb = bi.getRGB(x, y);
                if (counter_y != y) {
                    counter = 0;
                    counter_y = y;
                    out.write("\n");
                }
                if (x + 1 == 60) {
                    if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x - 1, y) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x - 1, y) != rgb)) {
                      
                        feature[y][counter] = new String(x + " "); 
                        counter++;
                        out.write(feature[y][counter - 1]);
                    } else {
                        out.write(0 + " ");
                    }
                    break;
                }

                if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x + 1, y) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x + 1, y) != rgb)) {

                    feature[y][counter] = new String(x + " ");//   y表示第几行  x表示第y行的第x个位置  整个式子表示[y][x]处是黑色
                    counter++;
//                    System.out.println(y + " " + x);
//                    System.out.println(counter);
//                    System.out.println("te" + feature[y][counter - 1]);

                    out.write(feature[y][counter - 1]);
                    //fw.write(feature[y][counter - 1]);
                } else {
                    out.write(0 + " ");
                }
            }
        }
        out.write("\n");
        /// 一列列检索
        for (int x = 0; x < w; x++) {//   由上到下一行行检索
            for (int y = 0; y < h; y++) {
                int rgb = bi.getRGB(x, y);
                if (counter_x !=x) {
                    counter = 0;
                    counter_x = x;
                    out.write("\n");
                }
                if (y + 1 == 60) {
                    if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x, y-1) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x , y- 1) != rgb)) {
                      
                        feature[y][counter] = new String(y + " "); 
                        counter++;
                        out.write(feature[y][counter - 1]);
                    } else {
                        out.write(0 + " ");
                    }
                    break;
                }

                if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x, y + 1) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x, y + 1) != rgb)) {

                    feature[y][counter] = new String(y + " ");//   y表示第几行  x表示第y行的第x个位置  整个式子表示[y][x]处是黑色
                    counter++;
                    System.out.println(y + " " + x);
                    System.out.println(counter);
                    System.out.println("te" + feature[y][counter - 1]);

                    out.write(feature[y][counter - 1]);
                    //fw.write(feature[y][counter - 1]);
                } else {
                    out.write(0 + " ");
                }
            }
        }
        out.write("\n");
        out.close();

        do {
            try {

                System.out.println(in.readLine());//in.read()+
            } catch (Exception e) {
                System.out.println("异常");
            }

        } while (in.read() != -1);
        in.close();
    }

    public static void main(String args[]) throws IOException {
        GetFeature feature1 = new GetFeature();
    }
}
