package com.handtours.service.api.domain.back.req;

import com.handtours.service.api.domain.core.req.Req;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class LoginReq extends Req {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
