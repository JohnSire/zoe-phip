/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.service.impl.proxy.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.MenuDataMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.proxy.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.SqlHelper;
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
@Repository("MenuDataService")
@Service(interfaceClass = MenuDataService.class, proxy = "sdpf", dynamic = true)
public class MenuDataServiceImpl extends BaseInServiceImpl<MenuData> implements MenuDataMapper {
    @Override
    public PageList<MenuData> getMenuPages(String key, QueryPage page) throws Exception {
        PageList<MenuData> pageList = new PageList<MenuData>();
        Example example = new Example(MenuData.class);
        SqlHelper.startPage(page);
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            example.createCriteria().andLike("code", key);
            example.or(example.createCriteria().andLike("name", key));
        }
        List<MenuData> results = getMapper().selectByExample(example);
        PageInfo<MenuData> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<MenuData> getMenus(String key) throws Exception {
        Example example = new Example(MenuData.class);
        Example.Criteria criteria = example.createCriteria().andLike("name", key);
        example.or(criteria);
        return getMapper().selectByExample(example);
    }

    @Override
    public List<MenuData> getMenuByCode(String code) throws Exception {
        Example example = new Example(MenuData.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("code", code);
        example.or(criteria);
        return getMapper().selectByExample(example);
    }

    @Override
    public List<MenuData> getChildMenus(String parentMenuId) throws Exception {
//            MenuData menuData = new MenuData();
        Example example = new Example(MenuData.class);
//            example.createCriteria().andEqualTo("id",parentMenuId);
//            List<MenuData> menuDataList = getMapper().selectByExample(example);
//            menuData.setFkParentMenuId(menuDataList.get(0).getFkParentMenuId());
        Example.Criteria criteria = example.createCriteria().andEqualTo("fkParentMenuId", parentMenuId);
        example.or(criteria);
        return getMapper().selectByExample(example);
    }

    @Override
    public List<MenuData> getCompetenceMenuByUser(String userId) throws Exception {
        List<MenuData> menus = ((MenuDataMapper) getMapper()).getCompetenceMenuByUser(userId);
        if (menus.size() == 0) {
            throw new BusinessException("还没有为该用户分配菜单!");
        }
        Map<String, List<MenuData>> map = new HashMap<>();
        menus.forEach(m -> {
            if (map.containsKey(m.getFkParentMenuId())) {
                map.get(m.getFkParentMenuId()).add(m);
            } else {
                List<MenuData> menuList = new ArrayList<MenuData>();
                menuList.add(m);
                map.put(m.getFkParentMenuId(), menuList);
            }
        });
        List<MenuData> topMenus = map.get("0");
        List<MenuData> data = new ArrayList<>();
        topMenus.forEach(t -> {
            findChildNodes(t, map);
            data.add(t);
        });

        map.clear();
        System.out.println(menus.size());
        return data;
    }

    private void findChildNodes(MenuData node, Map<String, List<MenuData>> cache) {
        if (!cache.containsKey(node.getId()))
            return;
        List<MenuData> menus = cache.get(node.getId());
        node.setChildren(menus);
        if (node.getChildren() != null) {
            node.getChildren().forEach(n -> {
                findChildNodes(n, cache);
            });
        }
    }
}
