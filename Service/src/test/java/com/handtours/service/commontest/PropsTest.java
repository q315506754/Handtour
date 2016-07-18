package com.handtours.service.commontest;

import com.handtours.common.utils.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void func2() {
        System.out.println(PropertiesUtil.readStringValue("redis.ip"));
    }
}
