package com.util;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * DateTime: 2016/9/18 21:01
 * 功能：下载文件
 * 思路：
 */
public class FileDownload {
    //测试
    public static void main(String[] args) {

    }

    /**
     * 下载文件
     * @param response
     * @param filePath  文件完整路径(包括文件名和扩展名)
     * @param fileName  下载后看到的文件名
     */
    public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws IOException {

        byte[] data=FileUtil.toByteArray2(filePath);
        fileName= URLEncoder.encode(fileName,"UTF-8");
        response.reset();
        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
        response.flushBuffer();

    }
}
