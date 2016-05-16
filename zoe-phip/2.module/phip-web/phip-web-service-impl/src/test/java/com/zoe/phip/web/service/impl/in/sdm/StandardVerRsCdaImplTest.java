package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StandardVerRsCda;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Test
    public void testUpdateCda() throws Exception {
//        List<StandardVerRsCda> info = impl.getCdaRsByFkVersionId("1111");
//        boolean b = impl.versionStandardStruct("1112", info);
        List<StandardVerRsCda> info = impl.getCdaRsByFkVersionId("1111");
    }

    @Test
    public void testAddCdaRs() throws Exception {
        StandardVerRsCda entity = new StandardVerRsCda();
        entity.setFkCdaId("3");
        entity.setFkVersionId("1111");
        int add = impl.add(entity);
    }
}
