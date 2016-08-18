package com.handtours.common.utils;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/18 0018 16:03
 */
public class FileUtil {
    public static String getPrefix(String fileName){
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}
