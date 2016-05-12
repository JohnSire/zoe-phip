package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhanghao on 2016/5/11.
 */
public class StandardVerRsCdaImplTest extends BaseTest {

    @Autowired
    private StandardVerRsCdaServiceImpl impl;

    @Test
    public void testGetCdaByFk() throws Exception {
        List<StCdaInfo> verRsCdaInfo = impl.getVerRsCdaInfo("1111");
    }
}
