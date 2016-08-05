package com.handtours.service.api.project.back;

import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;

import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public interface IUser {
    LoginRes login(LoginReq param);

    SaveUserRes batInsert(List<SaveUserReq> params);

    SaveUserRes batInsert2(List<SaveUserReq> params);
}
