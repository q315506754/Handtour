package com.handtours.service.com;

import com.handtours.common.utils.Copier;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/10 0010 17:07
 */
public interface ReqCopierChain<T> {
    void chainPostProcessor(Copier<T> copier);
}
