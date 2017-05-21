import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * DateTime: 2016/9/20 16:39
 * 功能：
 * 思路：
 */
public class TwoDimensionCodeImage implements QRCodeImage {

    BufferedImage bufImg;

    public TwoDimensionCodeImage(BufferedImage buf){
        this.bufImg=buf;
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public int getPixel(int i, int i1) {
        return bufImg.getRGB(i,i1);
    }
}
