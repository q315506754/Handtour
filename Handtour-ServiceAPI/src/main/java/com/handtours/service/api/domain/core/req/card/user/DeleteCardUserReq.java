package com.handtours.service.api.domain.core.req.card.user;

import com.handtours.service.api.domain.core.req.DeleteReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class DeleteCardUserReq extends DeleteReq<String> {
    public DeleteCardUserReq() {
    }

    public DeleteCardUserReq(String s) {
        super(s);
    }
}
