/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.service.in.sm;

import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
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
    ServiceResultT<LoginCredentials> login(String loginName, String passWord,int expiresTime);
}