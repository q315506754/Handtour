package com.handtours.common.utils;

import net.sf.json.JSONObject;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 11:10
 */
public class ClassUtil {

    public static String getToString(Object model) {
        JSONObject jsonObject = JSONObject.fromObject(model);
        jsonObject.remove("logger");
        return model.getClass() + "_" + jsonObject.toString();
    }
}
