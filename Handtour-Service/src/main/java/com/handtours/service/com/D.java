package com.handtours.service.com;

import com.handtours.service.api.domain.core.req.DeleteReq;
import com.handtours.service.api.domain.core.res.DeleteRes;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 10:42
 */
public interface D<T, ID, REQ extends DeleteReq<ID>, RES extends DeleteRes> {
    RES delete(REQ req, Class<RES> cls, ReqCopierChain<T> copierChain);
}
