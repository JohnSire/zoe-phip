/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.SystemUserMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.security.SystemCredential;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.LoginCredentials;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.SqlHelper;
import com.zoe.phip.service.in.sm.SystemUserService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Repository("SystemUserService")
@Service(interfaceClass = SystemUserService.class, proxy = "sdpf", dynamic = true)
public class SystemUserServiceImpl extends BaseInServiceImpl<SystemUser, SystemUserMapper> implements SystemUserMapper {


    @Override
    public List<SystemUser> getUserList(Map<String, Object> args) {
        return getMapper().getUserList(args);
    }

    @Override
    public LoginCredentials login(String loginName, String passWord, int expiresTime) throws Exception {

        List<SystemUser> list = getUserByLoginName(loginName);
        if (list == null || list.size() == 0) {
            throw new BusinessException("用户名错误!");
        }
        SystemUser user = list.get(0);
        if (user.getState() == 0) {
            throw new BusinessException("用户不可用");
        }
        String psd = createPassword(user.getLoginName(), passWord);
        if (!psd.equals(user.getPassword())) {
            throw new BusinessException("密码错误!");
        }
        LoginCredentials credentials = createLoginCredentials(user.getId(), user.getName(),expiresTime);
        return credentials;
    }

    @Override
    public int updatePassword(String id, String oldPwd, String newPwd) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("未找到该用户!");
        }
        String oldPassword = createPassword(user.getLoginName(), oldPwd);
        if (!user.getPassword().equals(oldPassword)) {
            throw new BusinessException("旧密码错");
        }
        user.setPassword(createPassword(user.getLoginName(), newPwd));
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    public int resetPassword(String id, String newPwd) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("未找到该用户!");
        }
        user.setPassword(createPassword(user.getLoginName(), newPwd));
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateState(String id, int state) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("未找到该用户!");
        }
        user.setState(state);
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    public PageList<SystemUser> getUserList(Integer state, String key, QueryPage queryPage) throws Exception {
        PageList<SystemUser> pageList = new PageList<SystemUser>();
        Example example = new Example(SystemUser.class);
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        if (state != null) {
            paras.put("state", state);
        }
        SqlHelper.setOrder(paras,queryPage);

        List<SystemUser> results = getMapper().getUserList(paras);
        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public int add(SystemUser entity) throws Exception {
        //判断是否存在用户
        List<SystemUser> list = getUserByLoginName(entity.getLoginName());
        if (list != null && list.size() > 0) {
            throw new BusinessException("已存在登录名({0})的用户", entity.getLoginName());
        }
        String password = createPassword(entity.getLoginName(), entity.getPassword());
        entity.setPassword(password);
        return getMapper().insertSelective(entity);
    }

    @Override
    public int addList(List<SystemUser> entities) throws Exception {

        List<String> loginNames = new ArrayList<String>();
        entities.forEach(e -> {
            loginNames.add(e.getLoginName());
        });
        //判断是否重名
        Example example = new Example(SystemUser.class);
        example.createCriteria().andIn("loginName", loginNames);
        List<SystemUser> list = getMapper().selectByExample(example);
        if (list.size() > 0) {
            loginNames.clear();
            list.forEach(l -> {
                loginNames.add(l.getLoginName());
            });
            throw new BusinessException("已存在登录名({0})的用户", loginNames.toString());
        }
        entities.forEach(e -> {
            String password = createPassword(e.getLoginName(), e.getPassword());
            e.setPassword(password);
            e.setId(StringUtil.getUUID());
        });

        return getMapper().addList(entities);
    }

    private String createPassword(String loginName, String password) {
        return StringUtil.toMD5(String.join("zoe", loginName, StringUtil.toMD5(password), loginName));
    }

    private LoginCredentials createLoginCredentials(String userId, String userName,int expiresTime) {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setUserId(userId);
        credentials.setUserName(userName);
        String credential=SystemCredential.createCredential(userId,userName,expiresTime);
        credentials.setCredential(credential);
        return credentials;
    }

    private List<SystemUser> getUserByLoginName(String loginName) {
        Example example = new Example(SystemUser.class);
        example.createCriteria().andEqualTo("loginName", loginName);
        List<SystemUser> list = getMapper().selectByExample(example);
        return list;
    }
}