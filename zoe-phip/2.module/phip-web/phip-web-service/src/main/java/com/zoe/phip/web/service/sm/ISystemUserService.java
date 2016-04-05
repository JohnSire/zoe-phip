/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.web.service.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.infrastructure.entity.LoginCredentials;
import com.zoe.phip.module.infrastructure.service.in.IBaseInService;
import com.zoe.phip.web.model.sm.SystemUser;


/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
public interface ISystemUserService extends IBaseInService<SystemUser> {

    /**
     * 用户登录操作
     *
     * @param loginName
     * @param passWord
     * @param expiresTime session过期时间 毫秒
     * @return
     */

    ServiceResultT<LoginCredentials> login(String loginName, String passWord, int expiresTime);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    ServiceResult updatePassword(SystemData systemData, String id, String oldPwd, String newPwd);

    /**
     * 重设密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    ServiceResult resetPassword(SystemData systemData, String id, String newPwd);

    /**
     * 更新用户状态
     *
     * @param id
     * @param state
     * @return
     */
    ServiceResult updateState(SystemData systemData, String id, int state);

    /**
     * 用户查询
     * @param systemData
     * @param state
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<SystemUser>> getUserList(SystemData systemData, Integer state, String key, QueryPage page);
}