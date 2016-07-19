package com.handtours.service.api.domain.core.res;

import com.handtours.common.utils.ClassUtil;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:51
 */
public class Res implements Serializable {
    private Integer code = 0;
    private String msg = "ok";

    public void set(Integer code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ClassUtil.getToString(this);
    }
}
