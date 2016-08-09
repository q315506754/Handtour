package com.handtours.service.commontest;

import com.handtours.service.dao.back.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class JPATest {
    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void func() {
        System.out.println(userDao);

    }

    @Test
    public void funcReg() {
        System.out.println(userDao);
    }

    @Test
    @Transactional()
    public void funcCustom() {
        System.out.println(userDao);
        System.out.println(entityManager);


    }


}
