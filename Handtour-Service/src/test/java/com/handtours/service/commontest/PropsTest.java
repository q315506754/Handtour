package com.handtours.service.commontest;

import com.handtours.common.utils.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/14 0014 16:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-common/*.xml"})
public class PropsTest {

    @Value("${redis.ip}")
    String redisIp;

    @Test
    public void func() {
        System.out.println(redisIp);
    }

    @Test
    public void func33() {
        ClassPathResource x = new ClassPathResource("msg/excetions.properties");
        System.out.println(x);
        System.out.println(x.exists());
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(x.getInputStream(), "utf8"));

            String pattern = String.valueOf(prop.get("1"));
            System.out.println(pattern);
            String format = MessageFormat.format(pattern, "aaaa");
            System.out.println(format);

        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        ResourceBundle bundle = new Res();

    }

    @Test
    public void func2() {
        System.out.println(PropertiesUtil.readStringValue("redis.ip"));
    }
}
