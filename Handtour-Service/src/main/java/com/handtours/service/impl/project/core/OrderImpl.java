package com.handtours.service.impl.project.core;

import com.handtours.common.constants.core.EarnestStatus;
import com.handtours.common.constants.core.OrderDiscountStatus;
import com.handtours.common.constants.core.OrderSettleStatus;
import com.handtours.common.constants.core.OrderStatus;
import com.handtours.common.utils.Copier;
import com.handtours.common.utils.FuncUtil;
import com.handtours.service.api.domain.core.req.order.*;
import com.handtours.service.api.domain.core.res.order.*;
import com.handtours.service.api.project.core.IOrder;
import com.handtours.service.com.R;
import com.handtours.service.dao.common.OrderDao;
import com.handtours.service.model.core.*;
import com.handtours.service.model.core.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class OrderImpl extends ImplSupport<Order, Long, SaveOrderReq, SaveOrderRes, QueryOrderReq, QueryOrderRes, QueryOrderOne, UpdateOrderReq, UpdateOrderRes, DeleteOrderReq, DeleteOrderRes> implements IOrder {
    @Autowired
    private OrderDao userDao;
    private static Copier<Order> saveCopier;
    private QueryInvoker<Order, QueryOrderReq> queryInvoker;
    private CopierBuilder<QueryOrderOne> queryCopierBuilder;

    @Override
    public JpaRepository<Order, Long> getDao() {
        return userDao;
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public SaveOrderRes save(SaveOrderReq params) {
        logger.debug("params:"+params);
        SaveOrderRes ret = new SaveOrderRes();

        if (saveCopier == null) {
            createSaveCopier();
        }

        Order from = saveCopier.from(params);
        logger.debug("parsed:"+from);
        userDao.refresh(from);
        logger.debug("refreshed:"+from);

        userDao.save(from);
        ret.setGeneratedId(from.getOrderId());
        return ret;
    }

    private void createSaveCopier() {
        saveCopier = Copier.to(getEntityClass());
        saveCopier.<Double,Earnest>mapG("earnest","earnest",(d)->{
            Earnest o = new Earnest();
            o.setPrice(d);
            o.setStatus(EarnestStatus.NOT_YET.getStatus());
            return o;
        });

        addListMapper(saveCopier, "groupPersons",OrderGroupMember.class,GroupPerson.class);
        addListMapper(saveCopier, "priceDetails",OrderPriceDetail.class, PriceDetail.class);
    }

    private <F,T> void addListMapper(Copier<Order> copier, String field,Class<F> fcls,Class<T> tcls) {
        Copier<T> listCopier = Copier.to(tcls);
        copier.<List<F>,List<T>>mapG(field,field,(list)->castList(list,listCopier));
    }

    private <F,T> List<T> castList(List<F> list,Copier<T> listCopier) {
        List<T> l = new ArrayList<>(list.size());
        if (list != null) {
            for (F groupPerson : list) {
                l.add(listCopier.from(groupPerson));
            }
        }
        return l;
    }

    @Override
    public QueryOrderRes query(QueryOrderReq params) {
        //                            Join join =root.join(root.getModel().getSingularAttribute("ldUser", SystemUser.class), JoinType.LEFT);
        if (queryInvoker == null) {
            queryInvoker = (req, page) -> userDao.findAll(new Specification<Order>() {
                @Override
                public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                    List<Predicate> pList = new ArrayList<>(16);
//                            Join join =root.join(root.getModel().getSingularAttribute("ldUser", SystemUser.class), JoinType.LEFT);
                    Long orderId = req.getOrderId();
                    if (!StringUtils.isEmpty(orderId)) {
                        if (orderId > 1000_0000) {
                            addNumberEqualCriteria(root, cb, pList, "orderId", orderId);
                        } else {
                            addStringRegexCriteria(root, cb, pList, "odId", String.valueOf(orderId));
                        }
                    }

//                            Predicate orderId = cb.equal(root.get("orderId"), req.getOrderId());
//                            Predicate orderId2 = cb.ge(root.get("orderId"), req.getOrderId());
//                            Predicate contactName = cb.like(root.get("contactName"), "%"+req.getContactName().trim()+"%");
//                            Predicate or = cb.or(contactName,orderId );
//                            Predicate or = cb.or(orderId2,orderId );

                    addLongGteCriteria(root, cb, pList, "startTime", req.getStartTimeStart());
                    addLongLteCriteria(root, cb, pList, "startTime", req.getStartTimeEnd());
                    addLongGteCriteria(root, cb, pList, "createTime", req.getCreateTimeStart());
                    addLongLteCriteria(root, cb, pList, "createTime", req.getCreateTimeEnd());

                    addNumberEqualCriteria(root, cb, pList, "settlementStatus", req.getSettlementStatus());
                    addNumberEqualCriteria(root, cb, pList, "discountStatus", req.getDiscountStatus());
                    addNumberEqualCriteria(root, cb, pList, "status", req.getStatus());

                    addStringRegexCriteria(root, cb, pList, "groupId", req.getGroupId());
                    addStringRegexCriteria(root, cb, pList, "startDateOfWeek", req.getStartDateOfWeek());
                    addStringRegexCriteria(root, cb, pList, "contactName", req.getContactName());
                    addStringRegexCriteria(root, cb, pList, "contactEmail", req.getContactEmail());
                    addStringRegexCriteria(root, cb, pList, "contactMobile", req.getContactMobile());
                    addStringRegexCriteria(root, cb, pList, "cardUser", req.getCardUser());

                    criteriaQuery.where(pList.toArray(new Predicate[pList.size()]));
//                            criteriaQuery.where(or);
                    return null;
                }
            }, page);
        }

        if (queryCopierBuilder == null) {
            Copier<OrderEarnestDetail> earnestDetailCopier = Copier.to(OrderEarnestDetail.class).
            map("price","priceStr", FuncUtil.priceConvertor).
            map("status","statusStr",FuncUtil.createStatusConvertor(EarnestStatus.class));

            Copier<OrderGroupMember>  orderGroupMemberCopier = Copier.to(OrderGroupMember.class);
            Copier<OrderPriceDetail>  orderPriceDetailCopier = Copier.to(OrderPriceDetail.class).
                    map("price","priceStr", FuncUtil.priceConvertor);

            queryCopierBuilder = () -> Copier.to(QueryOrderOne.class).
                    compose(FuncUtil.auditLong2StringCopier).
                    map("status","statusStr", FuncUtil.createStatusConvertor(OrderStatus.class)).
                    map("settlementStatus","settlementStatusStr", FuncUtil.createStatusConvertor(OrderSettleStatus.class)).
                    map("discountStatus","discountStatusStr", FuncUtil.createStatusConvertor(OrderDiscountStatus.class)).
                    map("totalPrice","totalPriceStr", FuncUtil.priceConvertor).
                    map("settlePrice","settlePriceStr", FuncUtil.priceConvertor).
                    map("discountPrice","discountPriceStr", FuncUtil.priceConvertor).
                    map("startTime","startTimeStr", FuncUtil.long2StringConvertor).
                    <Earnest,OrderEarnestDetail>mapG("earnest","earnest",(db)->earnestDetailCopier.from(db)).
                    <List<GroupPerson>,List<OrderGroupMember>>mapG("groupPersons","groupPersons",(db)->castList(db,orderGroupMemberCopier)).
                    <List<PriceDetail>,List<OrderPriceDetail>>mapG("priceDetails","priceDetails",(db)->castList(db,orderPriceDetailCopier))
                    ;

        }

        QueryOrderRes queryRs = super.query(params, QueryOrderRes.class,
                new SortRequestBuilder<>(params, new Sort(Sort.Direction.DESC, "createTime")),
                queryInvoker, queryCopierBuilder);

        return queryRs;
    }

    private void addNumberEqualCriteria(Root<Order> root, CriteriaBuilder cb, List<Predicate> pList, String field, Number val) {
        if(!StringUtils.isEmpty(val)){
            pList.add(cb.equal(root.get(field), val));
//            pList.add(cb.or(cb.equal(root.get(field), val)));
        }
    }
    private void addLongLteCriteria(Root<Order> root, CriteriaBuilder cb, List<Predicate> pList, String field, Long val) {
        if(!StringUtils.isEmpty(val)){
            pList.add(cb.lessThanOrEqualTo(root.get(field), val));
        }
    }
    private void addLongGteCriteria(Root<Order> root, CriteriaBuilder cb, List<Predicate> pList, String field, Long val) {
        if(!StringUtils.isEmpty(val)){
            pList.add(cb.greaterThanOrEqualTo(root.get(field), val));
        }
    }

    private void addStringRegexCriteria(Root<Order> root, CriteriaBuilder cb, List<Predicate> pList, String field, String val) {
        if(!StringUtils.isEmpty(val)){
            pList.add(cb.like(root.get(field), "%"+val.trim()+"%"));
        }
    }

    @Override
    protected Object[] generateRecordTitle() {
        return new Object[]{"订单"};
    }

    @Override
    public UpdateOrderRes update(UpdateOrderReq params) {
        Class<UpdateOrderRes> cls = UpdateOrderRes.class;

        UpdateOrderRes update = super.update(params, cls, null);
        return update;
    }

    @Override
    public DeleteOrderRes delete(DeleteOrderReq params) {
        return null;
    }
}
