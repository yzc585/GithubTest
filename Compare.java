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
public class Compare {

    File picture = new File("H:/いろいろな図片/壁纸/二值化后压缩.bmp");
    BufferedImage bi;
    private int h;
    private int w;
    private int counter = 0;
    private int counter_all = 0;
    private String feature[][] = new String[60][20];
    private int counter_y = 0;
    private int conter_x = 0;
    private int counter_x = 0;
    public   String word;

    public  String getWord() {
        return word;
    }
    private String fromWJ[] = new String[120];
    private String pictemp1[] = new String[120];
    private int simCount = 0;// 相似度
    private int fileCount = AllNew.fileSh;//  文件数目

    public Compare() throws IOException {
        bi = ImageIO.read(picture);
        h = bi.getHeight();//获取图像的高 
        w = bi.getWidth();//获取图像的宽 
        int i = 0;
        int j = 0;
        int line = 0;
        String pictemp = "";//  存储来自图片的rgb值  最后存储在pictemp1 之中
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = bi.getRGB(x, y);
                if (x + 1 == 60) {
                    if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x - 1, y) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x - 1, y) != rgb)) {
                        pictemp = pictemp + x + " ";
                    } else {
                        pictemp = pictemp + 0 + " ";
                    }
                    pictemp1[j] = new String(pictemp);
                    j++;
                    pictemp = "";
                    break;
                }
                if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x + 1, y) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x + 1, y) != rgb)) {
                    pictemp = pictemp + x + " ";

                } else {
                    pictemp = pictemp + 0 + " ";
                }
            }
        }
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgb = bi.getRGB(x, y);
                if (y + 1 == 60) {
                    if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x, y - 1) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x, y - 1) != rgb)) {
                        pictemp = pictemp + y + " ";
                    } else {
                        pictemp = pictemp + 0 + " ";
                    }
                    pictemp1[j] = new String(pictemp);
                    j++;
                    pictemp = "";
                    break;
                }
                if ((rgb == new Color(0, 0, 0).getRGB() && bi.getRGB(x, y + 1) != rgb) || (rgb == new Color(255, 255, 255).getRGB() && bi.getRGB(x, y + 1) != rgb)) {
                    pictemp = pictemp + y + " ";

                } else {
                    pictemp = pictemp + 0 + " ";
                }
            }
        }
        for (int k = 1; k <= fileCount; k++) {
            File f = new File("H:/いろいろな図片/壁纸/特征" + k + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String tempword = in.readLine();
            System.out.println(tempword);
            // fileCount++;
            line = 0;
            i = 0;
            while (true) {
                String temp = "";
                try {
                    line++;//   line初值为0
                    temp = in.readLine();
                    fromWJ[i] = new String(temp);
                    i++;
                    if (line == 120) {
                      //  show();
                        int tempI = bijiao(fromWJ, pictemp1);
                        fromWJ = new String[120];
                        if (tempI >= simCount) {
                            simCount = tempI;
                            word = tempword;
                            System.out.println(word + tempword + i);
                        
                            // continue;
                            break;
                        } 
                    }
                } catch (Exception e) {
                    System.out.println("异常");
                    break;
                }
            }
            in.close();
        }

        System.out.println(word);
        System.out.println(simCount);

    }

    public static void main(String args[]) throws IOException {
        Compare feature1 = new Compare();
    }

    private void show() {
        for (int i = 0; i < 120; i++) {
            System.out.println("a" + "  " + i + "  " + fromWJ[i].length() + "  " + fromWJ[i]);
            System.out.println("b" + "  " + i + "  " + pictemp1[i].length() + "  " + pictemp1[i]);
        }
    }

    private int bijiao(String[] fromWJ, String[] pictemp1) {//  得到图片和库值中相似的个数
        int c = 0;
//        String a []=pictemp1[0].split(" ");
//        for (int i = 0; i <a.length; i++) {
//            System.out.print(a[i]+" ");
//        }
        for (int i = 0; i < 120; i++) {

            String temp[] = fromWJ[i].split(" ");// 来自文件
            String temp1[] = pictemp1[i].split(" ");//来自图片
            for (int j = 0; j < temp1.length; j++) {
                for (int k = 0; k < temp.length; k++) {
                    if (Integer.parseInt(temp1[j]) > 0 && Integer.parseInt(temp[k]) > 0 && Integer.parseInt(temp1[j]) - Integer.parseInt(temp[k]) >= -2 && Integer.parseInt(temp1[j]) - Integer.parseInt(temp[k]) <= 2) {
                        c++;
                    }

                }
            }
        }
        return c;
    }

   
    
}
