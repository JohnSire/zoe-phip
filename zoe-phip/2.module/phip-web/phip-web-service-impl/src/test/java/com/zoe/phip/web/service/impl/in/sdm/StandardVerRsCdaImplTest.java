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
        List<StandardVerRsCda> list = new ArrayList<>();
        StandardVerRsCda rsCda = new StandardVerRsCda();
        rsCda.setFkVersionId("1111");
        rsCda.setFkCdaId("4");
        list.add(rsCda);
        rsCda = new StandardVerRsCda();
        rsCda.setFkVersionId("1111");
        rsCda.setFkCdaId("2");
        list.add(rsCda);
        rsCda = new StandardVerRsCda();
        rsCda.setFkVersionId("1111");
        rsCda.setFkCdaId("1");
        list.add(rsCda);
        int i = impl.versionStandardStruct("1111", list);
    }




}
