/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.SystemDictItemMapper;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.Page;
import com.zoe.phip.service.in.sm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Repository("SystemDictItemService")
@Service()
public final class SystemDictItemServiceImpl extends BaseInServiceImpl<SystemDictItem> implements SystemDictItemService {

    @Autowired
    private SystemDictCategoryService service;

    @Override
    public ServiceResult add(SystemData systemData, SystemDictItem entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictItem.class);
                    example.createCriteria().andEqualTo("code", entity.getCode());
                    int count = getMapper().selectCountByExample(example);
                    if (count > 0) {
                        throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
                    } else
                        return getMapper().insertSelective(entity);
                });
    }

    @Override
    public ServiceResult update(SystemData systemData, SystemDictItem entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictItem.class);
                    example.createCriteria().andEqualTo("code", entity.getCode());
                    example.createCriteria().andNotEqualTo("id", entity.getId());
                    int count = getMapper().selectCountByExample(example);
                    if (count > 0) {
                        throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
                    } else
                        return getMapper().updateByPrimaryKeySelective(entity);
                });
    }

    @Override
    public ServiceResult categoryExists(SystemData systemData, String categoryId) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictItem.class);
                    example.createCriteria().andLike("", categoryId);
                    int count = getMapper().selectCountByExample(example);
                    if (count > 0)
                        return true;
                    return false;
                });
    }

    @Override
    public ServiceResultT<PageList<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key, QueryPage page) {
        SafeExecuteUtil<PageList<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictItem> pageList = new PageList<>();
            Page.startPage(page);
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                map.put("key", key);
            }
            List<SystemDictItem> results = ((SystemDictItemMapper) getMapper()).getDataItemList(map);
            PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    @Override
    public ServiceResultT<List<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key) {
        SafeExecuteUtil<List<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                map.put("key", key);
            }
            return ((SystemDictItemMapper) getMapper()).getDataItemList(map);
        });
    }

    @Override
    public ServiceResultT<PageList<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode, QueryPage page) {
        SafeExecuteUtil<PageList<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictItem> pageList = new PageList<>();
            Page.startPage(page);
            String categoryId = getCategoryId(systemData, categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            List<SystemDictItem> results = ((SystemDictItemMapper) getMapper()).getDataItemList(map);
            PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    @Override
    public ServiceResultT<SystemDictItem> getDictItemByCategoryId(SystemData systemData, String categoryId, String code) {

        SafeExecuteUtil<SystemDictItem> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类不能为空!");
            if (StringUtil.isNullOrWhiteSpace(code))
                throw new BusinessException("字典项代码不能为空!");
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            map.put("code", code);
            return ((SystemDictItemMapper) getMapper()).getDataItemList(map);
        });
    }

    @Override
    public ServiceResultT<SystemDictItem> getDictItemByCategoryCode(SystemData systemData, String categoryCode, String code) {
        SafeExecuteUtil<SystemDictItem> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            String categoryId = getCategoryId(systemData, categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            if (StringUtil.isNullOrWhiteSpace(code))
                throw new BusinessException("字典项代码不能为空!");
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            map.put("code", code);
            return ((SystemDictItemMapper) getMapper()).getDataItemList(map);
        });
    }

    @Override
    public ServiceResultT<List<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode) {
        SafeExecuteUtil<List<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            String categoryId = getCategoryId(systemData, categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            Map<String, Object> map = new HashMap<>();
            map.put("categoryId", categoryId);
            return ((SystemDictItemMapper) getMapper()).getDataItemList(map);
        });
    }

    private String getCategoryId(SystemData systemData, String categoryCode) {
        ServiceResultT sr = service.getDictCategory(systemData, categoryCode);
        if (sr.getIsSuccess() && sr.getResult() != null)
            return ((SystemDictCategory) sr.getResult()).getId();
        return null;
    }


}