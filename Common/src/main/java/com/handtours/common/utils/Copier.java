package com.handtours.common.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/18 0018 16:55
 */
public class Copier<T> {
    private Class<T>  destCls ;
    private ArrayList<CopierMapper> mappers=new ArrayList<>();
    public static CopierFieldConvertor sameNameConvertor = new SameTypeConvertor();

    static class SameTypeConvertor implements CopierFieldConvertor {
        @Override
        public Object convert(Object src) {
            return src;
        }
    }

    private Copier() {
    }

    class CopierMapper{
        String fieldFrom;
        String fieldTo;
        CopierFieldConvertor convertor;

        public CopierMapper(String fieldFrom, String fieldTo, CopierFieldConvertor convertor) {
            this.fieldFrom = fieldFrom;
            this.fieldTo = fieldTo;
            this.convertor = convertor;
        }
    }


    public Copier<T> map( String fieldFrom, String fieldTo){
        return map(fieldFrom,fieldTo,sameNameConvertor);
    }

    public Copier<T> map( String fieldFrom, String fieldTo,CopierFieldConvertor convertor){
        mappers.add(new CopierMapper(fieldFrom, fieldTo, convertor));
        return this;
    }

    public static<T>  Copier<T> to(Class<T> cls){
        Copier ret = new Copier();
        try {
            ret.destCls = cls;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  T from(Object src){
        try {
            T  dest = destCls.newInstance();

            try {
                Map describe = BeanUtils.describe(src);
                BeanUtils.populate(dest,describe);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (CopierMapper mapper : mappers) {
                try {
                    Object srcVal = MethodUtil.invokeGetter(src, mapper.fieldFrom);
                    Object convertedVal = mapper.convertor.convert(srcVal);
                    MethodUtil.invokeSetter(dest,mapper.fieldTo,convertedVal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
