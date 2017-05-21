import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DateTime: 2016/9/19 21:38
 * 功能：水印
 * 思路：
 */
public class Watermark {
    //测试
    public static void main(String[] args) {

    }

    private static String strFWATERM;   //文字水印
    private static String strIWATERM;   //图片水印

    static {
        strFWATERM=Tools.readTxtFile(Const.FWATERM);
        strIWATERM=Tools.readTxtFile(Const.IWATERM);
    }

    public static void fushValue(){
        strFWATERM = Tools.readTxtFile(Const.FWATERM);	//读取文字水印配置
        strIWATERM = Tools.readTxtFile(Const.IWATERM);	//读取图片水印配置
    }

    /**
     *添加图片水印及文字水印
     * @param imagePath 图片全路径
     */
    public static void setWATERM(String imagePath){
        //文字水印
        if(null != strFWATERM && "".equals(strFWATERM)){
            String strFW[]=strFWATERM.split(",fh,");
            if(strFW.length==5){
                if("yes".equals(strFW[0])){
                    pressText(strFW[1].toString(), imagePath, "", 1, Color.RED,Integer.parseInt(strFW[2]), Integer.parseInt(strFW[3]), Integer.parseInt(strFW[4]));	//文字
                }
            }//end of  if(strFW.length==5){
        }

        //图片水印
        if(null!=strIWATERM && "".equals(strIWATERM)){
            String[] strIW=strIWATERM.split(",fh,");
            if(4==strIW.length){
                if("yes".equals(strIW[0])){
                    pressImage(PathUtil.getClasspath() + Const.FILEPATHIMG+strIW[1], imagePath, Integer.parseInt(strIW[2]), Integer.parseInt(strIW[3]));
                }
            }
        }

    }

    /**
     * 打印文字水印图片
     * @param pressText 文字
     * @param targetImg 目标图片
     * @param fontName  字体名
     * @param fontStyle 字体样式
     * @param color 字体颜色
     * @param fontSize  字体大小
     * @param x 偏移量
     * @param y 偏移量
     */
    public static void pressText(String pressText, String targetImg, String fontName, int fontStyle,
                                 Color color,int fontSize,int x,int y){
        try {
            File _file=new File(targetImg);
            Image src= ImageIO.read(_file);
            int weidth=src.getWidth(null);
            int height=src.getHeight(null);
            BufferedImage image=new BufferedImage(weidth,height,BufferedImage.TYPE_INT_RGB);
            Graphics g=image.createGraphics();
            g.drawImage(src,0,0,weidth,height,null);
            g.setColor(color);
            g.setFont(new Font(fontName,fontStyle,fontSize));
            g.drawString(pressText,x,y);
            g.dispose();
            FileOutputStream out=new FileOutputStream(targetImg);
            JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 把图片印刷到图片上
     * @param pressImg  水印文件
     * @param targetImg 目标文件
     * @param x x坐标
     * @param y y坐标
     */
    public final static void pressImage(String pressImg,String targetImg,int x,int y){
        try {
            //目标文件
            File _file=new File(targetImg);
            Image src=ImageIO.read(_file);

            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            //水印文件
            File _filebiao=new File(pressImg);
            Image src_biao=ImageIO.read(_filebiao);
            int weight_biao=src_biao.getWidth(null);
            int height_biao=src_biao.getHeight(null);

            g.drawImage(src_biao,x,y,weight_biao,height_biao,null);

            //水印结束
            g.dispose();
            FileOutputStream out=new FileOutputStream(targetImg);
            JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

}
