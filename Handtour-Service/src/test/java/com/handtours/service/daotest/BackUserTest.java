package com.handtours.service.daotest;

import com.handtours.service.commontest.BaseTest;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.model.back.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
//@TransactionConfiguration(defaultRollback = false)
public class BackUserTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    //    @Transactional
    @Test
    public void func_create_drop_if_exist() {
        if (userDao.findOne(TEST_MOBILE) != null) {
            userDao.delete(TEST_MOBILE);
            System.out.println("dropped:" + TEST_MOBILE);
        }

        User accountInfo = new User();
        accountInfo.setMobile(TEST_MOBILE);
        accountInfo.setName("name:" + rnd());
        accountInfo.setPassword(rnd() + "");
        accountInfo.setEnable(true);
        accountInfo.setEmail(rnd() + "@qq.com");
        Date cur = new Date();
//        accountInfo.setCreateTime(cur);
//        accountInfo.setLastUpdateTime(cur);
        User byMobile = userDao.save(accountInfo);

        System.out.println("created:" + byMobile);

    }


    @Test
    public void func_query_param() {
        User byMobile = userDao.findOne(TEST_MOBILE);
        System.out.println(byMobile);
    }

    @Test
    public void func_update_() {
        User byMobile = userDao.findOne(TEST_MOBILE);
        System.out.println("org:" + byMobile);

        byMobile.setName("updated name..." + rnd());
        userDao.save(byMobile);
        System.out.println("after:" + byMobile);
    }
}
