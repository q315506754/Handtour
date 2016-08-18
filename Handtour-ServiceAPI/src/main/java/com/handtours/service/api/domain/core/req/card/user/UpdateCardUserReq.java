package com.handtours.service.api.domain.core.req.card.user;

import com.handtours.service.api.domain.core.req.UpdateReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class UpdateCardUserReq extends UpdateReq<String> {
    private String name;
    private String email;
    private Boolean isEnable;

    private Integer status;//0:未审核 1:已审核
    private Double balance;
    private String password;
    private String secondPassword;

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

    public UpdateCardUserReq() {
    }

    public UpdateCardUserReq(String s) {
        super(s);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
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

    public void setBalance(Double balance) {

        this.balance = balance;
    }
}
