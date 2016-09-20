package com.fh.util;


import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.util.*;

/**
 * DateTime: 2016/9/20 16:51
 * 功能：排序类
 * 思路：
 */
public class SortUtil {
    //测试
    public static void main(String[] args) {

       ArrayList list=new ArrayList();
        list.add(123);
        list.add(789);
        list.add(456);
        list.add(101);

        System.out.println(sort(list,null,"asc"));
        System.out.println(sort(list,null,"desc"));

        //System.out.println(sortParam2(list,null,null,"asc"));
        //System.out.println(sortParam2(list,null,null,"desc"));

    }

    /**
     * 对list进行排序
     * @param sortList  需要排序的list
     * @param param 排序的参数名称
     * @param orderType 排序类型，正序 asc 逆序 desc
     * @return
     */
    public static List sort(List sortList,String param,String orderType){
        Comparator myCom1= ComparableComparator.getInstance();
        if("desc".equals(orderType)){
            myCom1= ComparatorUtils.reversedComparator(myCom1); //逆序 默认正序
        }

        ArrayList<Object> sortFields=new ArrayList<Object>();
        sortFields.add(new BeanComparator(param,myCom1));

        ComparatorChain multiSort=new ComparatorChain(sortFields);
        Collections.sort(sortList,multiSort);

        return sortList;

    }

    /**
     * 对list进行排序
     * @param sortList  需要排序的list
     * @param param1    排序的参数名称:参数长度
     * @param param2    排序的参数名称:排序参数
     * @param orderType 排序类型：正序-asc；倒序-desc
     * @return
     */
    public static List sortParam2(List sortList,String param1,String param2,String orderType){
        Comparator myComp1=ComparableComparator.getInstance();
        Comparator myComp2=ComparableComparator.getInstance();

        if("desc".equals(orderType)){
            myComp1=ComparatorUtils.reversedComparator(myComp1);     //逆序（默认为正序）
        }

        ArrayList<Object> sortFields=new ArrayList<Object>();
        sortFields.add(new BeanComparator(param1,myComp1)); //主排序 第一排序
        sortFields.add(new BeanComparator(param2,myComp2)); //主排序 第一排序

        ComparatorChain multiSort=new ComparatorChain(sortList);
        Collections.sort(sortList,multiSort);

        return sortList;

    }


}
