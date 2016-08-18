package com.handtours.service.api.domain.core.res.card.user;

import java.io.Serializable;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class QueryCardUserOne implements Serializable {
    private String mobile;
    private String name;
    private String email;

    private Integer status;//0:未审核 1:已审核
    private String statusStr;//0:未审核 1:已审核
    private Double balance;
    private String balanceStr;

    private Boolean isEnable;
    private String lastUpdateTime;
    private Long lastUpdateTimeTs;
    private String createTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBalanceStr() {
        return balanceStr;
    }

    public void setBalanceStr(String balanceStr) {
        this.balanceStr = balanceStr;
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
