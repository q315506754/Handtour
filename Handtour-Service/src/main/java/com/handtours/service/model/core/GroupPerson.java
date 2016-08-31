package com.handtours.service.model.core;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 14:19
 */
@Entity(name = "GroupPerson")
@Table(name = "c_groupPerson")
@EntityListeners({AuditingEntityListener.class})
public class GroupPerson extends Model {
    @Id
    @GeneratedValue
    private Long id;

    private String orderId;
    private Integer type;
    private String name;
    private String mobile;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }
}
