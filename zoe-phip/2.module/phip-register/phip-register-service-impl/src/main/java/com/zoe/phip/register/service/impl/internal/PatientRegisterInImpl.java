package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IPatientRegisterIn;

/**
 * Created by zengjiyang on 2016/4/22.
 */
public class PatientRegisterInImpl implements IPatientRegisterIn {
    @Override
    public ServiceResultT<XmanBaseInfo> addPatientRegistry(XmanBaseInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<XmanBaseInfo> updatePatientRegistry(XmanBaseInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<XmanBaseInfo> mergePatientRegistry(String newPatientId, String oldPatientId) {
        return null;
    }

    @Override
    public ServiceResult patientRegistryQuery(String message) {
        return null;
    }
}
