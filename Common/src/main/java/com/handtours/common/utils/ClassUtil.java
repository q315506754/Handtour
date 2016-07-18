package com.handtours.common.utils;

import net.sf.json.JSONObject;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 11:10
 */
public class ClassUtil {

    public static String getToString(Object model) {
        return model.getClass()+"_"+ JSONObject.fromObject(model).toString();
    }
}
