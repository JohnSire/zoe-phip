/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.OrgDeptInfo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IOrgDeptInfoMapper extends IServiceMapper<OrgDeptInfo> {
    OrgDeptInfo getOrgDeptInfo(Map<String, Object> map);

    PageList<OrgDeptInfo> organizationListQuery(String deptTypeCode, String key, QueryPage page);

    DictCatalog dictItemListQuery(String code ,String fkCatalogCode);

    boolean organizationDelete(String id);


    List<OrgDeptInfo> getOrgDeptInfoList(Map<String, Object> paras);
    List<OrgDeptInfo> getOrgDeptInfoListByType(Map<String, Object> paras) throws Exception ;
}