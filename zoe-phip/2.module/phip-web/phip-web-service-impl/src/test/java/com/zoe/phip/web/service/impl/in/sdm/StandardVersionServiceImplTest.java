package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.model.sdm.StandardVersion;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhanghao on 2016/5/9.
 */


public class StandardVersionServiceImplTest extends BaseTest {

    @Autowired
    private StandardVersionServiceImpl impl;

    @Test
    public void testAdd() throws Exception {
        StandardVersion standardVersion = new StandardVersion();
        standardVersion.setId(StringUtil.getUUID());
        standardVersion.setCode("123");
        standardVersion.setName("测试");
        impl.add(standardVersion);
    }


    @Test
    public void testQuery() throws Exception {
        QueryPage queryPage = new QueryPage(1,50);
        PageList<StandardVersion> list = impl.getDataPageList("1", queryPage);
    }

    @Test
    public void testDelete() throws Exception {
        int i = impl.deleteVersion("11");
    }


}
