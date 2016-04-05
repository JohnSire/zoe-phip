/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.infrastructure.dao.IMyMapper;
import com.zoe.phip.module.infrastructure.entity.LoginCredentials;
import com.zoe.phip.web.model.sm.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
public interface ISystemUserMapper extends IMyMapper<SystemUser> {
    List<SystemUser> getUserList(Map<String, Object> args);

    /**
     * 用户登录操作
     *
     * @param loginName
     * @param passWord
     * @param expiresTime session过期时间 毫秒
     * @return
     */

    LoginCredentials login(String loginName, String passWord, int expiresTime) throws Exception;

    /**
     * 修改密码
     *
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    int updatePassword(String id, String oldPwd, String newPwd) throws Exception;

    /**
     * 重设密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    int resetPassword(String id, String newPwd) throws Exception;

    /**
     * 更新用户状态
     *
     * @param id
     * @param state
     * @return
     */
    int updateState(String id, int state) throws Exception;

    /**
     * 用户查询
     *
     * @param state
     * @param key
     * @param page
     * @return
     */
    PageList<SystemUser> getUserList(Integer state, String key, QueryPage page) throws Exception;

}