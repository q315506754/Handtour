package com.handtours.service.impl.project.back;

import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.dao.back.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class UserImpl extends ImplSupport implements IUser {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginRes login(LoginReq param) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    @Override
    public SaveUserRes batInsert(List<SaveUserReq> params) {
        for (SaveUserReq param : params) {
//            userMapper.insert();
        }
        return null;
    }


}
