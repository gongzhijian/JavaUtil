import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * DateTime: 2016/9/18 21:41
 * 功能：文件上传
 * 思路：
 */
public class FileUpload {
    //测试
    public static void main(String[] args) {

    }

    /**
     * 写文件到当前目录的upload目录中
     * @param in
     * @param dir
     * @param realName
     * @return
     */
    public static String copyFile(InputStream in,String dir,String realName) throws IOException {
        File file=new File(dir,realName);
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            file.createNewFile();
        }
        FileUtils.copyInputStreamToFile(in,file);
        return realName;
    }

    /**
     * 文件上传
     * @param file  文件对象
     * @param filePath  上传路径
     * @param fileName  文件名
     * @return
     */
    public static String fileUp(MultipartFile file,String filePath,String fileName){
        String extName="";  //后缀
        try {
            if(file.getOriginalFilename().lastIndexOf(".")>=0){
                extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            copyFile(file.getInputStream(),filePath,fileName+extName).replace("-","");
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return fileName+extName;
    }

}
