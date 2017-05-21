import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateTime: 2016/9/19 21:43
 * 功能：工具类
 * 思路：
 */
public class Tools {
    //测试
    public static void main(String[] args) {
        System.out.println(getRandomNum());
        System.out.println(notEmpty(""));
        System.out.println(str2StrArray("abc,def,hig")[0]);
        System.out.println(date2Str(new Date()));

        Date old=new Date();
        try {
            Thread.sleep(1500);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println(getTimes(date2Str(old)));

        System.out.println(checkEmail("1295531995@qq.com"));
        System.out.println(checkEmail("1295531995.@qq.com"));

        System.out.println(checkMobileNumber("18468194782"));
        System.out.println(checkMobileNumber("1852645"));

        System.out.println(readTxtFile("../web/admin/config/EMAIL.txt"));

    }

    //生成六位随机数验证码
    public static int getRandomNum(){
        Random r=new Random();
        return r.nextInt(900000)+100000;
    }

    //检测字符串是否不为null 或 ""
    public static boolean notEmpty(String s){
        return s!=null && !"".equals(s) && !"null".equals(s);
    }

    //检测字符串是否为null 或 ""
    public static boolean isEmpty(String s){
        return s==null || "".equals(s) || "null".equals(s);
    }

    //字符串转化为字符串数组
    public static String[] str2StrArray(String str,String splitRegex){
        if(isEmpty(str)){
            return null;
        }
        return str.split(splitRegex);
    }

    //用默认的分隔符(,)将字符串转换为字符串数组
    public static String[] str2StrArray(String str){
        return str2StrArray(str,",\\s*");
    }

    //按照指定格式，日期转为字符串
    public static String date2Str(Date date,String format){
        if(null!=date){
            SimpleDateFormat sdf=new SimpleDateFormat(format);
            return sdf.format(date);
        }else return "";
    }

    //按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
    public static String date2Str(Date date){
        return date2Str(date,"yyyy-MM-dd HH:mm:ss");
    }

    //按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
    public static Date str2Date(String str){
        if(notEmpty(str)){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(str);
            } catch ( ParseException e ) {
                e.printStackTrace();
            }
            return new Date();
        }else return null;
    }

    //把时间根据时、分、秒转换为时间段
    public static String getTimes(String strDate){
        String resultTimes="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;

        try {
            now = new Date();                 //现在时间
            Date date = sdf.parse(strDate);   //传入时间
            long times = now.getTime()-date.getTime();
            long day  =  times/(24*60*60*1000);
            long hour = (times/(60*60*1000)-day*24);
            long min  = ((times/(60*1000))-day*24*60-hour*60);
            long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);

            StringBuffer sb=new StringBuffer();
            if(hour>0){
                sb.append(hour+"小时前");
            }else if(min>0){
                sb.append(min+"分钟前");
            }else {
                sb.append(sec+"秒前");
            }
            resultTimes=sb.toString();

        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    return resultTimes;
    }

    //写txt里的单行内容
    public static void writeFile(String fileP,String content){
        String filePath=String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";    //项目路径
        filePath=(filePath.trim()+fileP.trim()).substring(6).trim();
        if(filePath.indexOf(":")!=1){
            filePath= File.separator+filePath;
        }

        try {
            OutputStreamWriter write=new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    //验证邮箱
    public static boolean checkEmail(String email){
        boolean flag=false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex=Pattern.compile(check);
            Matcher matcher=regex.matcher(email);
            flag=matcher.matches();
        }catch ( Exception e ){
            flag=false;
        }
        return flag;
    }

    //验证手机号码
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag=false;
        try {
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher=regex.matcher(mobileNumber);
            flag=matcher.matches();
        }catch ( Exception e ){
            flag=false;
        }
        return flag;
    }

    //检测KEY是否正确
    public static boolean checkKey(String paraname,String FKEY){
        paraname=(null==paraname)?"":paraname;
        return MD5.md5(paraname+DateUtil.getDays()+",fh,").equals(FKEY);
    }

    //读取txt里的单行内容
    public static String readTxtFile(String fileP){
        try {
            String filePath=String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";//项目路径
            filePath=filePath.replaceAll("file:/", "");
            filePath=filePath.replaceAll("%20", " ");
            filePath=filePath.trim()+fileP.trim();
            if(filePath.indexOf(":")!=1){
                filePath=File.separator+filePath;
            }
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);   // 考虑到编码格式
                BufferedReader bufferedReader=new BufferedReader(read);
                String lineTxt=null;
                while ( (lineTxt=bufferedReader.readLine())!=null ){
                    return lineTxt;
                }
                read.close();
            }else {
                System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return "";
    }

}
