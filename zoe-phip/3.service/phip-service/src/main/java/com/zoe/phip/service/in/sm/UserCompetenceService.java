package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.UserCompetence;
import com.zoe.phip.service.in.BaseInService;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface UserCompetenceService extends BaseInService<UserCompetence> {

    ServiceResult saveList(String competenceCategoryId, List<UserCompetence> list);

    ServiceResultT<PageList<UserCompetence>> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page);

    ServiceResultT<List<String>> getUserCompetenceIdList(String categoryId);

    ServiceResult canceUserCompetence(String id);

    ServiceResult checkExists(String categoryId, String userId);

    ServiceResultT<List<String>> getCategoriesByUserId(String userId);
}