package com.handtours.service.impltest.project.back;

import com.handtours.service.api.domain.back.req.DeleteUserReq;
import com.handtours.service.api.domain.back.req.QueryUserReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.req.UpdateUserReq;
import com.handtours.service.api.domain.back.res.DeleteUserRes;
import com.handtours.service.api.domain.back.res.QueryUserRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.domain.back.res.UpdateUserRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.commontest.BaseTest;
import com.handtours.service.dao.back.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    @Autowired
    private UserDao userDao;

    @Test
    public void func_drop_and_save() {
        System.out.println(iUser);
        if (userDao.findOne(TEST_MOBILE) != null) {
            userDao.delete(TEST_MOBILE);
        }
        func_save_same();
    }

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

    @Repeat(22)
    @Test
    public void func_save_multi() {
        SaveUserReq params = new SaveUserReq();
        params.setMobile("13" + rnd());
        params.setName("multi name:" + rnd());
        String secondPassword = rnd() + "";
        params.setPassword(secondPassword);
        params.setSecondPassword(secondPassword);
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

    @Test
    public void func_query_all() {
        QueryUserReq params = new QueryUserReq();
//        params.setKeyword("137");
        QueryUserRes query = iUser.query(params);
        System.out.println(query);
    }

    @Test
    public void func_query_all_page() {
        QueryUserReq params = new QueryUserReq();
//        params.setKeyword("137");
        params.setPageIndex(2);
        QueryUserRes query = iUser.query(params);
        System.out.println(query);
    }

    @Test
    public void func_update_name() {
        UpdateUserReq params = new UpdateUserReq(TEST_MOBILE);
        params.setName("updated name:" + rnd());
        UpdateUserRes query = iUser.update(params);
        System.out.println(query);
    }

    @Test
    public void func_update_with_ts() {
        UpdateUserReq params = new UpdateUserReq(TEST_MOBILE);
        params.setLastUpdateTimeTs(System.currentTimeMillis());
        params.setName("updated name:" + rnd());
        UpdateUserRes query = iUser.update(params);
        System.out.println(query);
    }

    @Test
    public void func_drop_save_and_update() {
       func_drop_and_save();
        func_update_name();
    }

    @Test
    public void func_delete_logic() {
        DeleteUserReq params = new DeleteUserReq(TEST_MOBILE);
        DeleteUserRes query = iUser.delete(params);
        System.out.println(query);
    }
    @Test
    public void func_delete_logic_with_ts() {
        DeleteUserReq params = new DeleteUserReq(TEST_MOBILE);
        params.setLastUpdateTimeTs(System.currentTimeMillis());
        DeleteUserRes query = iUser.delete(params);
        System.out.println(query);
    }

    @Test
    public void func_delete_physical() {
        DeleteUserReq params = new DeleteUserReq(TEST_MOBILE);
        params.setLogicalDeletion(false);
        DeleteUserRes query = iUser.delete(params);
        System.out.println(query);
    }
}
