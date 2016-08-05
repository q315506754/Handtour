package com.handtours.example.consumer;

import com.handtours.service.api.domain.back.req.LoginReq;
import com.handtours.service.api.domain.back.res.LoginRes;
import com.handtours.service.api.project.back.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 */
public class ConsumerRunner {
    protected final static Logger logger = LoggerFactory.getLogger(ConsumerRunner.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            logger.debug("start consumer.");

            ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application*.xml");
            logger.debug("load xml over...");
            IUser bean = context.getBean(IUser.class);
            logger.debug("bean:" + bean);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    LoginReq param = new LoginReq();
                    param.setUsername("13761156786");
//            param.setPassword("123456");
                    param.setPassword("e10adc3949ba59abbe56e057f20f883e");
                    LoginRes login = bean.login(param);
                    long cost = System.currentTimeMillis() - start;
                    logger.debug(Thread.currentThread().getName() + "_" + "rs:" + login);
                    logger.debug(Thread.currentThread().getName() + "_" + "cost:" + cost + " ms");
                }
            };

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            int threads = 100;
            while (threads-- > 0) {
//                executorService.execute(runnable);
                runnable.run();
            }

//            System.in.read(); // 按任意键退出
            synchronized (logger) {
                logger.wait();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}
