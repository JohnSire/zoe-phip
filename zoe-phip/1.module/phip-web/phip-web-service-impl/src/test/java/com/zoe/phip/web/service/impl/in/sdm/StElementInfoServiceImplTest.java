package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhanghao on 2016/5/16.
 */
public class StElementInfoServiceImplTest extends BaseTest {

    @Autowired
    StElementInfoServiceImpl impl;

    @Test
    public void getList() throws Exception {

//        PageList<StElementInfo> list = impl.getDataPageList("", new QueryPage());
        StElementInfo relationById = impl.getRelationById("1");
    }

    @Test
    public void testGetData() throws Exception {
        StElementInfo dataById = impl.getDataById("3792afc0c7334347b8bde3f671d6c07d");
    }
}
