package com.handtours.service.api.domain.back.res.bg.user;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class QueryUserOne implements Serializable {
    private String mobile;
    private String name;
    private String email;
    private Boolean isEnable;
    private String lastUpdateTime;
    private Long lastUpdateTimeTs;
    private String createTime;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getLastUpdateTimeTs() {
        return lastUpdateTimeTs;
    }

    public void setLastUpdateTimeTs(Long lastUpdateTimeTs) {
        this.lastUpdateTimeTs = lastUpdateTimeTs;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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
}
