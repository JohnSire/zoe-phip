package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("OrganizationRegisterIn")
@Service(interfaceClass = IOrganizationRegisterIn.class, proxy = "sdpf",protocol = {"dubbo"},dynamic = true)
public class OrganizationRegisterInImpl implements IOrganizationRegisterIn {
    @Override
    public ServiceResultT<OrgDeptInfo> addOrganization(OrgDeptInfo orgDeptInfo) {
        return null;
    }

    @Override
    public ServiceResultT<OrgDeptInfo> updateOrganization(OrgDeptInfo orgDeptInfo) {
        return null;
    }

    @Override
    public ServiceResult organizationDetailQuery(String orgId) {
        return null;
    }
}
