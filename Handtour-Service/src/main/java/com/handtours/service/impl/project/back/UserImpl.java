package com.handtours.service.impl.project.back;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.req.QueryUserReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.domain.back.res.QueryUserRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.domain.core.res.SaveRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.impl.project.core.ImplSupport;
import com.handtours.service.model.back.User;
import com.handtours.service.com.Ex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class UserImpl extends ImplSupport<User, String,SaveUserReq,SaveUserRes> implements IUser {
    @Autowired
    private UserDao userDao;

    @Override
    public LoginRes login(LoginReq param) {
        return null;
    }

    @Override
    public CrudRepository<User, String> getDao() {
        return userDao;
    }

    @Override
    public SaveUserRes save(SaveUserReq params) {
        SaveUserRes ret = super.save(params, SaveUserRes.class);
        return ret;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected String get_C_requestId(SaveUserReq req) {
        return req.getMobile();
    }

    @Override
    protected Object[] get_C_ex_exist() {
        return new Object[]{"账号"};
    }

    @Override
    public QueryUserRes query(QueryUserReq params) {
        return null;
    }
}
