package com.handtours.service.impl.project.back;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.domain.back.res.SaveUserRes;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.impl.project.core.ImplSupport;
import com.handtours.service.model.back.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class UserImpl extends ImplSupport implements IUser {
    @Autowired
    private UserDao userDao;

    @Override
    public LoginRes login(LoginReq param) {
        LoginRes res = new LoginRes();
        UserInfo user = userDao.findByMobile(param.getUsername());
        if (user == null) {
            res.set(1, "用户不存在");
        } else {
            if (!user.getPassword().equals(param.getPassword())) {
                res.set(2, "密码不正确");
            } else {
                Copier.to(res).from(user);
            }

        }

        return res;
    }

    /**
     * 注解事务test
     * 任意一条插入失败会导致回滚
     *
     * @param params
     *
     * @return
     */
    @Transactional
    @Override
    public SaveUserRes batInsert(List<SaveUserReq> params) {
        SaveUserRes res = new SaveUserRes();
        for (SaveUserReq param : params) {
//            User record = convertReqToModel(param);
//
//            userMapper.insert(record);
        }
        return res;
    }

    private UserInfo convertReqToModel(SaveUserReq param) {
        UserInfo user = Copier.to(UserInfo.class).map("username", "mobile").map("password", "password", (val) -> String.valueOf(val)).from(param);
        logger.debug("user:" + user);

        return user;
    }

    /**
     * SqlSession事务 批量操作test
     * 任意一条插入失败会导致回滚
     *
     * @param params
     *
     * @return
     */
    @Override
    public SaveUserRes batInsert2(List<SaveUserReq> params) {
        SaveUserRes res = new SaveUserRes();
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
//
//        try {
//            for (SaveUserReq param : params) {
//                User record = convertReqToModel(param);
//
//                sqlSession.insert(PathUtil.buildCls(UserMapper.class, "insert"), record);
//            }
//            sqlSession.flushStatements();
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setCode(1);
//            res.setMsg(e.getMessage());
//        } finally {
//            sqlSession.close();
//        }
        return res;
    }
}
