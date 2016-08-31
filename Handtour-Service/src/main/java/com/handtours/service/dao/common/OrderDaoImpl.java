package com.handtours.service.dao.common;

import com.handtours.service.model.core.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public class OrderDaoImpl implements  OrderDaoCustom{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  EntityManager entityManager;

    @Override
    public Order save(Order s) {
        logger.debug("before:"+s);
        if (s.getOrderId() != null) {
            entityManager.merge(s);
        } else {
            entityManager.persist(s);

            s.getEarnest().setOrderId(s.getOrderId());
            logger.debug("s:"+s.getEarnest());
            entityManager.persist(s.getEarnest());
            logger.debug("saved:"+s);

            s.setOdId(String.valueOf(s.getOrderId()));
        }
        return s;
    }

    @Override
    public void refresh(Order s) {
        s.setStartTime(System.currentTimeMillis());
        s.setStartDateOfWeek("四");
        s.setGroupId("SHCYTS2016092214470");
        s.setProductName("波斯秘境◆伊朗一地10天7晚全景之旅");
        s.setProviderName("上海中青旅行社有限公司 ");
        s.setTotalPrice(1234d);
        s.setSettlePrice(1234d);
        s.setDiscountPrice(333d);
        s.setCardUser("13761156786");
    }

}
