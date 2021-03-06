package com.handtours.service.ext;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory;
import com.handtours.service.utils.DubboUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/19 0019 17:45
 */
public class JavassistExProxyFactory extends JavassistProxyFactory {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Map<String, String> infToImplMap = new HashMap<>();

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        logger.debug("getProxy");
        T proxy = super.getProxy(invoker, interfaces);
        return proxy;
    }

    @Override
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) {
//        logger.debug("getInvoker");
//        logger.debug("proxy:"+proxy.getClass());
//        logger.debug("proxy:"+proxy);
//        logger.debug("type:"+type);

        String uniqueKey = DubboUtil.uniqueKey(type, url);
        infToImplMap.put(uniqueKey, proxy.toString());

        Invoker<T> invoker = super.getInvoker(proxy, type, url);
        return invoker;
    }
}
