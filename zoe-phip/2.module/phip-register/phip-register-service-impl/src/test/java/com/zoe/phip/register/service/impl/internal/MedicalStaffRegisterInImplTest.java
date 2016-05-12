package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.MedicalStaffInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/28.
 */
public class MedicalStaffRegisterInImplTest extends BaseTest {

    @Autowired
    private MedicalStaffRegisterInImpl medicalStaffRegisterIn;

    @Test
    public void testProviderListQuery() throws Exception {
        PageList<MedicalStaffInfo> results =
                medicalStaffRegisterIn.providerListQuery("3", "","", "", new QueryPage());
    }
    @Test
    public void testProvicerDetailsQuery() throws Exception {
        MedicalStaffInfo staffInfo = medicalStaffRegisterIn.providerDetailsQuery("14BFF2E85C1545DA93768841B0BBD949");
    }


}