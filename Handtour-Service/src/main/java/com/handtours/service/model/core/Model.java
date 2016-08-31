package com.handtours.service.model.core;

import com.handtours.common.utils.ClassUtil;
import com.handtours.service.model.back.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
}
