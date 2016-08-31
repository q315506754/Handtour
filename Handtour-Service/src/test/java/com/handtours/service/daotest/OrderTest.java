package com.handtours.service.daotest;

import com.handtours.service.dao.common.GroupPersonDao;
import com.handtours.service.dao.common.OrderDao;
import com.handtours.service.dao.weixin.WeixinDao;
import com.handtours.service.model.core.CardUser;
import com.handtours.service.model.core.Earnest;
import com.handtours.service.model.core.GroupPerson;
import com.handtours.service.model.core.Order;
import com.handtours.service.model.weixin.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
@TransactionConfiguration(defaultRollback = false)
public class OrderTest {
    @Autowired
    private OrderDao userDao;

    @Autowired
    private GroupPersonDao groupPersonDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void func_drop_table() {

    }

    @Test
    public void func_save_order() {
        System.out.println(userDao);
        Order s = new Order();
        s.setStartTime(System.currentTimeMillis());

        List<GroupPerson> ret = createGroupPersons();

        s.setGroupPersons(ret);

        Earnest earnest = new Earnest();
        earnest.setStatus(0);
        earnest.setPrice(1d);
        s.setEarnest(earnest);

        userDao.refresh(s);
        userDao.save(s);
        System.out.println(s);
    }

    @Test
    public void func_findAll_Specification() {
        System.out.println(userDao);
        List<Order> all = userDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<String> namePath = root.get("contactName");
                Path<String> nicknamePath = root.get("contactEmail");
                /**
                 * 连接查询条件, 不定参数，可以连接0..N个查询条件
                 */
                criteriaQuery.where(cb.like(namePath, "%联系人%"), cb.like(nicknamePath, "%联系人%")); //这里可以设置任意条查询条件
                return null;
            }
        });
        all.stream().forEach((e)->System.out.println(e));
    }

    @Test
    public void func_findAll_order() {
        System.out.println(userDao);

        List<Order> all = userDao.findAll();
        all.stream().forEach((e)->System.out.println(e));
    }

    private List<GroupPerson> createGroupPersons() {
        List<GroupPerson> ret = new LinkedList<>();
        GroupPerson s1 = new GroupPerson();
        s1.setName("蒋礼");
        s1.setMobile("13761156786");

        GroupPerson s2 = new GroupPerson();
        s2.setName("蒋礼2");
        s2.setMobile("13761156786");
        ret.add(s1);
        ret.add(s2);

//        groupPersonDao.save(s1);
//        groupPersonDao.save(s2);
        return ret;
    }

    @Test
    public void func_save_GroupPerson() {
        System.out.println(groupPersonDao);
        GroupPerson s1 = new GroupPerson();
        s1.setName("蒋礼");
        s1.setMobile("13761156786");
        groupPersonDao.save(s1);
        System.out.println(s1);
    }


}
