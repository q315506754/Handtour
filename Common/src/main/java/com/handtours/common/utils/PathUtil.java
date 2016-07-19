package com.handtours.common.utils;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/18 0018 16:29
 */
public class PathUtil {
    public static String buildCls(Object obj, String... pathes) {
        return buildCls(obj.getClass(), pathes);
    }

    public static String buildCls(Class cls, String... pathes) {
        String base = cls.getCanonicalName();
        for (String path : pathes) {
            base = base + "." + path;
        }
        return base;
    }

}
