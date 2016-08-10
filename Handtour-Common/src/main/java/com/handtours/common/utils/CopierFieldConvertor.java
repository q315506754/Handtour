package com.handtours.common.utils;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/18 0018 17:04
 */
public interface CopierFieldConvertor<T,S> {
    S convert(T src);

}