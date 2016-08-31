package com.handtours.service.model.core;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 14:19
 */
@Entity(name = "PriceDetail")
@Table(name = "c_priceDetail")
@EntityListeners({AuditingEntityListener.class})
public class PriceDetail extends Model {
    @Id
    @GeneratedValue
    private Long id;

    private String orderId;
    private Double price;
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
