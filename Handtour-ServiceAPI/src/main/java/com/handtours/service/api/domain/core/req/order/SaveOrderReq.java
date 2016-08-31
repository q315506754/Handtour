package com.handtours.service.api.domain.core.req.order;

import com.handtours.service.api.domain.core.req.SaveReq;

import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class SaveOrderReq extends SaveReq {
    private Long shareId;//分享id
    private Double totalPrice;//订单总金额

    private String contactName;
    private String contactEmail;
    private String contactMobile;
    private String remark;//备注

    private List<OrderGroupMember> groupPersons;
    private List<OrderPriceDetail> priceDetails;
    private Double earnest;//定金

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getEarnest() {
        return earnest;
    }

    public void setEarnest(Double earnest) {
        this.earnest = earnest;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public List<OrderGroupMember> getGroupPersons() {
        return groupPersons;
    }

    public void setGroupPersons(List<OrderGroupMember> groupPersons) {
        this.groupPersons = groupPersons;
    }

    public List<OrderPriceDetail> getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(List<OrderPriceDetail> priceDetails) {
        this.priceDetails = priceDetails;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
