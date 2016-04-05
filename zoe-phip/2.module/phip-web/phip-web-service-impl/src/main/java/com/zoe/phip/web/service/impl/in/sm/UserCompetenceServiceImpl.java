
package com.zoe.phip.web.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.infrastructure.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.infrastructure.util.SqlHelper;
import com.zoe.phip.web.dao.sm.IUserCompetenceMapper;
import com.zoe.phip.web.model.sm.SystemUser;
import com.zoe.phip.web.model.sm.UserCompetence;
import com.zoe.phip.web.service.sm.IUserCompetenceService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Repository("UserCompetenceService")
@Service(interfaceClass = IUserCompetenceService.class, proxy = "sdpf", dynamic = true)
public class UserCompetenceServiceImpl extends BaseInServiceImpl<UserCompetence, IUserCompetenceMapper> implements IUserCompetenceMapper {

    @Override
    public boolean saveList(String competenceCategoryId, List<UserCompetence> list) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(competenceCategoryId))
            throw new BusinessException("权限分类不能为空!");
        //1 根据权限类型ID，查询出该类型下的用户
        List<String> existsUser = this.getUserCompetenceIdList(competenceCategoryId);
        if (existsUser != null && existsUser.size() > 0) {
            List<String> deleteUserIdStr = new ArrayList<>();
            List<UserCompetence> updateUserComList = new ArrayList<>();
            //2.1.1、 将新增的用户权限列表存在字典当中
            Map<String, UserCompetence> addCache = list.stream()
                    .collect(Collectors.toMap(UserCompetence::getFkUserId, (p) -> p));
            existsUser.stream().filter(item -> !StringUtil.isNullOrWhiteSpace(item)).forEach(item -> {
                if (addCache.containsKey(item)) {
                    updateUserComList.add(addCache.get(item));
                    addCache.remove(item);
                } else {
                    deleteUserIdStr.add(item);
                }
            });
            //经过 2.1.2 过滤后，新增的用户权限列表是要添加的列表
            if (addCache.size() > 0) {
                List<UserCompetence> addUserList = new ArrayList<>(addCache.values());
                addCache.clear();//清空缓存

                addList(addUserList);
            }
            if (deleteUserIdStr.size() > 0) {
                for (String userId : deleteUserIdStr) {
                    Delete(userId, competenceCategoryId);
                }
            }
            return true;
        } else {
            return getMapper().addList(list) > 0;
        }
    }

    @Override
    public PageList<SystemUser> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page) {
        PageList<SystemUser> pageList = new PageList<>();
        SqlHelper.startPage(page);
        Map map = MapUtil.createMap(m -> {
            m.put("C_ID", categoryId);
            m.put("KEY", key);
        });
        List<SystemUser> results = getMapper().getUserListByCompetenceCategory(map);
        PageInfo<SystemUser> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        map.clear();
        map = null;
        return pageList;
    }

    @Override
    public boolean cancelUserCompetence(String id) {
        return getMapper().deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean checkExists(String categoryId, String userId) {
        Map map = MapUtil.createMap(m -> {
            m.put("CATEGORY_ID", categoryId);
            m.put("USER_ID", userId);
        });
        Integer integer = getMapper().checkExists(map);
        map.clear();
        map = null;
        return integer > 0;
    }

    @Override
    public int checkExists(Map map) {
        return getMapper().checkExists(map);
    }

    @Override
    public List<String> getUserCompetenceIdList(String categoryId) {
        Example example = new Example(UserCompetence.class);
        example.createCriteria().andEqualTo("fkCompetenceCategoryId", categoryId);
        List<UserCompetence> list = getMapper().selectByExample(example);
        List<String> result = new ArrayList<>();
        list.forEach(v -> result.add(v.getFkUserId()));
        return result;
    }

    @Override
    public List<String> getCategoriesByUserId(String userId) {
        List<String> result = new ArrayList<>();
        Example example = new Example(UserCompetence.class);
        example.createCriteria().andEqualTo("fkUserId", userId);
        getMapper().selectByExample(example).forEach(v -> {
            if (!result.contains(v.getFkCompetenceCategoryId())) {
                result.add(v.getFkCompetenceCategoryId());
            }
        });
        return result;
    }

    @Override
    public List<SystemUser> getUserListByCompetenceCategory(Map map) {
        return getMapper().getUserListByCompetenceCategory(map);
    }

    private boolean Delete(String userId, String catalogId) {
        Example e = new Example(UserCompetence.class);
        e.createCriteria().andEqualTo("fkUserId", userId).andEqualTo("fkCompetenceCategoryId", catalogId);
        return getMapper().deleteByExample(e) > 0;
    }
}