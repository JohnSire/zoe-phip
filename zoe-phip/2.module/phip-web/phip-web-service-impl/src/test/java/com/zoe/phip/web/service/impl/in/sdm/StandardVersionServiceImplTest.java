package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.model.sdm.StandardVerRsCda;
import com.zoe.phip.web.model.sdm.StandardVerRsField;
import com.zoe.phip.web.model.sdm.StandardVersion;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testStruct() throws Exception {
        List<StandardVerRsCda> cdaInfo = new ArrayList<>();
        StandardVerRsCda rsCda = new StandardVerRsCda();
        rsCda.setFkVersionId("1111");
        rsCda.setFkCdaId("1");
        cdaInfo.add(rsCda);
        List<StandardVerRsField> fieldInfo = new ArrayList<>();
        StandardVerRsField field = new StandardVerRsField();
        field.setFkCdaId("2");
        field.setFkSetId("22");
        field.setFkFieldId("221");
        field.setFkVersionId("1111");
        fieldInfo.add(field);
        field = new StandardVerRsField();
        field.setFkCdaId("3");
        field.setFkSetId("31");
        field.setFkFieldId("311");
        field.setFkVersionId("1111");
        fieldInfo.add(field);
        field = new StandardVerRsField();
        field.setFkCdaId("3");
        field.setFkSetId("32");
        field.setFkFieldId("321");
        field.setFkVersionId("1111");
        fieldInfo.add(field); field = new StandardVerRsField();
        field.setFkCdaId("1");
        field.setFkSetId("123");
        field.setFkFieldId("123");
        field.setFkVersionId("1111");
        fieldInfo.add(field);
//        impl.versionStandardStruct("1111")
    }


}
