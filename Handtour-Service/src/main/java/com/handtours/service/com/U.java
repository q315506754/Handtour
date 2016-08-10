package com.handtours.service.com;

import com.handtours.service.api.domain.core.req.UpdateReq;
import com.handtours.service.api.domain.core.res.UpdateRes;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 10:42
 */
public interface U<T, ID, REQ extends UpdateReq<ID>, RES extends UpdateRes> {
    RES update(REQ req, Class<RES> cls, ReqCopierChain<T> copierChain);
}
