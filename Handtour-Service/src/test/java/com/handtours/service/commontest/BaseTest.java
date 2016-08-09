package com.handtours.service.commontest;

import java.util.Random;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/8 0008 17:18
 */
public class BaseTest {

    public static final String TEST_MOBILE = "13761156786";
    static final Random random = new Random();
    static final int randomSize = 1000000;

    public int rnd() {
        return random.nextInt(randomSize);
    }
}
