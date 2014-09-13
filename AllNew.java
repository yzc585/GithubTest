/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;
//  yzc
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author zhichao
 */
public class AllNew extends JFrame {

    private int x;
    private int y;
    private JLabel shxie;
    private JLabel showWords;
    private JPanel jPanel = new JPanel();
    private JPanel shxieban = new JPanel();
    private JLabel showarea = new JLabel();
    private BufferedImage iamge = new BufferedImage(250, 250, BufferedImage.TYPE_3BYTE_BGR);
    private JButton quxiao = new JButton("取消");
    private JButton shibie = new JButton("识别");
     private JButton xunlian = new JButton("训练");
    private JTextField hanzi = new JTextField("");
    private boolean flag = true;
    public static int fileSh=43;
    public static  String xunlianzi ;
    public AllNew() {
        this.setLayout(null);
        shxie = new JLabel();
        jPanel = new JPanel();
        shxie.setText("手写");
        showWords = new JLabel();
        showWords.setText("识别的字");
        shxie.setBounds(40, 50, 60, 40);
        showWords.setBounds(350, 50, 60, 40);
        shxieban.setBounds(40, 100, 250, 250);
        showarea.setBounds(350, 100, 250, 250);

        shxieban.setBackground(Color.WHITE);
        showarea.setBackground(Color.WHITE);
        quxiao.setText("取消");
        shibie.setText("识别");
        quxiao.setBounds(670, 150, 60, 30);
        shibie.setBounds(670, 100, 60, 30);
        shxieban.setToolTipText("在此处手写汉字！");
        xunlian.setText("训练");
        xunlian.setBounds(670, 230, 60, 30);
        hanzi.setBounds(670, 200, 60, 30);
        add(xunlian);
        add(hanzi);
        add(shibie);
        add(quxiao);
        add(shxie);
        add(showWords);
        add(shxieban);
        add(showarea);
        // this.add(jPanel);
        this.setLocation(100, 100);
        this.setSize(500, 500);
        // this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent obj) {
                System.exit(1);
            }
        });
        shxieban.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                System.out.println(e.getX() + "   " + e.getY());
                x = e.getX();
                y = e.getY();

                Graphics g = shxieban.getGraphics();
                Graphics g1 = iamge.getGraphics();

                g.setColor(Color.red);
                g1.setColor(Color.red);
                //  
                Font ftFont = new Font("Serif", Font.BOLD, 52);
                g.setFont(ftFont);
                g1.setFont(ftFont);
                // g.drawLine(x, y, x +1, y + 1);
                g.fillOval(x, y, 10, 10);
                g1.fillOval(x, y, 10, 10);
                //  g.drawImage(g, x, y, shxie);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        shxieban.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (flag) {
                    Graphics g = shxieban.getGraphics();
                    Graphics g1 = iamge.getGraphics();
                    g.setColor(Color.WHITE);
                    g1.setColor(Color.WHITE);
                    g.fillRect(0, 0, 250, 250);
                    g1.fillRect(0, 0, 250, 250);
                    try {
                        ImageIO.write(iamge, "JPG", new File("H:/いろいろな図片/壁纸/222.jpg"));
                        //g.drawImage((ImageIcon)shxieban, 0, 0, bi1.getWidth(), bi1.getHeight(), 0, 0, width, height, null);

                    } catch (IOException ex) {
                        Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flag = false;       
                }
            }
        });
        shibie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(iamge.getRGB(4, 4));
                try {
                      hanzi.setText("");
                    ImageIO.write(iamge, "JPG", new File("H:/いろいろな図片/壁纸/222.jpg"));
                    //g.drawImage((ImageIcon)shxieban, 0, 0, bi1.getWidth(), bi1.getHeight(), 0, 0, width, height, null);

                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }     
                try {
                    ErZhiHua();
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }               
                try {      
                    Cut();
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }
                GuiYiHua();
                try {
                    Compare compare=new Compare();
                    showarea.setFont(new Font(Font.MONOSPACED,Font.BOLD,150));
                    showarea.setText(compare.getWord());
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
        });
        quxiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //shxieban.set
                Graphics g = shxieban.getGraphics();
                Graphics g1 = iamge.getGraphics();
                g.setColor(Color.WHITE);
                g1.setColor(Color.WHITE);
                g.fillRect(0, 0, 250, 250);
                g1.fillRect(0, 0, 250, 250);
                try {
                    ImageIO.write(iamge, "JPG", new File("H:/いろいろな図片/壁纸/222.jpg"));
                    //g.drawImage((ImageIcon)shxieban, 0, 0, bi1.getWidth(), bi1.getHeight(), 0, 0, width, height, null);

                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }
                showarea.setText("");
            }
        });
        Background();
        xunlian.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               xunlianzi=hanzi.getText();
               fileSh++;
               GetFeature getFeature;
                try {
                     getFeature=new GetFeature();
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }
               try {
                    ImageIO.write(iamge, "JPG", new File("H:/いろいろな図片/壁纸/222.jpg"));
                    //g.drawImage((ImageIcon)shxieban, 0, 0, bi1.getWidth(), bi1.getHeight(), 0, 0, width, height, null);

                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }     
                try {
                    ErZhiHua();
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }               
                try {      
                    Cut();
                } catch (IOException ex) {
                    Logger.getLogger(AllNew.class.getName()).log(Level.SEVERE, null, ex);
                }
                GuiYiHua();
                
            }
        });

    }
