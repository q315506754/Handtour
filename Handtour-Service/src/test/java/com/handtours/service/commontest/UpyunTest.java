package com.handtours.service.commontest;

import com.handtours.common.utils.UpYunUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:57
 */
public class UpyunTest {
    File file;

    @Before
    public void setUp() {
        file = new File("C:\\Users\\Administrator\\Desktop\\ttt\\20151210102927.png");
        System.out.println(file+" exists:" +file.exists());
    }


    @Test
    public void func_uploadFile() {
        try {
            String filePath = "a/b/c";
            boolean b = UpYunUtil.uploadFile(filePath, file);
            System.out.println(filePath+" rs:"+b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void func_uploadFileAndGetFileName() {
        try {
            String filePath = "/a/b/c";
            String b = UpYunUtil.uploadFileAndGetFileName(filePath, file);
            System.out.println(filePath+" rs:"+b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 推荐此法
     *  输入文件C:\Users\Administrator\Desktop\ttt\20151210102927.png
     *
     * 生成路径 http://handtours.b0.upaiyun.com/5baa120a345627b637f63b76f3626754
     */
    @Test
    public void func_uploadFileAndGetFileName2() {
        try {
            String b = UpYunUtil.uploadFileAndGetFileName(file);
            System.out.println(" rs:"+b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void func_getFileMetaInfo() {
        try {
            String filePath = "/a/b/c";
            Map b = UpYunUtil.getFileMetaInfo(filePath);
            System.out.println(filePath+" rs:"+b);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
