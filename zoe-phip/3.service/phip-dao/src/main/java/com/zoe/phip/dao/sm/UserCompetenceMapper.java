/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.model.sm.UserCompetence;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface UserCompetenceMapper extends MyMapper<UserCompetence> {

    boolean saveList(String competenceCategoryId, List<UserCompetence> list) throws Exception;

    PageList<UserCompetence> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page);

    List<String> getUserCompetenceIdList(String categoryId);

    boolean canceUserCompetence(String id);

    boolean checkExists(String categoryId, String userId);

    List<String> getCategoriesByUserId(String userId);
}