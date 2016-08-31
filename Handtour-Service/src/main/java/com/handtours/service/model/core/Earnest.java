package com.handtours.service.model.core;

import org.hibernate.annotations.GenericGenerator;
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
@Entity(name = "Earnest")
@Table(name = "c_earnest")
@EntityListeners({AuditingEntityListener.class})
public class Earnest extends Model {
    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "pkGenerator" )
//    @GenericGenerator(
//            name = "pkGenerator" ,
//            strategy = "foreign" ,
//            parameters = @org.hibernate.annotations.Parameter (name = "property" , value = "order" )
//    )
    private Long id;

    private Double price;
    private Integer status;
    private Long orderId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
