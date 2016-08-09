package com.handtours.service.com;

import com.handtours.service.api.domain.back.req.SaveUserReq;
import com.handtours.service.api.domain.core.req.SaveReq;
import com.handtours.service.api.domain.core.res.SaveRes;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 10:42
 */
public interface C<REQ extends SaveReq,RES extends SaveRes> {
    RES save(REQ req,Class<RES> cls);
}
