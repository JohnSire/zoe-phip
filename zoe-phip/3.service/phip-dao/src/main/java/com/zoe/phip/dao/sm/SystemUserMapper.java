/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.model.sm.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
public interface SystemUserMapper extends MyMapper<SystemUser> {
    List<SystemUser> getUserList(Map<String,Object> args);
}