package com.handtours.service.impl.project.back;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.req.QueryUserReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.domain.back.res.QueryUserOne;
import com.handtours.service.api.domain.back.res.QueryUserRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.domain.core.res.SaveRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.impl.project.core.ImplSupport;
import com.handtours.service.model.back.User;
import com.handtours.service.com.Ex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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
    public JpaRepository<User, String> getDao() {
        return userDao;
    }

    @Override
    public SaveUserRes save(SaveUserReq params) {
        Class<SaveUserRes> cls = SaveUserRes.class;
        if (!params.getPassword().equals(params.getSecondPassword())) {
            return retEx(cls,Ex.not_the_same,"密码") ;
        }

        SaveUserRes ret = super.save(params, cls);
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
        QueryUserRes res = new QueryUserRes();
        PageRequest pageRequest=new PageRequest(0,10);

        Page<User> users = userDao.queryList(params.getKeyword(), pageRequest);
        Page<QueryUserOne> map = users.map(user -> {
            QueryUserOne one = new QueryUserOne();
            return one;
        });
        List<QueryUserOne> content = map.getContent();

        res.setDataList(content);
        return res;
    }
}
