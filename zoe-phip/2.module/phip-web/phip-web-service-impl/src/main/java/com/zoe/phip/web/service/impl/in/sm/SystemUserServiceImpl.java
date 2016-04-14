/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.security.SystemCredential;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.entity.LoginCredentials;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sm.ISystemUserMapper;
import com.zoe.phip.web.model.sm.SystemUser;
import com.zoe.phip.web.service.sm.ISystemUserService;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Repository("SystemUserService")
@Service(interfaceClass = ISystemUserService.class, proxy = "sdpf", dynamic = true)
public class SystemUserServiceImpl extends BaseInServiceImpl<SystemUser, ISystemUserMapper> implements ISystemUserMapper {


    @Override
    public List<SystemUser> getUserList(Map<String, Object> args) {
        return getMapper().getUserList(args);
    }

    @Override
    @ErrorMessage(code="001",message = "用户名错�")
    @ErrorMessage(code="002",message = "用户不可�")
    @ErrorMessage(code="003",message = "密码错误!")
    public LoginCredentials login(String loginName, String passWord, int expiresTime) throws Exception {

        List<SystemUser> list = getUserByLoginName(loginName);
        if (list == null || list.size() == 0) {
            throw new BusinessException("001");
        }
        SystemUser user = list.get(0);
        if (user.getState() == 0) {
            throw new BusinessException("002");
        }
        String psd = createPassword(user.getLoginName(), passWord);
        if (!psd.equals(user.getPassword())) {
            throw new BusinessException("003");
        }
        LoginCredentials credentials = createLoginCredentials(user.getId(), user.getName(), expiresTime);
        return credentials;
    }

    @Override
    @ErrorMessage(code = "004",message = "未找到该用户!")
    @ErrorMessage(code = "005",message = "旧密码错!")
    public int updatePassword(String id, String oldPwd, String newPwd) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("004");
        }
        String oldPassword = createPassword(user.getLoginName(), oldPwd);
        if (!user.getPassword().equals(oldPassword)) {
            throw new BusinessException("005");
        }
        user.setPassword(createPassword(user.getLoginName(), newPwd));
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    @ErrorMessage(code = "006",message = "未找到该用户!")
    public int resetPassword(String id, String newPwd) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("006");
        }
        user.setPassword(createPassword(user.getLoginName(), newPwd));
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    @ErrorMessage(code = "007",message = "未找到该用户!")
    public int updateState(String id, int state) throws Exception {
        SystemUser user = getMapper().selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("007");
        }
        user.setState(state);
        user.setModifyAt(new Date());
        return getMapper().updateByPrimaryKeySelective(user);
    }

    @Override
    public PageList<SystemUser> getUserList(Integer state, String key, QueryPage queryPage) throws Exception {
        PageList<SystemUser> pageList = new PageList<SystemUser>();
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        if (state != null) {
            paras.put("state", state);
        }
//        SqlHelper.setOrder(paras,queryPage);
        List<SystemUser> results = getMapper().getUserList(paras);
        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    @ErrorMessage(code = "008",message = "已存在登录名({0})的用户！")
    public int add( @Validated SystemUser entity) throws Exception {
       //判断是否存在用户
        List<SystemUser> list = getUserByLoginName(entity.getLoginName());
        if (list != null && list.size() > 0) {
            throw new BusinessException("008", entity.getLoginName());
        }
        String password = createPassword(entity.getLoginName(), entity.getPassword());
        entity.setPassword(password);
        return getMapper().insertSelective(entity);
    }

    @Override
    @ErrorMessage(code = "009",message = "已存在登录名({0})的用�")
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
            throw new BusinessException("009", loginNames.toString());
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

    private LoginCredentials createLoginCredentials(String userId, String userName, int expiresTime) {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setUserId(userId);
        credentials.setUserName(userName);
        String credential = SystemCredential.createCredential(userId, userName, expiresTime);
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