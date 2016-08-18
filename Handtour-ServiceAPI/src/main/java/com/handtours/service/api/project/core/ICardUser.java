package com.handtours.service.api.project.core;
import com.handtours.service.api.domain.back.req.bg.user.*;
import com.handtours.service.api.domain.back.res.bg.user.*;
import com.handtours.service.api.domain.core.req.card.user.DeleteCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.QueryCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.SaveCardUserReq;
import com.handtours.service.api.domain.core.req.card.user.UpdateCardUserReq;
import com.handtours.service.api.domain.core.res.card.user.DeleteCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.QueryCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.SaveCardUserRes;
import com.handtours.service.api.domain.core.res.card.user.UpdateCardUserRes;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public interface ICardUser {
    SaveCardUserRes save(SaveCardUserReq params);

    QueryCardUserRes query(QueryCardUserReq params);

    UpdateCardUserRes update(UpdateCardUserReq params);

    DeleteCardUserRes delete(DeleteCardUserReq params);


}
