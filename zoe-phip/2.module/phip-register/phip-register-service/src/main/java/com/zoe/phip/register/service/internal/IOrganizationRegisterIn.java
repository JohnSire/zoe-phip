package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.NationalStandards;
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
     * 查询机构类别列表，用于构造树
     * @param systemData
     * @param codeSystem  NationalStandards表里的字段：code_system
     * @return
     */
    ServiceResultT<NationalStandards> dictItemListQueryByCodeSystem(SystemData systemData,String codeSystem);

    /**
     * 查询机构列表，内部使用
     * @param deptTypeCode
     * @param key
     * @param page
     * @param type 1:是指点击机构分类，全部数据，2是指单个分类数据
     * @return
     */
    ServiceResultT<PageList<OrgDeptInfo>> organizationListQuery(SystemData systemData,String type, String deptTypeCode, String key, QueryPage page);

    /**
     *
     * @param type：1:是指点击科室分类，全部数据，2是指单个分类数据
     * @param deptTypeCode
     * @param deptParentCode
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<OrgDeptInfo>> DepartmentListQuery(String type, String deptTypeCode,String deptParentCode ,String key, QueryPage page);



    ServiceResultT<PageList<OrgDeptInfo>>   orgListQuery(SystemData systemData);



    ServiceResultT<PageList<DictItem>>  getDictItemPage(SystemData systemData,String codeSystem,String key,QueryPage page);
    
}
