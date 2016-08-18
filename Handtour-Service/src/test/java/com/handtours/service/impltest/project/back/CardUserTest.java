package com.handtours.service.impltest.project.back;

import com.handtours.service.api.domain.core.req.card.user.DeleteCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.QueryCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.SaveCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.UpdateCardUserReq;
import com.handtours.service.api.domain.core.res.card.user.DeleteCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.QueryCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.SaveCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.UpdateCardUserRes;
import com.handtours.service.api.project.core.ICardUser;
import com.handtours.service.commontest.BaseTest;
import com.handtours.service.dao.common.CardUserDao;
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
public class CardUserTest extends BaseTest {
    @Autowired
    @Qualifier("bean_card_user")
    private ICardUser iUser;
    @Autowired
    private CardUserDao userDao;

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

        SaveCardUserReq params = new SaveCardUserReq();
        params.setMobile(TEST_MOBILE);
        params.setName("name:" + rnd());
        params.setPassword(rnd() + "");
        params.setSecondPassword(rnd() + "");
        params.setEnable(true);
        params.setEmail(rnd() + "@qq.com");

        SaveCardUserRes save = iUser.save(params);
        System.out.println(save);
    }

    @Repeat(22)
    @Test
    public void func_save_multi() {
        SaveCardUserReq params = new SaveCardUserReq();
        params.setMobile("13" + rnd());
        params.setName("multi name:" + rnd());
        String secondPassword = rnd() + "";
        params.setPassword(secondPassword);
        params.setSecondPassword(secondPassword);
        params.setEnable(true);
        params.setEmail(rnd() + "@qq.com");

        SaveCardUserRes save = iUser.save(params);
        System.out.println(save);
    }

    @Test
    public void func_save_same() {
        System.out.println(iUser);

        SaveCardUserReq params = new SaveCardUserReq();
        params.setMobile(TEST_MOBILE);
//        params.setName("name:" + rnd());
        String secondPassword = rnd() + "";
        params.setPassword(secondPassword);
        params.setSecondPassword(secondPassword);
        params.setEnable(true);
//        params.setEmail(rnd() + "@qq.com");

        SaveCardUserRes save = iUser.save(params);
        System.out.println(save);
    }

    @Test
    public void func_query() {
        QueryCardUserReq params = new QueryCardUserReq();
        params.setKeyword("137");
        QueryCardUserRes query = iUser.query(params);
        System.out.println(query);
    }

    @Test
    public void func_query_all() {
        QueryCardUserReq params = new QueryCardUserReq();
//        params.setKeyword("137");
        QueryCardUserRes query = iUser.query(params);
        System.out.println(query);
    }

    @Test
    public void func_query_all_page() {
        QueryCardUserReq params = new QueryCardUserReq();
//        params.setKeyword("137");
        params.setPageIndex(2);
        QueryCardUserRes query = iUser.query(params);
        System.out.println(query);
    }

    @Test
    public void func_update_name() {
        UpdateCardUserReq params = new UpdateCardUserReq(TEST_MOBILE);
        params.setName("updated name:" + rnd());
        UpdateCardUserRes query = iUser.update(params);
        System.out.println(query);
    }

    @Test
    public void func_update_with_ts() {
        UpdateCardUserReq params = new UpdateCardUserReq(TEST_MOBILE);
        params.setLastUpdateTimeTs(System.currentTimeMillis());
        params.setName("updated name:" + rnd());
        UpdateCardUserRes query = iUser.update(params);
        System.out.println(query);
    }

    @Test
    public void func_drop_save_and_update() {
        func_drop_and_save();
        func_update_name();
    }

    @Test
    public void func_delete_logic() {
        DeleteCardUserReq params = new DeleteCardUserReq(TEST_MOBILE);
        DeleteCardUserRes query = iUser.delete(params);
        System.out.println(query);
    }

    @Test
    public void func_delete_logic_with_ts() {
        DeleteCardUserReq params = new DeleteCardUserReq(TEST_MOBILE);
        params.setLastUpdateTimeTs(System.currentTimeMillis());
        DeleteCardUserRes query = iUser.delete(params);
        System.out.println(query);
    }

    @Test
    public void func_delete_physical() {
        DeleteCardUserReq params = new DeleteCardUserReq(TEST_MOBILE);
        params.setLogicalDeletion(false);
        DeleteCardUserRes query = iUser.delete(params);
        System.out.println(query);
    }
}
