/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.LoginCredentials;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.BaseInService;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
public interface SystemUserService extends BaseInService<SystemUser> {

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
    ServiceResult updatePassword(String id, String oldPwd, String newPwd);

    /**
     * 重设密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    ServiceResult resetPassword(String id, String newPwd);

    /**
     * 更新用户状态
     *
     * @param id
     * @param state
     * @return
     */
    ServiceResult updateState(String id, int state);
}