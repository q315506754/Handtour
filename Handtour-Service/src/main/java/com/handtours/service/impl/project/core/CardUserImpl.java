package com.handtours.service.impl.project.core;

import com.handtours.common.constants.core.CardStatus;
import com.handtours.common.utils.Copier;
import com.handtours.common.utils.FuncUtil;
import com.handtours.common.utils.PriceUtil;
import com.handtours.service.api.domain.back.req.bg.user.*;
import com.handtours.service.api.domain.back.res.bg.user.*;
import com.handtours.service.api.domain.core.req.card.user.DeleteCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.QueryCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.SaveCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.UpdateCardUserReq;
import com.handtours.service.api.domain.core.res.card.user.*;
import com.handtours.service.api.project.back.IUser;
import com.handtours.service.api.project.core.ICardUser;
import com.handtours.service.com.Ex;
import com.handtours.service.dao.back.UserDao;
import com.handtours.service.dao.common.CardUserDao;
import com.handtours.service.model.back.User;
import com.handtours.service.model.core.CardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public class CardUserImpl extends ImplSupport<CardUser, String, SaveCardUserReq, SaveCardUserRes, QueryCardUserReq, QueryCardUserRes, QueryCardUserOne, UpdateCardUserReq, UpdateCardUserRes, DeleteCardUserReq, DeleteCardUserRes> implements ICardUser {
    @Autowired
    private CardUserDao userDao;


    @Override
    public JpaRepository<CardUser, String> getDao() {
        return userDao;
    }

    @Override
    public SaveCardUserRes save(SaveCardUserReq params) {
        Class<SaveCardUserRes> cls = SaveCardUserRes.class;
        if (!params.getPassword().equals(params.getSecondPassword())) {
            return retEx(cls, Ex.not_the_same, "密码");
        }
        SaveCardUserRes ret = super.save(params, cls, null);
        return ret;
    }

    @Override
    public Class<CardUser> getEntityClass() {
        return CardUser.class;
    }

    @Override
    protected String get_C_requestId(SaveCardUserReq req) {
        return req.getMobile();
    }

    @Override
    protected Object[] generateRecordTitle() {
        return new Object[]{"账号"};
    }

    @Override
    public QueryCardUserRes query(QueryCardUserReq params) {
        QueryCardUserRes queryRs = super.query(params, QueryCardUserRes.class,
                new SortRequestBuilder<>(params, new Sort(Sort.Direction.DESC, "createTime")),
                (req, page) -> {
                    String keyword = params.getKeyword();
                    if (!StringUtils.isEmpty(keyword)) {
                        return userDao.queryList(keyword, page);
                    } else {
                        return userDao.findAll(page);
                    }
                },
                () -> Copier.to(QueryCardUserOne.class).
                        compose(FuncUtil.auditDate2StringCopier).
                        map("balance","balanceStr", FuncUtil.priceConvertor).
                        map("status","statusStr", FuncUtil.createStatusConvertor(CardStatus.class))

        );

        return queryRs;
    }

    @Override
    public UpdateCardUserRes update(UpdateCardUserReq params) {
        Class<UpdateCardUserRes> cls = UpdateCardUserRes.class;

        if (params.getPassword() != null && params.getSecondPassword() != null && !params.getPassword().equals(params.getSecondPassword())) {
            return retEx(cls, Ex.not_the_same, "密码");
        }

        UpdateCardUserRes update = super.update(params, cls, null);
        return update;
    }

    @Override
    public DeleteCardUserRes delete(DeleteCardUserReq params) {

        DeleteCardUserRes deleted = super.delete(params, DeleteCardUserRes.class, null);
        return deleted;
    }
}
