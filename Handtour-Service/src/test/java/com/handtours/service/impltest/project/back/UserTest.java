package com.handtours.service.impltest.project.back;

import com.handtours.service.api.domain.back.req.QueryUserReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.QueryUserRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.commontest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/15 0015 16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml", "classpath*:application-service/*.xml"})
public class UserTest extends BaseTest {
    @Autowired
    @Qualifier("bean_back_user")
    private IUser iUser;

    @Test
    public void func_save_not_same() {
        System.out.println(iUser);

        SaveUserReq params = new SaveUserReq();
        params.setMobile(TEST_MOBILE);
        params.setName("name:" + rnd());
        params.setPassword(rnd() + "");
        params.setSecondPassword(rnd() + "");
        params.setEnable(true);
        params.setEmail(rnd() + "@qq.com");

        SaveUserRes save = iUser.save(params);
        System.out.println(save);
    }
    @Test
    public void func_save_same() {
        System.out.println(iUser);

        SaveUserReq params = new SaveUserReq();
        params.setMobile(TEST_MOBILE);
        params.setName("name:" + rnd());
        String secondPassword = rnd() + "";
        params.setPassword(secondPassword);
        params.setSecondPassword(secondPassword);
        params.setEnable(true);
        params.setEmail(rnd() + "@qq.com");

        SaveUserRes save = iUser.save(params);
        System.out.println(save);
    }

    @Test
    public void func_query() {
        QueryUserReq params = new QueryUserReq();
        params.setKeyword("137");
        QueryUserRes query = iUser.query(params);
        System.out.println(query);
    }
}
