package com.zoe.phip.web.service.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sm.SystemUser;
import com.zoe.phip.web.model.sm.UserCompetence;


import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IUserCompetenceService extends IBaseInService<UserCompetence> {

    /**
     * 添加用户权限
     *
     * @param systemData
     * @param competenceCategoryId
     * @param list
     * @return
     */
    ServiceResult saveList(SystemData systemData, String competenceCategoryId, List<UserCompetence> list);

    /**
     * 通过权限类别获取用户列表
     *
     * @param systemData
     * @param categoryId
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<SystemUser>> getUserListByCompetenceCategory(SystemData systemData, String categoryId, String key, QueryPage page);

    /**
     * 获取用户权限列表
     *
     * @param systemData
     * @param categoryId
     * @return
     */
    ServiceResultT<List<String>> getUserCompetenceIdList(SystemData systemData, String categoryId);

    /**
     * 取消用户权限
     *
     * @param id
     * @return
     */
    ServiceResult canceUserCompetence(String id);

    /**
     * 检查该用户权限是否存在
     *
     * @param systemData
     * @param categoryId
     * @param userId
     * @return
     */
    ServiceResult checkExists(SystemData systemData, String categoryId, String userId);

    /**
     * 通过用户ID获取该用户的所有权限类别
     *
     * @param systemData
     * @param userId
     * @return
     */
    ServiceResultT<List<String>> getCategoriesByUserId(SystemData systemData, String userId);
}