package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhanghao on 2016/5/12.
 */
public class StRsSetElementInfoTest extends BaseTest {

    @Autowired
    private StRsSetElementInfoServiceImpl impl;

    @Autowired
    private StElementInfoServiceImpl simpl;

    @Test
    public void testSet() throws Exception {
        PageList<StRsSetElementInfo> dataPageList = impl.getDataPageList("522dc8b3-213b-475c-980f-89cef50ae92f", "", new QueryPage());
    }

    @Test
    public void testAdd() throws Exception {
        StElementInfo ss = new StElementInfo();
        ss.setCode("DE04.30.007.00");
        simpl.add(ss);
    }
}
