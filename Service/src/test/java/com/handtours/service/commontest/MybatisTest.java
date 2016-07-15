package com.handtours.service.commontest;

import com.handtours.service.dao.back.UserMapper;
import com.handtours.service.model.back.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class MybatisTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void func() {
        System.out.println(userMapper);

        User user = userMapper.selectByPrimaryKey("13761156786");
        System.out.println(user);
        System.out.println(user.getPassword());
    }

}
