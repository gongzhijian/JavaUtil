/**
 * DateTime: 2016/9/18 19:17
 * 功能：将以逗号分隔的字符串转化为字符数组
 * 思路：定义一个数组，用来存放分割后的字符串，长度为当前长度-去掉空格的长度+1 eg:abc,def,hij 11-9+1=3
 * 在字符串后面加一个"," 从开始取到第一个',' 然后让字符串截取为这个','后的第一个字符，再循环截取，直到最后一个','
 */
public class StringUtil {

    public static void main(String[] args) {
        strList("abc,def,hig");
    }
    /**
     * 将以逗号分隔的字符串转化为字符数组
     * @param valStr
     * @return
     */
    public static String[] strList(String valStr){
        int i=0;
        String TempStr=valStr;
        String[] returnStr=new String[valStr.length()+1-TempStr.replace(",","").length()];
        valStr=valStr+",";
        while(valStr.indexOf(',')>0){
            returnStr[i]=valStr.substring(0,valStr.indexOf(','));
            valStr=valStr.substring(valStr.indexOf(',')+1,valStr.length());
            i++;
        }
        return returnStr;
    }
}
