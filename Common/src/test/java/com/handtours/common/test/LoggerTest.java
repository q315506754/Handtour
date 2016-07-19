package com.handtours.common.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/14 0014 16:40
 */
public class LoggerTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void func() {
        logger.debug("log ok..");
        Assert.assertTrue("ok", true);
    }

}
