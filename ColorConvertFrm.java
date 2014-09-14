/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;

public class ColorConvertFrm extends JFrame {

    ColorConvert panel;
    Container contentPane;
    BorderLayout borderLayout1 = new BorderLayout();

    public ColorConvertFrm() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        panel = new ColorConvert();
        contentPane = getContentPane();
        contentPane.add(panel);
        this.setSize(new Dimension(panel.w * 2, panel.h + 25));
        this.setTitle("应用Java 2D API进行图像颜色空间转换处理");
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    public class ColorConvert extends JPanel {

        private Image img;
        public int w;
        public int h;

        public ColorConvert() {
            img = getToolkit().getImage(
                    ClassLoader.getSystemResource("H:/いろいろな図片/壁纸/222.jpg"));
            MediaTracker mt = new MediaTracker(this); // 加载图片
            mt.addImage(img, 0);
            try { // 判断图片是否完全加载
                mt.waitForAll();
            } catch (Exception err) {
                err.printStackTrace();
            }
            w = img.getWidth(this);
            h = img.getHeight(this);
            this.setSize(w * 2, h);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D big = bi.createGraphics();
            big.drawImage(img, 0, 0, this);
            BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            ColorConvertOp cop = new ColorConvertOp(cs, null); // 创建灰化颜色转换器
            cop.filter(bi, bimg);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, 0, 0, w, h, null);
            g2d.drawImage(bimg, w, 0, w, h, null);
        }
    }

    public static void main(String[] args) {
        ColorConvertFrm colorConvertFrm = new ColorConvertFrm();
        colorConvertFrm.show();
    }
}
