import java.util.UUID;

/**
 * DateTime: 2016/9/18 22:15
 * 功能：
 * UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
 * 算法的核心思想是结合机器的网卡、当地时间、一个随机数来生成GUID。
 * 从理论上讲，如果一台机器每秒产生10000000个GUID，则可以保证（概率意义上）3240年不重复。
 * 思路：
 */
public class UuidUtil {
    //测试
    public static void main(String[] args) {
        System.out.println(get32UUID());
        System.out.println(get32UUID());
        System.out.println(get32UUID());
    }
    public static String get32UUID(){
        String uuid= UUID.randomUUID().toString().trim().replaceAll("-","");
        return uuid;
    }


}
