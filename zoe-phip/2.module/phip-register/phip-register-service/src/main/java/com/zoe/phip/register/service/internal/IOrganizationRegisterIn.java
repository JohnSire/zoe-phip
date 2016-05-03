package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.OrgDeptInfo;

import java.util.Map;

/**医疗机构注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IOrganizationRegisterIn extends IBaseInService<OrgDeptInfo> {

    /**
     *	新增医疗卫生机构注册
     * @param orgDeptInfo
     * @return
     */
    ServiceResultT<OrgDeptInfo> addOrganization(SystemData systemData,OrgDeptInfo orgDeptInfo);

    /**
     *	医疗卫生机构信息更新
     * @param orgDeptInfo
     * @return
     */
    ServiceResultT<OrgDeptInfo> updateOrg(SystemData systemData,OrgDeptInfo orgDeptInfo);

    /**
     * 	医疗卫生机构信息查询
     * @param map
     * @return
     */
    ServiceResultT<OrgDeptInfo> organizationDetailQuery(Map<String,Object> map);

    /**
     * 1)医疗卫生机构（科室）信息删除
     * @param id
     * @return
     */


    ServiceResult  organizationDelete(SystemData systemData, String id);

    /**
     * 查询医疗机构（科室）类别字典列表，用于构造树
     * @param
     * @return
     */
    ServiceResultT<DictCatalog> dictItemListQuery(SystemData systemData);

    /**
     * 查询医疗机构（科室）列表，内部使用
     * @param deptTypeCode
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<OrgDeptInfo>> organizationListQuery(SystemData systemData,String type, String deptTypeCode, String key, QueryPage page);

    ServiceResultT<PageList<OrgDeptInfo>>   getDeptInfoListByType(SystemData systemData,String type);

    ServiceResultT<PageList<OrgDeptInfo>>   orgListQuery(SystemData systemData);
    
}
