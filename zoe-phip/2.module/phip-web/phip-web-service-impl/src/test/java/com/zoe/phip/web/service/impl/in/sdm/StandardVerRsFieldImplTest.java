package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhanghao on 2016/5/12.
 */
public class StandardVerRsFieldImplTest extends BaseTest {

    @Autowired
    private StandardVerRsFieldServiceImpl impl;

    @Test
    public void testGetField() throws Exception {
        List<StRsSetElementInfo> info = impl.getVerRsFieldInfo("1111", "1", "123");
    }
}
