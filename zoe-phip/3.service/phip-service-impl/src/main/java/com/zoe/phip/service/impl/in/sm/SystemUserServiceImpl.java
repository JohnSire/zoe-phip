/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import com.zoe.phip.model.sm.LoginCredentials;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.SafeExecuteUtil;
import com.zoe.phip.service.in.sm.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Service("SystemUserService")
public class SystemUserServiceImpl extends BaseInServiceImpl<SystemUser> implements SystemUserService {

    @Override
    public ServiceResultT<LoginCredentials> login(String loginName, String passWord, int expiresTime) {
        SafeExecuteUtil<LoginCredentials> safeExecute = new SafeExecuteUtil<LoginCredentials>();
        return safeExecute.executeT(() -> {
            Example example = new Example(SystemUser.class);
            example.createCriteria().andEqualTo("loginName", loginName);
            List<SystemUser> list = getMapper().selectByExample(example);
            SystemUser user = list.get(0);
            if (user == null) {
                throw new Exception("用户未找到!");
            }
            if (user.getState() == 0) {
                throw new Exception("用户不可用!");
            }
            String psd = createPassword(user.getLoginName(), passWord);
            if (!psd.equals(user.getPassword())) {
                throw new Exception("用户密码错误!");
            }

            LoginCredentials credentials = createLoginCredentials(user.getId(),user.getName());
            return credentials;
        });
    }

    @Override
    public ServiceResult add(SystemUser entity) {
        String password = createPassword(entity.getLoginName(), entity.getPassword());
        entity.setPassword(password);
        return super.add(entity);
    }

    @Override
    public ServiceResult addList(List<SystemUser> entities) {
        entities.forEach(e -> {
            String password = createPassword(e.getLoginName(), e.getPassword());
            e.setPassword(password);
        });
        return super.addList(entities);
    }

    private String createPassword(String loginName, String password) {
        return StringUtil.toMD5(String.join("zoe", loginName, StringUtil.toMD5(password), loginName));
    }

    private LoginCredentials createLoginCredentials(String userId, String userName) {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setUserId(userId);
        credentials.setUserName(userName);
        //todo 设置LoginCredentials
        credentials.setCredential("");
        return credentials;
    }
}