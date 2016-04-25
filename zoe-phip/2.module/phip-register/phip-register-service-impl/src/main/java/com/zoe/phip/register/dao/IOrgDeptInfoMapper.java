/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.OrgDeptInfo;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IOrgDeptInfoMapper extends IServiceMapper<OrgDeptInfo> {
  OrgDeptInfo getOrgDeptInfo(Map<String,Object> map);


}