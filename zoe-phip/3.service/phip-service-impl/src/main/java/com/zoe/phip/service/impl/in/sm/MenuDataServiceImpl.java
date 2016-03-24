/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.dao.sm.MenuDataMapper;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.MenuTreeNode;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.MenuDataService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-21
 */
@Repository("menuDataService")
@Service
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
    public ServiceResultT<List<MenuData>> getCompetenceMenuByUser(SystemData systemData, String userId) {
        SafeExecuteUtil<List<MenuData>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(()->
        {
            List<MenuData> menus=((MenuDataMapper)getMapper()).GetCompetenceMenuByUser(userId);
            if(menus.size()==0)
            {
                throw new BusinessException("还没有为该用户分配菜单!");
            }
            Map<String,List<MenuData>> map=new HashMap<>();
            menus.forEach(m->{
                if(map.containsKey(m.getFkParentMenuId())){
                    map.get(m.getFkParentMenuId()).add(m);
                }else {
                    List<MenuData> menuList=new ArrayList<MenuData>();
                    menuList.add(m);
                    map.put(m.getFkParentMenuId(),menuList);
                }
            });
            List<MenuData> topMenus= map.get("0");
            List<MenuData> data=new ArrayList<>();
            topMenus.forEach(t->{
                findChildNodes(t,map);
                data.add(t);
            });

            map.clear();
            System.out.println(menus.size());
            return data;
        });
    }

    private void findChildNodes(MenuData node, Map<String,List<MenuData>>  cache)
    {
        if (!cache.containsKey(node.getId()))
            return;
        List<MenuData> menus = cache.get(node.getId());
        node.setChildren(menus);
        if(node.getChildren()!=null){
            node.getChildren().forEach(n->{
                findChildNodes(n,cache);
            });
        }
    }

    @Override
    public ServiceResultT<List<MenuData>> getCompetenceMenuByUser(String userId) {
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
