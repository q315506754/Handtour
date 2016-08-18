package com.handtours.service.api.domain.back.req.bg.user;

import com.handtours.service.api.domain.core.req.PageReq;
import com.handtours.service.api.domain.core.req.Req;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class QueryUserReq extends PageReq {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
