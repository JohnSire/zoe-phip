package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.OrgBaseInfo;

import java.util.List;

/**卫生管理机构
 * Created by hyf on 2016/4/25.
 */
public interface IOrgBaseInfoService extends IBaseInService<OrgBaseInfo> {


    /**
     *新增卫生管理机构
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    ServiceResult AddOrganization(OrgBaseInfo orgBaseInfo) throws Exception ;

    /**
     *卫生管理机构信息更新
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    ServiceResult UpdateOrganization(OrgBaseInfo orgBaseInfo) throws Exception;


    /**
     *3）卫生管理机构信息查询
     * @param code
     * @param deptName
     * @return
     * @throws Exception
     */
    ServiceResultT<List<OrgBaseInfo>> OrganizationDetailQuery(String code,String deptName) throws Exception;

    /**
     *机构删除
     * @param id
     * @return
     * @throws Exception
     */
    ServiceResult OrganizationDelete(String id) throws Exception;


    /***
     *查询卫生管理机构列表
     * @param code
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    ServiceResultT<PageList<OrgBaseInfo>>  OrganizationListQuery(String code, String key,QueryPage page) throws Exception;



}
