package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.service.IAreaRegister;
import com.zoe.phip.register.service.IMedicalStaffRegister;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("MedicalStaffRegister")
@Service(interfaceClass = IMedicalStaffRegister.class, proxy = "sdpf", dynamic = true)
public class MedicalStaffRegisterImpl implements IMedicalStaffRegister {
    @Override
    public String addProvider(String message) {
        return null;
    }

    @Override
    public String updateProvider(String message) {
        return null;
    }

    @Override
    public String providerDetailsQuery(String message) {
        return null;
    }
}
