package com.handtours.service.impltest.project.core;

import com.handtours.common.constants.core.OrderDiscountStatus;
import com.handtours.common.constants.core.OrderSettleStatus;
import com.handtours.common.constants.core.OrderStatus;
import com.handtours.service.api.domain.core.req.order.*;
import com.handtours.service.api.domain.core.res.order.QueryOrderRes;
import com.handtours.service.api.domain.core.res.order.SaveOrderRes;
import com.handtours.service.api.domain.core.res.order.UpdateOrderRes;
import com.handtours.service.api.project.core.IOrder;
import com.handtours.service.commontest.BaseTest;
import com.handtours.service.dao.common.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml", "classpath*:application-service/*.xml"})
public class OrderTest extends BaseTest {
    @Autowired
    @Qualifier("bean_order")
    private IOrder iOrder;
    @Autowired
    private OrderDao orderDao;

    @Test
    public void func_update() {
        UpdateOrderReq params = new UpdateOrderReq();
        params.setId(1L);
        params.setSettlementStatus(OrderSettleStatus.ALREADY.getStatus());
        params.setDiscountStatus(OrderDiscountStatus.ALREADY.getStatus());
        params.setStatus(OrderStatus.AL_HANDLED.getStatus());
        UpdateOrderRes update = iOrder.update(params);
        System.out.println(update);
    }

    @Test
    public void func_query() {
        QueryOrderReq params = new QueryOrderReq();
        params.setContactName("联系人");
        params.setOrderId(4L);
        QueryOrderRes query = iOrder.query(params);
        System.out.println(query);
    }

    @Test
    public void func_query_time() {
        Calendar instance = Calendar.getInstance();
        long cur = instance.getTimeInMillis();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.MILLISECOND, 000);
        long today = instance.getTimeInMillis();

        QueryOrderReq params = new QueryOrderReq();
        params.setCreateTimeStart(today);
        params.setCreateTimeEnd(cur);
        QueryOrderRes query = iOrder.query(params);
        System.out.println(query);
    }

    @Test
    public void func_query_all() {
        Calendar instance = Calendar.getInstance();

        QueryOrderReq params = new QueryOrderReq();
        QueryOrderRes query = iOrder.query(params);
        System.out.println(query);
    }

    @Test
    @Repeat(39)
    public void func_save() {
        SaveOrderReq params = new SaveOrderReq();
        params.setShareId(333L);
        params.setTotalPrice(888d);
        params.setEarnest(100d);
        params.setRemark("你好  备注"+rnd());
        params.setContactName("联系人"+rnd());
        params.setContactEmail("联系人邮箱"+rnd());
        params.setContactMobile("联系人手机号"+rnd());

        LinkedList<OrderGroupMember> groupPersons = new LinkedList<>();

        groupPersons.add(genOrderGroupMember());
        groupPersons.add(genOrderGroupMember());
        groupPersons.add(genOrderGroupMember());


        params.setGroupPersons(groupPersons);

        LinkedList<OrderPriceDetail> priceDetails = new LinkedList<>();

        priceDetails.add(genOrderPriceDetail());
        priceDetails.add(genOrderPriceDetail());
        priceDetails.add(genOrderPriceDetail());
        
        params.setPriceDetails(priceDetails);

        SaveOrderRes save = iOrder.save(params);
        System.out.println(save);

    }

    private OrderPriceDetail genOrderPriceDetail() {
        OrderPriceDetail one = new OrderPriceDetail();
        one.setType(0);
        one.setPrice((double) rnd());
        return one;
    }

    private OrderGroupMember genOrderGroupMember() {
        OrderGroupMember one = new OrderGroupMember();
        one.setName("入住人"+rnd());
        one.setMobile("入住人手机号"+rnd());
        one.setType(0);
        return one;
    }
}
