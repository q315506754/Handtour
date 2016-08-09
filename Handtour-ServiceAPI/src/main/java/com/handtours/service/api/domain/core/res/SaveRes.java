package com.handtours.service.api.domain.core.res;

import com.handtours.common.utils.ClassUtil;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:51
 */
public class SaveRes<T> extends Res {
    private T generatedId ;

    public T getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(T generatedId) {
        this.generatedId = generatedId;
    }
}
