package com.handtours.service.api.domain.core.res.order;

import com.handtours.common.constants.core.OrderDiscountStatus;
import com.handtours.common.constants.core.OrderSettleStatus;
import com.handtours.service.api.domain.core.req.order.OrderEarnestDetail;
import com.handtours.service.api.domain.core.req.order.OrderGroupMember;
import com.handtours.service.api.domain.core.req.order.OrderPriceDetail;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class QueryOrderOne implements Serializable {
    private Integer status;//0:未审核 1:已审核
    private String statusStr;//0:未审核 1:已审核

    private Double totalPrice ;//订单金额
    private String totalPriceStr ;//订单金额
    private Double settlePrice ;//结算金额
    private String settlePriceStr ;//结算金额
    private Double discountPrice ;//返佣金额
    private String discountPriceStr  ;//返佣金额

    private String lastUpdateTime;
    private Long lastUpdateTimeTs;
    private String createTime;

    private Long orderId;

    //input
    private String remark;//备注
    private String contactName;
    private String contactEmail;
    private String contactMobile;

    private String startDateOfWeek;//出团星期
    private String startTimeStr;//出团星期
    private String groupId;//团号
    private String productName;
    private String providerName;//供应商名称
    private String cardUser;//微名片mobile

    private Integer settlementStatus;//结算状态
    private String settlementStatusStr;//结算状态
    private Integer discountStatus;//返佣状态
    private String discountStatusStr;//返佣状态
    private List<OrderGroupMember> groupPersons;
    private List<OrderPriceDetail> priceDetails;
    private OrderEarnestDetail earnest;

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPriceStr() {
        return totalPriceStr;
    }

    public void setTotalPriceStr(String totalPriceStr) {
        this.totalPriceStr = totalPriceStr;
    }

    public Double getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(Double settlePrice) {
        this.settlePrice = settlePrice;
    }

    public String getSettlePriceStr() {
        return settlePriceStr;
    }

    public void setSettlePriceStr(String settlePriceStr) {
        this.settlePriceStr = settlePriceStr;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountPriceStr() {
        return discountPriceStr;
    }

    public void setDiscountPriceStr(String discountPriceStr) {
        this.discountPriceStr = discountPriceStr;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementStatusStr() {
        return settlementStatusStr;
    }

    public void setSettlementStatusStr(String settlementStatusStr) {
        this.settlementStatusStr = settlementStatusStr;
    }

    public Integer getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(Integer discountStatus) {
        this.discountStatus = discountStatus;
    }

    public String getDiscountStatusStr() {
        return discountStatusStr;
    }

    public void setDiscountStatusStr(String discountStatusStr) {
        this.discountStatusStr = discountStatusStr;
    }

    public OrderEarnestDetail getEarnest() {
        return earnest;
    }

    public void setEarnest(OrderEarnestDetail earnest) {
        this.earnest = earnest;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    public Long getLastUpdateTimeTs() {
        return lastUpdateTimeTs;
    }

    public void setLastUpdateTimeTs(Long lastUpdateTimeTs) {
        this.lastUpdateTimeTs = lastUpdateTimeTs;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStartDateOfWeek() {
        return startDateOfWeek;
    }

    public void setStartDateOfWeek(String startDateOfWeek) {
        this.startDateOfWeek = startDateOfWeek;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCardUser() {
        return cardUser;
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

    public void setCardUser(String cardUser) {


        this.cardUser = cardUser;
    }
}
