package com.zoe.phip.web.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sm.IMenuCompetenceMapper;
import com.zoe.phip.web.model.sm.MenuCompetence;
import com.zoe.phip.web.model.sm.MenuData;
import com.zoe.phip.web.service.sm.IMenuCompetenceService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Repository("menuCompetenceService")
@Service(interfaceClass = IMenuCompetenceService.class, proxy = "sdpf",protocol = {"dubbo"}, dynamic = true)
public class MenuCompetenceServiceImpl extends BaseInServiceImpl<MenuCompetence, IMenuCompetenceMapper> implements IMenuCompetenceMapper {

    @Override
    @ErrorMessage(code="001",message = "权限分类不能为空!")
    public boolean saveList(String competenceCategoryId, List<MenuCompetence> list) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(competenceCategoryId))
            throw new BusinessException("001");
        //1、 根据权限类型，查询已经存在的菜单权限
        List<String> existsMenuList = this.getMenuCompetenceIdList(competenceCategoryId);
        if (existsMenuList != null && existsMenuList.size() > 0) {
            List<String> deleteMenuStrs = new ArrayList<>();
            List<MenuCompetence> updateMenuList = new ArrayList<>();

            Map<String, MenuCompetence> addCache = list.stream()
                    .collect(Collectors.toMap(MenuCompetence::getFkMenuId, (p) -> p));
            //2.1.1、 如果旧菜单权限列表与新菜单权限列表都有的菜单权限，加入修改列表中，并移除新菜单权限列表的改权限
// 2.1.2、 如果旧菜单权限中不存在于新的菜单权限列表，加入删除列表中
            existsMenuList.stream().filter(item -> StringUtil.isNullOrWhiteSpace(item)).forEach(item -> {
                //2.1.1、 如果旧菜单权限列表与新菜单权限列表都有的菜单权限，加入修改列表中，并移除新菜单权限列表的改权限
                if (addCache.containsKey(item)) {
                    updateMenuList.add(addCache.get(item));
                    addCache.remove(item);
                } else // 2.1.2、 如果旧菜单权限中不存在于新的菜单权限列表，加入删除列表中
                {
                    deleteMenuStrs.add(item);
                }
            });

            //经过 2.1.2 过滤后，新增的用户权限列表是要添加的列表
            if (addCache.size() > 0) {
                List<MenuCompetence> addUserList = new ArrayList<>(addCache.values());
                addCache.clear();//清空缓存
                addList(addUserList);
            }
            if (deleteMenuStrs.size() > 0) {
                for (String menuId : deleteMenuStrs) {
                    Delete(menuId, competenceCategoryId);
                }
            }
            return true;
        } else {
            //if (list.size() > 0)
            return getMapper().addList(list) > 0;
        }

    }

    private boolean Delete(String menuId, String catalogId) {
        Example e = new Example(MenuCompetence.class);
        e.createCriteria().andEqualTo("fkMenuId", menuId).andEqualTo("fkCompetenceCategoryId", catalogId);
        return getMapper().deleteByExample(e) > 0;
    }

    @Override
    public PageList<MenuData> getMenuListByCompetenceCategory(String categoryId, String key, QueryPage page) {
        PageList<MenuData> pageList = new PageList<>();
        SqlHelper.startPage(page);
        Map map = MapUtil.createMap(m -> {
            m.put("C_ID", categoryId);
            m.put("KEY", key);
        });
        List<MenuData> results = getMapper().getMenuListByCompetenceCategory(map);
        PageInfo<MenuData> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        map.clear();
        map = null;
        return pageList;
    }

    @Override
    public List<String> getMenuCompetenceIdList(String categoryId) {
        Example example = new Example(MenuCompetence.class);
        example.createCriteria().andEqualTo("fkCompetenceCategoryId", categoryId);
        return getMapper().selectByExample(example).stream()
                .map(MenuCompetence::getFkMenuId).collect(Collectors.toList());

    }

    @Override
    public boolean cancelMenuCompetence(String id) throws Exception {
        return deleteById(id) > 0;
    }

    @Override
    public boolean checkExists(String categoryId, String menuId) {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("CATEGORY_ID", categoryId);
            m.put("MENU_ID", menuId);
        });

        Integer integer = getMapper().checkExists(map);
        map.clear();
        map = null;
        return integer > 0;
    }

    @Override
    public boolean checkExists(String categoryId, int menuCode) {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("CATEGORY_ID", categoryId);
            m.put("MENU_CODE", menuCode);
        });
        Integer integer = getMapper().checkExists(map);
        map.clear();
        map = null;
        return integer > 0;
    }

    @Override
    public List<String> getCategoriesByMenuId(String menuId) {
        List<String> result = new ArrayList<>();
        Example example = new Example(MenuCompetence.class);
        example.createCriteria().andEqualTo("fkMenuId", menuId);
        getMapper().selectByExample(example).forEach(v -> {
            if (!result.contains(v.getFkCompetenceCategoryId())) {
                result.add(v.getFkCompetenceCategoryId());
            }
        });
        return result;
    }

    @Override
    public int checkExists(Map map) {
        return getMapper().checkExists(map);
    }

    @Override
    public List<MenuData> getMenuListByCompetenceCategory(Map map) {
        return getMapper().getMenuListByCompetenceCategory(map);
    }
}