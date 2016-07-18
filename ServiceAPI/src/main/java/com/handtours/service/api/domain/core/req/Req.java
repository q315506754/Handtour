package com.handtours.service.api.domain.core.req;

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
public class Req implements Serializable {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String toString() {
        return ClassUtil.getToString(this);
    }
}
