package com.handtours.service.model.core;

import com.handtours.common.constants.core.OrderDiscountStatus;
import com.handtours.common.constants.core.OrderSettleStatus;
import com.handtours.common.constants.core.OrderStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 14:19
 */
@Entity(name = "Order")
@Table(name = "c_order")
@EntityListeners({AuditingEntityListener.class})
public class Order extends Model {
    @Id
    @GeneratedValue
    private Long orderId;
    private String odId;
    private Long shareId;//分享id

    //snap field
    private Long productId;//产品id from shareId
    private Long priceId;//价格id from shareId
    private Long providerId;//供应商id from shareId
    private Long startTime;//出团时间
    private String startDateOfWeek;//出团星期
    private String groupId;//团号
    private String productName;
    private String providerName;//供应商名称
    private String cardUser;//微名片mobile

    private Double totalPrice = 0d;//订单金额
    private Double settlePrice = 0d;//结算金额
    private Double discountPrice = 0d;//返佣金额


    //    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="earnestId")
//    @PrimaryKeyJoinColumn
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name="earnestId")
    private Earnest earnest;//定金

    //flag
    private Integer settlementStatus = OrderSettleStatus.NOT_YET.getStatus();//结算状态
    private Integer discountStatus = OrderDiscountStatus.NOT_YET.getStatus();//返佣状态

    //input
    private String remark;//备注
    private String contactName;
    private String contactEmail;
    private String contactMobile;

    public String getOdId() {
        return odId;
    }

    public void setOdId(String odId) {
        this.odId = odId;
    }

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private List<GroupPerson> groupPersons;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "orderId")
    private List<PriceDetail> priceDetails;

    //generate
    private Integer status = OrderStatus.NOT_HANDLED.getStatus();

    @LastModifiedDate
    private Long lastUpdateTime;
    @CreatedDate
    private Long createTime;


    public List<PriceDetail> getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(List<PriceDetail> priceDetails) {
        this.priceDetails = priceDetails;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Earnest getEarnest() {
        return earnest;
    }

    public void setEarnest(Earnest earnest) {
        this.earnest = earnest;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShareId() {
        return shareId;
    }

    public List<GroupPerson> getGroupPersons() {
        return groupPersons;
    }

    public void setGroupPersons(List<GroupPerson> groupPersons) {
        this.groupPersons = groupPersons;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(Double settlePrice) {
        this.settlePrice = settlePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
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

    public String getCardUser() {
        return cardUser;
    }

    public void setCardUser(String cardUser) {
        this.cardUser = cardUser;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
