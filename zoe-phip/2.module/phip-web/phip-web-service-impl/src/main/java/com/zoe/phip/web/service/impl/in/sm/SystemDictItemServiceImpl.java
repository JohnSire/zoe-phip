/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.infrastructure.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.infrastructure.util.SqlHelper;
import com.zoe.phip.web.dao.sm.ISystemDictItemMapper;
import com.zoe.phip.web.model.sm.SystemDictCategory;
import com.zoe.phip.web.model.sm.SystemDictItem;
import com.zoe.phip.web.service.sm.ISystemDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Repository("SystemDictItemService")
@Service(interfaceClass = ISystemDictItemService.class, proxy = "sdpf", dynamic = true)
public final class SystemDictItemServiceImpl extends BaseInServiceImpl<SystemDictItem, ISystemDictItemMapper> implements ISystemDictItemMapper {

    @Autowired
    private SystemDictCategoryServiceImpl service;

    @Override
    public int add(SystemDictItem entity) throws Exception {
        Example example = new Example(SystemDictItem.class);
        example.createCriteria().andEqualTo("fkSystemDictCategoryId", entity.getFkSystemDictCategoryId())
                .andEqualTo("code", entity.getCode());
        //example.createCriteria().andEqualTo("code", entity.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
        } else
            return getMapper().insertSelective(entity);
    }

    @Override
    public int update(SystemDictItem entity) throws Exception {

        Example example = new Example(SystemDictItem.class);
        example.createCriteria().andEqualTo("code", entity.getCode()).andEqualTo("fkSystemDictCategoryId", entity.getFkSystemDictCategoryId()).andNotEqualTo("id", entity.getId());

        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
        } else
            return getMapper().updateByPrimaryKeySelective(entity);

    }

    @Override
    public int categoryExists(String categoryId) throws Exception {

        Example example = new Example(SystemDictItem.class);
        example.createCriteria().andLike("fkSystemDictCategoryId", categoryId);
        int count = getMapper().selectCountByExample(example);
        return count;
    }

    @Override
    public PageList<SystemDictItem> getDictItems(String categoryId, String key, QueryPage page) {

        PageList<SystemDictItem> pageList = new PageList<>();
        page.setOrderBy("ssdi.code");
        SqlHelper.startPage(page);
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("categoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                m.put("key", key);
            }
        });
        List<SystemDictItem> results = getMapper().getDataItemList(map);
        PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        map.clear();
        map = null;
        return pageList;
    }

    @Override
    public List<SystemDictItem> getDictItems(String categoryId, String key) {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("categoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                m.put("key", key);
            }
        });
        List<SystemDictItem> list = getMapper().getDataItemList(map);
        map.clear();
        map = null;
        return list;
    }

    @Override
    public PageList<SystemDictItem> getDictItemsByCategoryCode(String categoryCode, QueryPage page) throws Exception {

        PageList<SystemDictItem> pageList = new PageList<>();
        SqlHelper.startPage(page);
        String categoryId = getCategoryId(categoryCode);
        if (StringUtil.isNullOrWhiteSpace(categoryId))
            throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
        Map<String, Object> map = MapUtil.createMap(m -> m.put("categoryCode", categoryCode));
        List<SystemDictItem> results = getMapper().getDataItemList(map);
        PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        map.clear();
        map = null;
        return pageList;
    }

    @Override
    public SystemDictItem getDictItemByCategoryId(String categoryId, String code) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(code))
            throw new BusinessException("字典项代码不能为空!");
        return getMapper().getDataItemList(MapUtil.createMap(m -> {
            m.put("categoryId", categoryId);
            m.put("code", code);
        })).stream().findFirst().orElseGet(null);
    }

    @Override
    public SystemDictItem getDictItemByCategoryCode(String categoryCode, String code) throws Exception {
        String categoryId = getCategoryId(categoryCode);
        if (StringUtil.isNullOrWhiteSpace(categoryId))
            throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
        if (StringUtil.isNullOrWhiteSpace(code))
            throw new BusinessException("字典项代码不能为空!");
        return getMapper().getDataItemList(MapUtil.createMap(m -> {
            m.put("categoryId", categoryId);
            m.put("code", code);
        })).stream().findFirst().orElseGet(null);
    }

    @Override
    public List<SystemDictItem> getDictItemsByCategoryCode(String categoryCode) throws Exception {

        String categoryId = getCategoryId(categoryCode);
        if (StringUtil.isNullOrWhiteSpace(categoryId))
            throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
        Example example = new Example(SystemDictItem.class);
        example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
        return getMapper().selectByExample(example);
    }

    private String getCategoryId(String categoryCode) throws Exception {
        SystemDictCategory sr = service.getDictCategory(categoryCode);
        if (sr != null)
            return sr.getId();
        return null;
    }

    @Override
    public List<SystemDictItem> getDataItemList(Map map) {
        return getMapper().getDataItemList(map);
    }
}