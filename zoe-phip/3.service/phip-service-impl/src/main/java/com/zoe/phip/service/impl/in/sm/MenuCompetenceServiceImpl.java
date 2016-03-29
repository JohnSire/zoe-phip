package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.dao.sm.MenuCompetenceMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.in.sm.MenuCompetenceService;
import org.springframework.stereotype.Repository;
import com.zoe.phip.model.sm.MenuCompetence;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Repository("menuCompetenceService")
@Service(interfaceClass = MenuCompetenceService.class, proxy = "sdpf", dynamic = true)
public class MenuCompetenceServiceImpl extends BaseInServiceImpl<MenuCompetence, MenuCompetenceMapper> implements MenuCompetenceMapper {

    @Override
    public boolean saveList(String competenceCategoryId, List<MenuCompetence> list) {
        return false;
    }

    @Override
    public PageList<MenuData> getMenuListByCompetenceCategory(String categoryId, String key, QueryPage page) {
        return null;
    }

    @Override
    public List<String> getMenuCompetenceIdList(String categoryId) {
        return null;
    }

    @Override
    public boolean cancelMenuCompetence(String id) {
        return false;
    }

    @Override
    public boolean checkExists(String categoryId, String menuId) {
        return false;
    }

    @Override
    public boolean checkExists(String categoryId, int menuCode) {
        return false;
    }

    @Override
    public List<String> getCategoriesByMenuId(String menuId) {
        return null;
    }
}