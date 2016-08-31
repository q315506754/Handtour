package com.handtours.service.api.domain.core.req.order;

import com.handtours.service.api.domain.core.req.DeleteReq;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class DeleteOrderReq extends DeleteReq<Long> {
    public DeleteOrderReq() {
    }

    public DeleteOrderReq(Long aLong) {
        super(aLong);
    }
}
