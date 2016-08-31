package com.handtours.service.api.domain.core.req.order;

import com.handtours.service.api.domain.core.req.Req;
import com.handtours.service.api.domain.core.req.SaveReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class OrderGroupMember extends Req {
    private String name;
    private String mobile;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
