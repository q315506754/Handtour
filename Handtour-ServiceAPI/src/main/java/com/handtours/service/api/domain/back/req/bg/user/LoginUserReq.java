package com.handtours.service.api.domain.back.req.bg.user;

import com.handtours.service.api.domain.core.req.Req;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class LoginUserReq extends Req {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
