package com.handtours.service.impltest.project.back;

import com.handtours.service.api.project.back.IUser;
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
@ContextConfiguration(locations = {"classpath*:application-common/*.xml","classpath*:application-service/*.xml"})
public class UserTest {
    @Autowired
    private IUser iUser;

    @Test
    public void func() {
        System.out.println(iUser);
    }

}
