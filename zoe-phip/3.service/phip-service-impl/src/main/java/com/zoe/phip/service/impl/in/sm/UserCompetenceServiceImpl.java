
package com.zoe.phip.service.impl.in.sm;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zoe.phip.dao.sm.UserCompetenceMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.UserCompetence;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.UserCompetenceService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Repository("UserCompetenceService")
@Service(interfaceClass = UserCompetenceService.class, proxy = "sdpf", dynamic = true)
public class UserCompetenceServiceImpl extends BaseInServiceImpl<UserCompetence, UserCompetenceMapper> implements UserCompetenceMapper {

    @Override
    public boolean saveList(String competenceCategoryId, List<UserCompetence> list) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(competenceCategoryId))
            throw new BusinessException("权限分类不能为空!");
        return false;
    }

    @Override
    public PageList<UserCompetence> getUserListByCompetenceCategory(String categoryId, String key, QueryPage page) {
        return new PageList<>();
    }


    @Override
    public boolean canceUserCompetence(String id) {
        return false;
    }

    @Override
    public boolean checkExists(String categoryId, String userId) {
        return false;
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
        return null;
    }
}