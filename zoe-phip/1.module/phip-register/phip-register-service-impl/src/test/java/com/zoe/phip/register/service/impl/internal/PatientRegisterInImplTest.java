package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.impl.external.PatientRegisterImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/26.
 */
public class PatientRegisterInImplTest extends BaseTest {

    @Autowired
    private PatientRegisterInImpl patientRegister;

    @Test
    public void testPatientRegistryListQuery() throws Exception {
        PageList<XmanBaseInfo> result =
                patientRegister.patientRegistryListQuery("", new QueryPage());
    }

    @Test
    public void testGetById() throws Exception {
        XmanBaseInfo result = patientRegister.getById("ecd03ef24b7143d29aa4d57574f192c2");
    }

    @Test
    public void testGetPatient() throws Exception {
        int result = patientRegister.deleteById("3e3a46c835cb4aa3b83ba0cf4aab716f");

    }
}