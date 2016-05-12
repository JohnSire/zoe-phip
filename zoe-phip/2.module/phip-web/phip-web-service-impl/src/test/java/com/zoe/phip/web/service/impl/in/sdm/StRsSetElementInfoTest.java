package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhanghao on 2016/5/12.
 */
public class StRsSetElementInfoTest extends BaseTest {

    @Autowired
    private StRsSetElementInfoServiceImpl impl;

    @Test
    public void testSet() throws Exception{
        impl.getBySetCode("", "");
    }
}
