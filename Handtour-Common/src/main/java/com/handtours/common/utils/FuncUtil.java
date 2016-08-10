package com.handtours.common.utils;

import java.util.Date;
import java.util.function.Function;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 17:58
 */
public class FuncUtil {
    public static Function<Date, String> date2String_YMD_HMS = ((Function<Date,Long>)Date::getTime).andThen(DateUtil::getDate_YYYY_MM_DD);
    public static CopierFieldConvertor<Date, String> date2StringConvertor = date2String_YMD_HMS::apply;
    public static CopierFieldConvertor<Date, Long> date2LongConvertor = Date::getTime;
    public static Copier auditDate2StringCopier = Copier.to(Object.class).map("createTime", "createTime", date2StringConvertor).map("lastUpdateTime", "lastUpdateTime", date2StringConvertor).map("lastUpdateTime", "lastUpdateTimeTs", date2LongConvertor);

}
