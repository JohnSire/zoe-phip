package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.OrgDeptInfo;

import java.util.List;
import java.util.Map;

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
     * @param map
     * @return
     */
    ServiceResultT<OrgDeptInfo> organizationDetailQuery(Map<String,Object> map);

    /**
     * 1)医疗卫生机构（科室）信息删除
     * @param id
     * @return
     */
    boolean organizationDelete(String id);

    /**
     * 查询医疗机构（科室）类别字典列表，用于构造树
     * @param deptParentCode
     * @return
     */
    List<OrgDeptInfo> dictItemListQuery(String deptParentCode);

    /**
     * 查询医疗机构（科室）列表，内部使用
     * @param deptParentCode
     * @param key
     * @param page
     * @return
     */
    PageList<OrgDeptInfo> organizationListQuery(String deptParentCode, String key, QueryPage page);


    
}
