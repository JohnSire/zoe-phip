package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.model.sm.UserCompetence;
import com.zoe.phip.service.in.IBaseInService;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IUserCompetenceService extends IBaseInService<UserCompetence> {

    ServiceResult saveList(SystemData systemData, String competenceCategoryId, List<UserCompetence> list);

    ServiceResultT<PageList<SystemUser>> getUserListByCompetenceCategory(SystemData systemData, String categoryId, String key, QueryPage page);

    ServiceResultT<List<String>> getUserCompetenceIdList(SystemData systemData, String categoryId);

    ServiceResult canceUserCompetence(String id);

    ServiceResult checkExists(SystemData systemData, String categoryId, String userId);

    ServiceResultT<List<String>> getCategoriesByUserId(SystemData systemData, String userId);
}