//    public void ErZhiHua() {
//        ImageIcon imageIcon = new ImageIcon("H:/いろいろな図片/壁纸/222.jpg");
//        try {
//            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_3BYTE_BGR);
//
//            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
//
//            g2D.drawImage(imageIcon.getImage(), 0, 0,null);
//           //         imageIcon.getImageObserver());
//            g2D.setColor(Color.WHITE);
//            g2D.drawRect(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//            //循环每一个像素点，改变像素点的Alpha值
//
//            int alpha = 100;
//
//            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
//
//                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
//
//                    int rgb = bufferedImage.getRGB(j2, j1);
//                    int R = (rgb & 16711680) >> 16;
//                    int G = (rgb & 65280) >> 8;
//                    int B = (rgb & 255);
//                    // bufferedImage.get;
//                    if (R <=255 && R>200 && G <= 100 && B <= 100) {//&& G<=255 &&G>=100 && B<=255 &&B>=100
//                        bufferedImage.setRGB(j2, j1,new Color(255,0,0).getRGB());//  
//
//                    } else {
//                      bufferedImage.setRGB(j2, j1, new Color(255,255,255).getRGB());
//                    }
//                    // rgb = ((alpha + 1) ) | (rgb & 0xffffff);
//                  //  System.out.println(rgb + "   " + R + "  " + G + "   " + B);
//
//
//                }
//
//            }
//
//            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
//
//
//            ImageIO.write(bufferedImage, "JPG", new File("H:/いろいろな図片/壁纸/2223.jpg"));
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    }

    public void GuiYiHua() {
        
    }

    public void ErZhiHua() throws IOException {
        new BinaryTest();
    }

    public void Cut() throws IOException {
    new CutTest();
      
    }

    public void Background() {
        ImageIcon background = new ImageIcon("H:/いろいろな図片/壁纸/背景2.jpg");// 背景图片
        JLabel jLabel = new JLabel(background);// 把背景图片显示在一个标签里面
        // 把标签的大小位置设置为图片刚好填充整个面板
        jLabel.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 内容窗格默认的布局管理器为BorderLayout
        //imagePanel.setLayout(new FlowLayout());
        //imagePanel.add(new JButton("测试按钮"));

        this.getLayeredPane().setLayout(null);
        // 把背景图片添加到分层窗格的最底层作为背景
        this.getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
    }

    public static void main(String args[]) {
        new AllNew().setVisible(true);
    }
}
