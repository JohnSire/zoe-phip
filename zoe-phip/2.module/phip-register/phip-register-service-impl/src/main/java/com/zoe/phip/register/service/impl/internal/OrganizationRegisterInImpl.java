package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;

/**
 * Created by zengjiyang on 2016/4/22.
 */
public class OrganizationRegisterInImpl implements IOrganizationRegisterIn {
    @Override
    public ServiceResultT<OrgDeptInfo> addOrganization(OrgDeptInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<OrgDeptInfo> updateOrganization(OrgDeptInfo message) {
        return null;
    }

    @Override
    public ServiceResult organizationDetailQuery(String orgId) {
        return null;
    }
}
