package com.handtours.service.runner;

import com.handtours.common.utils.PropertiesUtil;
import com.handtours.common.utils.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 */
public class Runner {
    protected final static Logger logger = LoggerFactory.getLogger(Runner.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            logger.debug("start runner.");
            logger.debug("load props...");
            List<Props> props = PropertiesUtil.getProps();
            for (Props prop : props) {
                Properties properties = prop.getProperties();
                logger.debug(""+prop.getFileName());
                Enumeration<?> enumeration = properties.propertyNames();
                while (enumeration.hasMoreElements()) {
                    Object element = enumeration.nextElement();
                    Object val = properties.get(element);
                    logger.debug(">>>"+element+":"+val);
                }
                logger.debug("------------------");
            }


            ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-*/*.xml");
            logger.debug("load xml over...");

//            System.in.read(); // 按任意键退出
            synchronized (logger) {
                logger.wait();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}
