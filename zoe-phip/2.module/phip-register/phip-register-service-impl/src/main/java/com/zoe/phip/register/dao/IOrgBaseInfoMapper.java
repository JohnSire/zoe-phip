/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.OrgBaseInfo;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IOrgBaseInfoMapper extends IServiceMapper<OrgBaseInfo> {

    /**
     * 新增卫生管理机构
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    boolean AddOrganization(OrgBaseInfo orgBaseInfo) throws Exception ;

    /**
     * 卫生管理机构信息更新
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    boolean UpdateOrganization(OrgBaseInfo orgBaseInfo) throws Exception;

    /**
     * 3）卫生管理机构信息查询
     * @param code
     * @param deptName
     * @return
     * @throws Exception
     */
    List<OrgBaseInfo> OrganizationDetailQuery(String code,String deptName) throws Exception;

    /**
     * 机构删除
     * @param id
     * @return
     * @throws Exception
     */
    boolean OrganizationDelete(String id) throws Exception;

    /**
     * 查询卫生管理机构列表
     * @param code
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<OrgBaseInfo>  OrganizationListQuery(String code, String key,QueryPage page) throws Exception;

}