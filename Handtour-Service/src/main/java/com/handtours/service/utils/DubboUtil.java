package com.handtours.service.utils;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/20 0020 10:44
 */
public class DubboUtil {

    public static String uniqueKey(Class<?> anInterface, URL url) {
        String group = url.getParameter(Constants.GROUP_KEY, "NONE");
        return group + "/" + anInterface.getCanonicalName();
    }
}
