package com.handtours.service.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class Runner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}
