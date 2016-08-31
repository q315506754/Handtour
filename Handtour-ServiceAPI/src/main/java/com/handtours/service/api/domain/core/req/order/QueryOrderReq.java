package com.handtours.service.api.domain.core.req.order;

import com.handtours.service.api.domain.core.req.PageReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class QueryOrderReq extends PageReq {
    private Long orderId;
    private Long startTimeStart;
    private Long startTimeEnd;
    private Long createTimeStart;
    private Long createTimeEnd;
    private String groupId;
    private String startDateOfWeek;
    private String contactName;
    private String contactEmail;
    private String contactMobile;
    private String cardUser;
    private Integer settlementStatus;
    private Integer discountStatus;
    private Integer status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getStartDateOfWeek() {
        return startDateOfWeek;
    }

    public void setStartDateOfWeek(String startDateOfWeek) {
        this.startDateOfWeek = startDateOfWeek;
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

    public String getCardUser() {
        return cardUser;
    }

    public void setCardUser(String cardUser) {
        this.cardUser = cardUser;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Integer getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(Integer discountStatus) {
        this.discountStatus = discountStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(Long startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public Long getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(Long startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

    public Long getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Long createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Long getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public void setStatus(Integer status) {


        this.status = status;
    }
}
