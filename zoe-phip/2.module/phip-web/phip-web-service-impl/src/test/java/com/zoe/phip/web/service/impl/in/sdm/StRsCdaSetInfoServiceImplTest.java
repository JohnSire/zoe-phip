package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghao on 2016/5/16.
 */
public class StRsCdaSetInfoServiceImplTest extends BaseTest {

    @Autowired
    StRsCdaSetInfoServiceImpl impl;

    @Test
    public void testUpdate() throws Exception {
        StRsCdaSetInfo info = new StRsCdaSetInfo();
        List<StRsCdaSetInfo> listInfo = new ArrayList<>();
        info.setFkCdaId("2");
        info.setFkSetId("223");
        info.setId(StringUtil.getUUID());
        listInfo.add(info);
//        info.setFkCdaId("2");
//        info.setFkSetId("123");
//        info.setId(StringUtil.getUUID());
//        listInfo.add(info);
   //     int i = impl.updateByCdaId("2", listInfo);
    }
}
