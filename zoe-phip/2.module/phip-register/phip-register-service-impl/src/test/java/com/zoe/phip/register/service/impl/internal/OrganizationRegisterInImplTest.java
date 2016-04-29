package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.OrgDeptInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/29.
 */
public class OrganizationRegisterInImplTest extends BaseTest {

    @Autowired
    private OrganizationRegisterInImpl organizationRegisterIn;

    @Test
    public void testGetDeptInfoListByType() throws Exception {
        PageList<OrgDeptInfo> list= organizationRegisterIn.getDeptInfoListByType("0101");
    }
}