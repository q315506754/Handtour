package com.handtours.service.jpa.model.back;

import com.handtours.service.model.core.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:38
 */
@Entity
@Table(name = "back_user")
public class UserInfo extends Model{
    @Id
    private String mobile;

    @Column(name = "name")
    private String name;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
