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
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
public interface IOrgBaseInfoMapper extends IServiceMapper<OrgBaseInfo> {

    /**
     * 新增第三方机构注�
     *
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    OrgBaseInfo addOrganization(OrgBaseInfo orgBaseInfo) throws Exception;

    /**
     * 第三方机构信息更�
     *
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    OrgBaseInfo updateOrganization(OrgBaseInfo orgBaseInfo) throws Exception;

    /**
     * 第三方机构信息查�
     *
     * @param map
     * @return
     * @throws Exception
     */
    OrgBaseInfo organizationDetailQuery(Map<String, Object> map) throws Exception;

    /**
     * 第三方机构信息删�
     *
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    boolean organizationDelete(OrgBaseInfo orgBaseInfo) throws Exception;



    /**
     * 查询第三方机构列表，内部使用
     *
     * @param page
     * @param message
     * @return
     * @throws Exception
     */
    PageList<OrgBaseInfo> organizationListQuery(QueryPage page, String message) throws Exception;


    /**
     * 新增卫生管理机构
     * @param entity
     * @return
     * @throws Exception
     */
    int add(OrgBaseInfo entity)throws Exception;

    /**
     * 卫生管理机构信息更新
     * @param orgBaseInfo
     * @return
     * @throws Exception
     */
    boolean UpdateOrgBaseInfo(OrgBaseInfo orgBaseInfo) throws Exception;

    /**
     * 3）卫生管理机构信息查询
     * @param code
     * @param deptName
     * @return
     * @throws Exception
     */
    List<OrgBaseInfo> orgBaseInfoDetailQuery(String code, String deptName) throws Exception;

    /**
     * 4）卫生机构（科室）信息删除
     * @param id
     * @return
     * @throws Exception
     */
    boolean OrgabaseInfoDelete(String id) throws Exception;

    /**
     * 5）医疗卫生机构（科室）信息删除
     * @param code
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<OrgBaseInfo> orgBaseInfoPageListQuery(String code, String key, QueryPage page) throws Exception;

}