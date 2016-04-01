/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.IMyMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.model.sm.UserCompetence;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IUserCompetenceMapper extends IMyMapper<UserCompetence> {

    boolean saveList(String competenceCategoryId, List<UserCompetence> list) throws Exception;

    PageList<SystemUser> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page);

    List<String> getUserCompetenceIdList(String categoryId);

    boolean cancelUserCompetence(String id);

    boolean checkExists(String categoryId, String userId);


    int checkExists(Map map);

    List<String> getCategoriesByUserId(String userId);

    List<SystemUser> getUserListByCompetenceCategory(Map map);
}