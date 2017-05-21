import java.io.File;

/**
 * DateTime: 2016/9/18 20:42
 * 功能：删除所有文件及文件夹
 * 思路：
 */
public class DelAllFile {
    //测试
    public static void main(String[] args) {
        delFolder("G:/demo/dira"); //删除dira文件夹下的文件，dira也会删除
        delAllFile("G:/demo/dira"); //只删除dira文件夹下的文件，dira不会删除
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path  文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path){
        boolean flag=false;
        File file=new File(path);
        if(!file.exists()){ //如果不存在
            return  flag;
        }
        if(!file.isDirectory()){    //如果不是文件夹
            return flag;
        }

        String[] tempList=file.list();
        File temp=null;

        for ( int i = 0; i <tempList.length ; i++ ) {

            if(path.endsWith(File.separator)){  //File.separator文件分隔符
                temp=new File(path+tempList[i]);
            }else {
                temp=new File(path+File.separator+tempList[i]);
            }

            if(temp.isFile()){
                temp.delete();
            }
            if(temp.isDirectory()){
                delAllFile(path+"/"+tempList[i]);//先删除文件夹里边的文件
                delFolder(path+"/"+tempList[i]);//再删除空文件夹
                flag=true;
            }
        }//end of  for ( int i = 0; i <tempList.length ; i++ ) {
        return flag;
    }

    /**
     * 只删除此路径的最末路径下所有文件和文件夹
     * @param folderPath 文件路径 (
     */
    private static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath=folderPath;
            filePath=filePath.toString();
            File myFilePath=new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

}
