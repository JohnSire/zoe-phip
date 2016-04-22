package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.internal.IMedicalStaffRegisterIn;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("MedicalStaffRegisterIn")
@Service(interfaceClass = IMedicalStaffRegisterIn.class, proxy = "sdpf",protocol = {"dubbo"},dynamic = true)
public class MedicalStaffRegisterInImpl implements IMedicalStaffRegisterIn {
    @Override
    public ServiceResultT<MedicalStaffInfo> addProvider(MedicalStaffInfo medicalStaffInfo) {
        return null;
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> updateProvider(MedicalStaffInfo medicalStaffInfo) {
        return null;
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> providerDetailsQuery(String staffId) {
        return null;
    }
}
