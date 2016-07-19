package com.handtours.service.ext;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/19 0019 16:55
 */
public class ProviderCountInvokerListener implements InvokerListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {

    }

    @Override
    public void destroyed(Invoker<?> invoker) {

    }
}
