package com.handtours.service.commontest;

import com.handtours.common.utils.FileUtil;
import org.junit.Test;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/18 0018 16:04
 */
public class FileUtilTest {

    @Test
    public void func() {
        String fileName = "xxxaaa.jpg";
        System.out.println(FileUtil.getPrefix(fileName));
        System.out.println(FileUtil.getSuffix(fileName));
    }

}
