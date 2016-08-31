package com.handtours.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/18 0018 16:55
 */
public class Copier<T> {
    private Class<T> destCls;
    private T dest;
    private List<CopierMapper> mappers = new ArrayList<>();
    public static CopierFieldConvertor sameNameConvertor = new SameTypeConvertor();

    static class SameTypeConvertor<T> implements CopierFieldConvertor<T,T> {
        @Override
        public Object convert(Object src) {
            return src;
        }
    }

    private Copier() {
    }

    static class CopierMapper {
       final String fieldFrom;
        final String fieldTo;
        final  CopierFieldConvertor convertor;

        public CopierMapper(String fieldFrom, String fieldTo, CopierFieldConvertor convertor) {
            this.fieldFrom = fieldFrom;
            this.fieldTo = fieldTo;
            this.convertor = convertor;
        }
    }


    public Copier<T> map(String fieldFrom, String fieldTo) {
        return map(fieldFrom, fieldTo, sameNameConvertor);
    }

    public Copier<T> map(String fieldFrom, String fieldTo, CopierFieldConvertor convertor) {
        mappers.add(new CopierMapper(fieldFrom, fieldTo, convertor));
        return this;
    }

    public Copier<T> compose(Copier prototype) {
        List<CopierMapper> mappers = prototype.mappers;
        mappers.parallelStream().forEach((mapper)->this.mappers.add(mapper));
        return this;
    }

    public <K,V>Copier<T> mapG(String fieldFrom, String fieldTo, CopierFieldConvertor <K,V> convertor) {
        mappers.add(new CopierMapper(fieldFrom, fieldTo, convertor));
        return this;
    }

    public static <T> Copier<T> to(Class<T> cls) {
        Copier ret = new Copier();
        try {
            ret.destCls = cls;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Copier<T> to(Object ins) {
        Copier ret = new Copier();
        try {
            ret.dest = ins;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T from(Object src) {
        return from(src,true);
    }
    public T from(Object src,boolean copyNullProps) {
        try {
            T ret = null;
            if (dest == null) {
                ret = destCls.newInstance();
            }else {
                ret = dest;
            }

            try {
                Map describe = BeanUtils.describe(src);
                if (!copyNullProps) {
                    Map operMap = new HashMap(describe);
                    Set<Map.Entry> set = describe.entrySet();
                    set.parallelStream().forEach((entry)->{
                        Object value = entry.getValue();
                        if (StringUtils.isEmpty(value)) {
                            operMap.remove(entry.getKey());
                        }
                    });

                    describe = operMap;
                }

                for (CopierMapper mapper : mappers) {
                    if (mapper.fieldFrom.equals(mapper.fieldTo)) {
                        describe.remove(mapper.fieldFrom);
                    }
                }

                BeanUtils.populate(ret, describe);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (CopierMapper mapper : mappers) {
                try {
                    Object srcVal = MethodUtil.invokeGetter(src, mapper.fieldFrom);

                    if (!copyNullProps && (srcVal == null || StringUtils.isEmpty(srcVal))) {
                        continue;
                    }

                    Object convertedVal = mapper.convertor.convert(srcVal);
                    MethodUtil.invokeSetter(ret, mapper.fieldTo, convertedVal);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
