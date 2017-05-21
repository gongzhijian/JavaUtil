package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateTime: 2016/9/18 19:56
 * 功能：日期处理的工具类
 * 获取YYYY格式 获取yyyy-MM-dd格式  获取yyyyMMdd格式  获取yyyy-MM-dd HH:mm:ss格式
 * 格式化日期
 * 日期比较，如果s>=e 返回true 否则返回false)
 * 检验日期是否合法
 * 得到两个日期相差的年数
 * 时间相减得到天数
 * 得到n天之后的日期
 * 得到n天后是周几
 * 思路：
 */
public class DateUtil {

    //测试
    public static void main(String[] args) {
        System.out.println(getDay());
        System.out.println(getDays());
        System.out.println(getYear());
        System.out.println(getTime());
        System.out.println(isValidDate("2012-3-15"));
        System.out.println(getDiffYear("2012-3-15","2015-3-24"));
        System.out.println(getAfterDayDate("5"));
        System.out.println(getAfterDayWeek("3"));
    }

    //定义格式
    private final static SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDay=new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays=new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //获取YYYY格式
    public static String getYear(){
        return sdfYear.format(new Date());
    }

    //获取yyyy-MM-dd格式
    public static String getDay(){
        return sdfDay.format(new Date());
    }

    //获取yyyyMMdd格式
    public static String getDays(){
        return sdfDays.format(new Date());
    }

    //获取yyyy-MM-dd HH:mm:ss格式
    public static String getTime(){
        return sdfTime.format(new Date());
    }

    //格式化日期
    public static Date fomatDate(String date){
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s,String e){
        if(fomatDate(s)==null || fomatDate(e)==null){
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    //检验日期是否合法
    public static boolean isValidDate(String s){
        try {
            sdfDay.parse(s);
            return true;
        } catch ( ParseException e ) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到两个日期相差的年数
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    public static int getDiffYear(String sTime,String eTime){
        try {
            long aa=0;
            int years=(int)(((sdfDay.parse(sTime).getTime()-sdfDay.parse(eTime).getTime())/(1000*60*60*24))/365);
            return years;
        } catch ( ParseException e ) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 时间相减得到天数
     * @param sTime
     * @param eTime
     * @return
     */
    public static long getDaySub(String sTime,String eTime){
        long day=0;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime=null;
        Date endTime=null;
        try {
            beginTime=simpleDateFormat.parse(sTime);
            endTime=simpleDateFormat.parse(eTime);
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        day=(endTime.getTime()-beginTime.getTime())/(24*60*60*1000);
        return day;
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days){
        int daysInt=Integer.parseInt(days);

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,daysInt);
        Date date=calendar.getTime();

        String str=sdfTime.format(date);
        return str;
    }

    /**
     * 得到n天后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days){
        int daysInt=Integer.parseInt(days);

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,daysInt);
        Date date=calendar.getTime();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("E");
        String str=simpleDateFormat.format(date);
        return str;
    }

}
