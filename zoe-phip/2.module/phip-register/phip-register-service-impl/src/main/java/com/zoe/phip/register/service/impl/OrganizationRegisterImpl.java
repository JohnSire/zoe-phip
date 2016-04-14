package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.service.IAreaRegister;
import com.zoe.phip.register.service.IOrganizationRegister;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("OrganizationRegister")
@Service(interfaceClass = IOrganizationRegister.class, proxy = "sdpf", dynamic = true)
public class OrganizationRegisterImpl implements IOrganizationRegister {
    @Override
    public String addOrganization(String message) {
        return null;
    }

    @Override
    public String updateOrganization(String message) {
        return null;
    }

    @Override
    public String organizationDetailQuery(String message) {
        return null;
    }
}
