package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.service.IAreaRegister;
import com.zoe.phip.register.service.IPatientRegister;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {
    @Override
    public String addPatientRegistry(String message) {
        return null;
    }

    @Override
    public String updatePatientRegistry(String message) {
        return null;
    }

    @Override
    public String mergePatientRegistry(String message) {
        return null;
    }

    @Override
    public String patientRegistryQuery(String message) {
        return null;
    }
}
