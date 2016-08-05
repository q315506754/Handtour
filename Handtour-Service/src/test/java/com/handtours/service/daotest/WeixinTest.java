package com.handtours.service.daotest;

import com.handtours.service.dao.weixin.WeixinDao;
import com.handtours.service.model.weixin.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
@TransactionConfiguration(defaultRollback = false)
public class WeixinTest {
    @Autowired
    private WeixinDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void func222() {
        System.out.println(userDao);

        UserInfo byMobile = userDao.findOne("13761156786");
        System.out.println(byMobile);
    }


}
