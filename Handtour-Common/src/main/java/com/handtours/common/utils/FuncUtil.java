package com.handtours.common.utils;

import com.handtours.common.constants.core.StatusEnum;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    public static CopierFieldConvertor<Double, String> priceConvertor = PriceUtil::format;
    public static Copier auditDate2StringCopier = Copier.to(Object.class).map("createTime", "createTime", date2StringConvertor).map("lastUpdateTime", "lastUpdateTime", date2StringConvertor).map("lastUpdateTime", "lastUpdateTimeTs", date2LongConvertor);

    public static  <T extends Enum> CopierFieldConvertor<Integer, String> createStatusConvertor(Class<T> cls){
        return new FuncUtil.StatusConvertor(cls);
    }

    public static class StatusConvertor<T extends Enum> implements CopierFieldConvertor<Integer, String>{
        private final Class<T> cls;
        private final Map<Integer,String> mapper = new HashMap<>();

        public StatusConvertor(Class<T> cls) {
            this.cls = cls;

            initMap();
        }

        private void initMap() {
            try {
                Method values = this.cls.getDeclaredMethod("values");
                Object invoke = values.invoke(this.cls);
                int length = Array.getLength(invoke);
                for (int i = 0; i < length; i++) {
                    Object o = Array.get(invoke, i);
                    if (o instanceof StatusEnum) {
                        StatusEnum se = (StatusEnum) o ;
                        mapper.put(se.getStatus(),se.getName());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Map<Integer, String> getMapper() {
            return mapper;
        }

        @Override
        public String convert(Integer src) {
            return mapper.get(src);
        }
    }

}
