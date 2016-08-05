package com.handtours.service.commontest;

import com.handtours.service.dao.back.UserMapper;
import com.handtours.service.jpa.dao.back.UserDao;
import com.handtours.service.jpa.model.back.UserInfo;
import com.handtours.service.model.back.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
@TransactionConfiguration(defaultRollback=false)
public class JPATest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void func() {
        System.out.println(userDao);

        UserInfo byMobile = userDao.findByMobile("13761156786");
        System.out.println(byMobile);
    }

    @Test
    public void funcReg() {
        System.out.println(userDao);

        Page<UserInfo> byMobileRegex = userDao.findByMobileRegex("76", new PageRequest(0, 10));
        System.out.println(byMobileRegex);
        List<UserInfo> content = byMobileRegex.getContent();
        System.out.println(content);
    }

    @Test
    @Transactional()
    public void funcCustom() {
        System.out.println(userDao);
        System.out.println(entityManager);

//        UserInfo byMobileRegex = userDao.findRandomOne();
//        System.out.println(byMobileRegex);
        UserInfo accountInfo = new UserInfo();
        accountInfo.setMobile("13777778");
        accountInfo.setName("asdsad");
        userDao.save(accountInfo);

    }



}
