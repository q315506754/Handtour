package com.handtours.service.api.domain.back.req.bg.user;

import com.handtours.service.api.domain.core.req.DeleteReq;
import com.handtours.service.api.domain.core.req.UpdateReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class DeleteUserReq extends DeleteReq<String> {
    public DeleteUserReq() {
    }

    public DeleteUserReq(String s) {
        super(s);
    }
}
