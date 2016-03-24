/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.MenuTreeNode;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.MenuDataService;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-21
 */
//@Service("menuDataService")
@com.alibaba.dubbo.config.annotation.Service
@Component
public class MenuDataServiceImpl extends BaseInServiceImpl<MenuData> implements MenuDataService {


    @Override
    public ServiceResult add(MenuData entity) {

        return super.add(entity);
    }

    @Override
    public ServiceResultT<PageList<MenuData>> getMenuPages(int state, String key, QueryPage page) {


        return null;
    }

    @Override
    public ServiceResultT<List<MenuData>> getMenus(String key) {
        SafeExecuteUtil<List<MenuData>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            Example example = new Example(MenuData.class);
            Example.Criteria criteria = example.createCriteria().andLike("name", key);
            example.or(criteria);
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResultT<List<MenuData>> getMenuByCode(String code) {
        SafeExecuteUtil<List<MenuData>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            Example example = new Example(MenuData.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("code",code);
            example.or(criteria);
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResultT<List<MenuData>> getChildMenus(String parentMenuId) {
        SafeExecuteUtil<List<MenuData>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
//            MenuData menuData = new MenuData();
            Example example = new Example(MenuData.class);
//            example.createCriteria().andEqualTo("id",parentMenuId);
//            List<MenuData> menuDataList = getMapper().selectByExample(example);
//            menuData.setFkParentMenuId(menuDataList.get(0).getFkParentMenuId());
            Example.Criteria criteria = example.createCriteria().andEqualTo("fkParentMenuId", parentMenuId);
            example.or(criteria);
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResult updateState(String id, int state) {
        return null;
    }

    @Override
    public ServiceResultT<List<MenuTreeNode>> getCompentenceMenuByUser(SystemData systemData, String userId) {

        return null;
    }

    @Override
    public ServiceResultT<List<MenuData>> getCompentenceMenuByUser(String userId) {
        SafeExecuteUtil<List<MenuData>> safeExcute = new SafeExecuteUtil<>();
        return safeExcute.executeT(() ->
        {
            Example example = new Example(MenuData.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("USER_ID", userId);
            example.or(criteria);
            return getMapper().selectByExample(example);
        });
    }


}
