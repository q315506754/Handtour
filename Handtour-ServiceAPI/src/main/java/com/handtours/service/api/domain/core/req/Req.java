package com.handtours.service.api.domain.core.req;

import com.handtours.common.utils.ClassUtil;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:51
 */
public class Req implements Serializable {

    @Override
    public String toString() {
        return ClassUtil.getToString(this);
    }
}
