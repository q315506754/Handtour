package com.handtours.service.api.domain.back.req;

import com.handtours.service.api.domain.core.req.PageReq;
import com.handtours.service.api.domain.core.req.UpdateReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class UpdateUserReq extends UpdateReq<String> {
    private String name;
    private String email;
    private Boolean isEnable;
    private String password;
    private String secondPassword;

    public UpdateUserReq(String s) {
        super(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }
}
