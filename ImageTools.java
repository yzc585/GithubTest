/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCountPackage;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
public class ImageTools {
   
/**截取图片*/
public static File cutting(File file,int width,int height){
  return cutting(file,0,0,width,height);
}
/**截取图片*/
public static File cutting(File file,int x,int y,int width,int height){
  try{
   String endName=file.getName();
   endName=endName.substring(endName.lastIndexOf(".")+1);
   Iterator<ImageReader> readers=ImageIO.getImageReadersByFormatName(endName); 
   ImageReader reader = (ImageReader)readers.next(); 
   InputStream is=new FileInputStream(file);
   ImageInputStream iis = ImageIO.createImageInputStream(is); 
   reader.setInput(iis,true); 
   ImageReadParam param = reader.getDefaultReadParam(); 
   Rectangle rect = new Rectangle(x,y,width,height); 
   param.setSourceRegion(rect); 
            BufferedImage bi = reader.read(0,param);
            String path=file.getAbsolutePath();
            path=path.substring(0,path.lastIndexOf("\\"));
           // path=path+"[url=file://\\]\\"+width+"-"+height[/url];
            File newFile=new File(path);
            if(!newFile.exists())newFile.mkdirs();
            newFile=new File(path,file.getName());
            ImageOutputStream out=ImageIO.createImageOutputStream(new FileOutputStream(newFile));
            ImageIO.write(bi,endName,out);
            file=newFile;
  }catch(Exception e){e.printStackTrace();}
  return file;
}

public static void main(String args[]){
  File file=new File("E:/2.jpg");
  cutting(file,200,200,400,400);
}

 


}
