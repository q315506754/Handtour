package com.handtours.service.impl.project.back;

import com.handtours.common.utils.Copier;
import com.handtours.common.utils.FuncUtil;
import com.handtours.service.api.domain.back.req.*;
import com.handtours.service.api.domain.back.res.*;
import com.handtours.service.api.domain.core.res.UpdateRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.com.Ex;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.impl.project.core.ImplSupport;
import com.handtours.service.model.back.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class UserImpl extends ImplSupport<User, String, SaveUserReq, SaveUserRes, QueryUserReq, QueryUserRes, QueryUserOne, UpdateUserReq, UpdateUserRes, DeleteUserReq, DeleteUserRes> implements IUser {
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
            return retEx(cls, Ex.not_the_same, "密码");
        }

        SaveUserRes ret = super.save(params, cls, null);
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
    protected Object[] generateRecordTitle() {
        return new Object[]{"账号"};
    }

    @Override
    public QueryUserRes query(QueryUserReq params) {
        QueryUserRes queryRs = super.query(params, QueryUserRes.class,
                new SortRequestBuilder<>(params, new Sort(Sort.Direction.DESC, "createTime")),
                (req, page) -> {
                    String keyword = params.getKeyword();
                    if (!StringUtils.isEmpty(keyword)) {
                        return userDao.queryList(keyword, page);
                    } else {
                        return userDao.findAll(page);
                    }
                },
                () -> Copier.to(QueryUserOne.class).compose(FuncUtil.auditDate2StringCopier));

        return queryRs;
    }

    @Override
    public UpdateUserRes update(UpdateUserReq params) {
        UpdateUserRes update = super.update(params, UpdateUserRes.class, null);
        return update;
    }

    @Override
    public DeleteUserRes delete(DeleteUserReq params) {
        DeleteUserRes deleted = super.delete(params, DeleteUserRes.class, null);
        return deleted;
    }
}
