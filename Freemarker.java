import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * DateTime: 2016/9/17 15:18
 * 功能：将指定的ftl模板输出到文件
 * 思路：
 */
public class Freemarker {

    //测试
    public static void main(String[] args) throws Exception {
        Map<String,Object> root=new HashMap<String, Object>();

        root.put("packageName", "Hello");						//包名
        root.put("objectName", "Demo");							//类名

        print("controllerTemplate.ftl",root,"createCode");
    }

    /**
     * 通过文件名加载模板
     * @param ftlName   ftl文件名
     * @param ftlPath   ftl文件路径
     * @return
     * @throws Exception
     */
    public static Template getTemplate(String ftlName,String ftlPath) throws Exception{
        try {
            Configuration cfg=new Configuration();  //通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA,"utf-8");
            cfg.setDirectoryForTemplateLoading(new File(PathUtil.getClassResources()+"/ftl/"+ftlPath));//设定去哪里读取相应的ftl模板文件
            Template template=cfg.getTemplate(ftlName);//在模板文件目录中找到名称为name的文件
            return template;
        }catch ( IOException e ){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 打印到控制台
     * @param ftlName
     * @param root
     * @param ftlPath
     * @throws Exception
     */
    public static void print(String ftlName,Map<String,Object>root,String ftlPath) throws Exception{
        try {
            Template template=getTemplate(ftlName,ftlPath);
            template.process(root,new PrintWriter(System.out));
        }catch ( TemplateException te ){
            te.printStackTrace();
        }catch ( IOException ie ){
            ie.printStackTrace();
        }
    }

    /**
     * 输出到文件
     * @param ftlName   ftl文件名
     * @param root      传入的map
     * @param outFile   输出后的文件全部路径
     * @param filePath  输出前的文件全部路径
     * @param ftlPath   ftl所在路径
     * @throws Exception
     */
    public static void printFile(String ftlName, Map<String,Object> root,String outFile,String filePath,String ftlPath) throws Exception{
        try {
            File file=new File(PathUtil.getClasspath()+filePath+outFile);
            if(!file.getParentFile().exists()){ //判断有没有父路径，就是判断文件整个路径是否存在
                file.getParentFile().mkdir();   //不存在就全部创建
            }
            Writer out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
            Template template=getTemplate(ftlName,filePath);
            template.process(root,out);
            out.flush();
            out.close();
        }catch ( TemplateException te ){
            te.printStackTrace();
        }catch ( IOException e ){
            e.printStackTrace();
        }
    }
}
