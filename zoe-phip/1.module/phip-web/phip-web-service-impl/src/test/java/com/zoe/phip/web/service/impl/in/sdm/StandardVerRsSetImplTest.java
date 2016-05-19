package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhanghao on 2016/5/12.
 */
public class StandardVerRsSetImplTest extends BaseTest {

    @Autowired
    private StandardVerRsSetServiceImpl impl;

    @Test
    public void testGetVersionSet() throws Exception {
        List<StSetInfo> info = impl.getVerRsSetInfo("1111", "1");
    }
}
