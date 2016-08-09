package com.handtours.service.model.core;

import com.handtours.common.utils.ClassUtil;
import com.handtours.service.model.back.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.util.Date;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 11:08
 */

public class Model {

    @LastModifiedDate
    private Date lastUpdateTime;
    @CreatedDate
    private Date createTime;

    @CreatedBy
    private User createdUser;
    @LastModifiedBy
    private User lastModifiedUser;

    @Override
    public String toString() {
        return ClassUtil.getToString(this);
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
