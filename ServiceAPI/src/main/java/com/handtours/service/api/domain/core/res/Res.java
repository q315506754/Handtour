package com.handtours.service.api.domain.core.res;

import com.handtours.common.utils.ClassUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:51
 */
public class Res implements Serializable {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer code=0;
    private String msg="ok";

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
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
