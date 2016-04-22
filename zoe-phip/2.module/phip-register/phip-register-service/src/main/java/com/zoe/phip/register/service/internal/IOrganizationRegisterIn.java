package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.OrgDeptInfo;

/**医疗机构注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IOrganizationRegisterIn {

    /**
     *	新增医疗卫生机构注册
     * @param orgDeptInfo
     * @return
     */
    ServiceResultT<OrgDeptInfo> addOrganization(OrgDeptInfo orgDeptInfo);

    /**
     *	医疗卫生机构信息更新
     * @param orgDeptInfo
     * @return
     */
    ServiceResultT<OrgDeptInfo> updateOrganization(OrgDeptInfo orgDeptInfo);

    /**
     * 	医疗卫生机构信息查询
     * @param orgId
     * @return
     */
    ServiceResult organizationDetailQuery(String orgId);
}
