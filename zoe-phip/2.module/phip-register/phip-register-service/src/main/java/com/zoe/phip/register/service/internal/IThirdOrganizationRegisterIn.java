package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.OrgBaseInfo;

import java.util.Map;

/**
 * Created by zhanghao on 2016/4/25.
 */
public interface IThirdOrganizationRegisterIn extends IBaseInService<OrgBaseInfo> {

    /**
     * 新增第三方机构注册
     *
     * @param systemData
     * @param orgBaseInfo
     * @return
     */
    ServiceResultT<OrgBaseInfo> addOrganization(SystemData systemData, OrgBaseInfo orgBaseInfo);

    /**
     * 第三方机构信息更新
     *
     * @param systemData
     * @param orgBaseInfo
     * @return
     */
    ServiceResultT<OrgBaseInfo> updateOrganization(SystemData systemData, OrgBaseInfo orgBaseInfo);

    /**
     * 第三方机构信息查询
     *
     * @param map
     * @return
     */
    ServiceResultT<OrgBaseInfo> organizationDetailQuery(Map<String, Object> map);

    /**
     * 第三方机构信息删除
     *
     * @param systemData
     * @param orgBaseInfo
     * @return
     */
    ServiceResult organizationDelete(SystemData systemData, OrgBaseInfo orgBaseInfo);

    /**
     * 查询第三方机构列表，内部使用
     *
     * @param systemData
     * @param message
     * @return
     */
    ServiceResultT<PageList<OrgBaseInfo>> organizationListQuery(SystemData systemData, String message);
}
