package com.handtours.service.ext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.ExporterListener;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/19 0019 16:55
 */
public class ProviderCountExporterListener implements ExporterListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected int count=0;

    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        Invoker<?> invoker = exporter.getInvoker();
        Class<?> anInterface = invoker.getInterface();
        URL url = invoker.getUrl();
        if (Constants.LOCAL_PROTOCOL.toString().equals(url.getProtocol())) {
            return;
        }
        String group = url.getParameter(Constants.GROUP_KEY, "NONE");
        String uniqueKey = group + "/" + anInterface.getCanonicalName();

        logger.debug((++count) + ">>>" + uniqueKey + " >>>URL:" + url);

    }

    @Override
    public void unexported(Exporter<?> exporter) {

    }
}
