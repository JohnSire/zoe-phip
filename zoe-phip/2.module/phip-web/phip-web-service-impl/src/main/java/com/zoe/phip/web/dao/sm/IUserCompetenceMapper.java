/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sm.SystemUser;
import com.zoe.phip.web.model.sm.UserCompetence;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IUserCompetenceMapper extends IMyMapper<UserCompetence> {

    /**
     * 添加用户权限
     *
     * @param competenceCategoryId
     * @param list
     * @return
     * @throws Exception
     */
    boolean saveList(String competenceCategoryId, List<UserCompetence> list) throws Exception;

    /**
     * 通过权限类别分页获取用户列表
     *
     * @param categoryId
     * @param key
     * @param page
     * @return
     */
    PageList<SystemUser> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page);

    /**
     * 获取用户权限列表
     *
     * @param categoryId
     * @return
     */
    List<String> getUserCompetenceIdList(String categoryId);

    /**
     * 取消用户权限
     *
     * @param id
     * @return
     */
    boolean cancelUserCompetence(String id);

    /**
     * 检查该用户权限是否存在
     *
     * @param categoryId
     * @param userId
     * @return
     */
    boolean checkExists(String categoryId, String userId);

    /**
     * 检查该用户权限是否存在
     *
     * @param map
     * @return
     */
    int checkExists(Map map);

    /**
     * 通过用户ID获取该用户的所有权限类别
     *
     * @param userId
     * @return
     */
    List<String> getCategoriesByUserId(String userId);

    /**
     * 通过权限类别获取用户列表
     *
     * @param map
     * @return
     */
    List<SystemUser> getUserListByCompetenceCategory(Map map);
}