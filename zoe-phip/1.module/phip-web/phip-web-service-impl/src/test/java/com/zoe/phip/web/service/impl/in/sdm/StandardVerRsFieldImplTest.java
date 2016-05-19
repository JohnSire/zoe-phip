package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StandardVerRsField;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Test
    public void testUpdateField() throws Exception {
        List<StandardVerRsField> info = new ArrayList<>();
        StandardVerRsField field = new StandardVerRsField();
        field.setFkCdaId("2");
        field.setFkSetId("22");
        field.setFkFieldId("221");
        field.setFkVersionId("1111");
        info.add(field);
        field = new StandardVerRsField();
        field.setFkCdaId("3");
        field.setFkSetId("31");
        field.setFkFieldId("311");
        field.setFkVersionId("1111");
        info.add(field);
        field = new StandardVerRsField();
        field.setFkCdaId("3");
        field.setFkSetId("32");
        field.setFkFieldId("321");
        field.setFkVersionId("1111");
        info.add(field); field = new StandardVerRsField();
        field.setFkCdaId("1");
        field.setFkSetId("123");
        field.setFkFieldId("123");
        field.setFkVersionId("1111");
        info.add(field);
        int i = impl.versionStandardStruct("1111", info);
    }
}
