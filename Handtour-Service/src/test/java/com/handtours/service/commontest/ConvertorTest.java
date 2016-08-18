package com.handtours.service.commontest;

import com.handtours.common.constants.core.CardStatus;
import com.handtours.common.utils.FuncUtil;
import org.junit.Test;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:57
 */
public class ConvertorTest {

    @Test
    public void func() {
        FuncUtil.StatusConvertor statusConvertor = new FuncUtil.StatusConvertor(CardStatus.class);
        System.out.println(statusConvertor.getMapper());
    }


}
