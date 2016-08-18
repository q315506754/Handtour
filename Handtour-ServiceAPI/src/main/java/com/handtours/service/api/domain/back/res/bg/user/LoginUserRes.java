package com.handtours.service.api.domain.back.res.bg.user;

import com.handtours.service.api.domain.core.res.Res;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class LoginUserRes extends Res {
    private String mobile;
    private String name;
    private String email;
    private Boolean isEnable;
    private String lastUpdateTime;
    private Long lastUpdateTimeTs;
    private String createTime;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getLastUpdateTimeTs() {
        return lastUpdateTimeTs;
    }

    public void setLastUpdateTimeTs(Long lastUpdateTimeTs) {
        this.lastUpdateTimeTs = lastUpdateTimeTs;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
