package com.handtours.service.daotest;

import com.handtours.service.dao.back.AddressDao;
import com.handtours.service.dao.back.ProjectionForAddress;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.model.back.Address;
import com.handtours.service.model.back.UserInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
//@TransactionConfiguration(defaultRollback = false)
public class BackUserTest {
    public static final String TEST_MOBILE = "13761156786";

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    static final  Random random = new Random();
    static final int randomSize = 100000;

    public int rnd() {
        return random.nextInt(randomSize);
    }

    @PersistenceContext
    private EntityManager entityManager;

//    @Transactional
    @Test
    public void func_create_drop_if_exist() {
        if (userDao.findByMobile(TEST_MOBILE) != null) {
            userDao.delete(TEST_MOBILE);
            System.out.println("dropped:"+TEST_MOBILE);
        }

        UserInfo accountInfo = new UserInfo();
        accountInfo.setMobile(TEST_MOBILE);
        accountInfo.setName("name:"+rnd());
        accountInfo.setPassword(rnd()+"");

        Address address = new Address();
        address.setCountry("中国");
        address.setState("上海");
        address.setStreet("南京东路");

        accountInfo.setAddress(address);

        Address save = addressDao.save(address);
        UserInfo byMobile = userDao.save(accountInfo);

        System.out.println("created:"+byMobile);

    }

    @Test
    public void func_fund() {
        System.out.println(userDao);
        System.out.println(entityManager);

        UserInfo accountInfo = new UserInfo();
        accountInfo.setMobile("123232332");

        UserInfo byMobile = userDao.save(accountInfo);
        System.out.println(byMobile);

        accountInfo.setMobile("13333333333");
        byMobile = userDao.saveCustom(accountInfo);
        System.out.println(byMobile);

    }


    @Test
    public void func_query_param() {
        UserInfo byMobile = userDao.findByMobileCustom(TEST_MOBILE);
        System.out.println(byMobile);
        byMobile = userDao.findByMobile(TEST_MOBILE);
        System.out.println(byMobile);
    }
    @Test
    public void func_query_projected() {
        ProjectionForAddress byMobile = userDao.findUserInfoByMobile(TEST_MOBILE);
        System.out.println(byMobile);
        System.out.println(byMobile.getMobile());
        try {
            System.out.println(BeanUtils.describe(byMobile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void func_update_modifying() {
        String mobile = TEST_MOBILE;
        UserInfo byMobile = userDao.findByMobileCustom(mobile);
        System.out.printf("org name:%s\n",byMobile.getName());

        int i = userDao.updateName(mobile, "nnnNNNname" + rnd());

        System.out.printf("effedted n:%d\n",i);

        System.out.printf("entity name:%s\n",byMobile.getName());
        byMobile = userDao.findByMobileCustom(mobile);
        System.out.printf("recent name:%s\n",byMobile.getName());


    }
}
