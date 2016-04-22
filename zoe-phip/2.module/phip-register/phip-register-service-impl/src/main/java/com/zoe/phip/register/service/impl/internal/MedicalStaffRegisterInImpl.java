package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.internal.IMedicalStaffRegisterIn;

/**
 * Created by zengjiyang on 2016/4/22.
 */
public class MedicalStaffRegisterInImpl implements IMedicalStaffRegisterIn {
    @Override
    public ServiceResultT<MedicalStaffInfo> addProvider(MedicalStaffInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> updateProvider(MedicalStaffInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> providerDetailsQuery(String staffId) {
        return null;
    }
}
