package com.handtours.service.model.back;

import com.handtours.service.model.core.Model;

import javax.persistence.*;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:38
 */
@Entity(name="back.Address")
@Table(name = "back_address")
public class Address extends Model{
    @Id @GeneratedValue
    private Long addid;
    private String street, state, country;

    public Long getAddid() {
        return addid;
    }

    public void setAddid(Long addid) {
        this.addid = addid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
