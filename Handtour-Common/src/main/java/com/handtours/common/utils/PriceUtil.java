package com.handtours.common.utils;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:28
 */
public class PriceUtil {
    public static String format(double price){
        return format(price,0);
    }
    public static String format00(double price){
        return format(price,2);
    }
    public static String format(double price,int size){
        return new BigDecimal(price).setScale(size ,BigDecimal.ROUND_DOWN).toString();
    }
}
