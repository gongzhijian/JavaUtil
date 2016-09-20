package com.fh.util;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * DateTime: 2016/9/20 13:21
 * 功能：二维码
 * 思路：
 */
public class TwoDimensionCode {
    //测试
    public static void main(String[] args) throws Exception {
        //生成二维码
        encoderQRCode("http://www.yangenneng.cn/","D:\\Users\\yangenneng0\\Pictures\\收藏\\2.jpg");
        //解析二维码
        System.out.println(decoderQRCode("D:\\Users\\yangenneng0\\Pictures\\收藏\\2.jpg"));
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content   存储内容
     * @param imgPath   图片路径
     */
    public static void encoderQRCode(String content,String imgPath){
        encoderQRCode(content,imgPath,"png",2);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content    存储内容
     * @param output     输出流
     */
    public static void encoderQRCode(String content, OutputStream output){
        encoderQRCode(content,output,"png",2);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content    存储内容
     * @param imgPath    图片路径
     * @param imgType    图片类型
     */
    public static void encoderQRCode(String content,String imgPath,String imgType){
        encoderQRCode(content,imgPath,imgType,2);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content    存储内容
     * @param out   输出流
     * @param imgType   图片类型
     */
    public static void encoderQRCode(String content,OutputStream out,String imgType){
        encoderQRCode(content,out,imgType,2);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content   存储内容
     * @param imgPath   图片路径
     * @param imgType   图片类型
     * @param size  二维码尺寸
     */
    public static void encoderQRCode(String content,String imgPath,String imgType,int size){
        try {
            BufferedImage bufImg=qRCodeCommon(content,imgType,size);
            File imgFile=new File(imgPath);
            // 生成二维码QRCode图片
            ImageIO.write(bufImg,imgType,imgFile);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static void encoderQRCode(String content,OutputStream out,String imgType,int size){
        try {
            BufferedImage bufImg=qRCodeCommon(content,imgType,size);
            //生成二维码QRCode图片
            ImageIO.write(bufImg,imgType,out);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码(QRCode)图片的公共方法
     * @param content   存储内容
     * @param imgType   图片类型
     * @param size  图片大小
     * @return
     */
    private static BufferedImage qRCodeCommon(String content,String imgType,int size){
        BufferedImage bugImg=null;
        size=10;
        try {
            Qrcode qrcodeHandler=new Qrcode();
            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%),排错率越高可存储的信息越少，但对二维码清晰度要求较小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            //设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(size);
            //获得内容的字节数组，设置编码格式
            byte[] contentBytes=content.getBytes("UTF-8");
            //图片尺寸
            int imgSize=67+12*(size-1);
            bugImg=new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
            Graphics2D gs=bugImg.createGraphics();
            //设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0,0,imgSize,imgSize);
            //设置图像颜色 > BALCK
            gs.setColor(Color.BLACK);
            //设置偏移量，不设置可能导致解析错误
            int pixoff=2;
            //输出内容 > 二维码
            if(contentBytes.length > 0 && contentBytes.length < 800){
                boolean[][] codeOut=qrcodeHandler.calQrcode(contentBytes);
                for ( int i = 0; i <codeOut.length ; i++ ) {
                    for ( int j = 0; j < codeOut.length; j++ ) {
                        if(codeOut[j][i]){
                            gs.fillRect(j*3+pixoff,i*3+pixoff,3,3);
                        }
                    }
                }
            }else {
                throw new Exception("QRCode content bytes length = "+contentBytes.length+" not in [0, 800].");
            }
            gs.dispose();
            bugImg.flush();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return bugImg;
    }

    /**
     * 解析二维码（QRCode）
     * @param imgPath   图片路径
     * @return
     * @throws Exception
     */
    public static String decoderQRCode(String imgPath) throws Exception{
        // QRCode 二维码图片的文件
        File imageFile=new File(imgPath);
        BufferedImage bufImg=null;
        String content=null;
        try {
            bufImg= ImageIO.read(imageFile);
            QRCodeDecoder decoder=new QRCodeDecoder();
            content=new String(decoder.decode(new TwoDimensionCodeImage(bufImg)),"UTF-8");
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 解析二维码（QRCode）
     * @param input 输入流
     * @return
     */
    public  static String decoderQRCode(InputStream input){
        BufferedImage bugImg=null;
        String content=null;
        try {
            bugImg=ImageIO.read(input);
            QRCodeDecoder decoder=new QRCodeDecoder();
            content=new String(decoder.decode(new TwoDimensionCodeImage(bugImg)),"utf-8");

        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return content;
    }


}
