package com.handtours.service.impltest.project.back;

import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.project.back.IUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserTest {
    @Autowired
    private IUser iUser;

    @Test
    public void func() {
        System.out.println(iUser);
    }

    @Test
    public void func2() {
        ArrayList<SaveUserReq> params = new ArrayList<>();
        params.add(new SaveUserReq("13761156783", "123456"));
        params.add(new SaveUserReq("13761156786", "123456"));
        SaveUserRes res = iUser.batInsert(params);
        System.out.println(res);
    }

    @Test
    public void func3() {
        ArrayList<SaveUserReq> params = new ArrayList<>();
        params.add(new SaveUserReq("13761156782", "123456"));
        params.add(new SaveUserReq("13761156786", "123456"));
        SaveUserRes res = iUser.batInsert2(params);
        System.out.println(res);
    }

}
