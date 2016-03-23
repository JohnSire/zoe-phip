/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Component("SystemDictItemService")
@com.alibaba.dubbo.config.annotation.Service
public class SystemDictItemServiceImpl extends BaseInServiceImpl<SystemDictItem> implements SystemDictItemService {

    @Autowired
    private SystemDictCategoryService service;

    @Override
    public ServiceResult categoryExists(String categoryId) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictItem.class);
                    example.createCriteria().andLike("", categoryId);
                    List<SystemDictItem> items = getMapper().selectByExample(example);
                    if (items != null && items.size() > 0)
                        return true;
                    return false;
                });
    }

    @Override
    public ServiceResultT<PageList<SystemDictItem>> getDictItems(String categoryId, String key, QueryPage page) {
        SafeExecuteUtil<PageList<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictItem> pageList = new PageList<>();
            Example example = new Example(SystemDictItem.class);
            if (page.getOrderBy() != null) {
                PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
            } else {
                PageHelper.startPage(page.getPageNum(), page.getPageSize());
            }
            Example.Criteria criteria = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                criteria.andLike("code", key);
                Example.Criteria criteria2 = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId).andLike("name", key);
                example.or(criteria2);
            }
            List<SystemDictItem> results = getMapper().selectByExample(example);
            PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    @Override
    public ServiceResultT<List<SystemDictItem>> getDictItems(String categoryId, String key) {
        SafeExecuteUtil<List<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            Example example = new Example(SystemDictItem.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                criteria.andLike("code", key);
                Example.Criteria criteria2 = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId).andLike("name", key);
                example.or(criteria2);
            }
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResultT<PageList<SystemDictItem>> getDictItemsByCategoryCode(String categoryCode, QueryPage page) {
        SafeExecuteUtil<PageList<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictItem> pageList = new PageList<>();
            Example example = new Example(SystemDictItem.class);

            if (page.getOrderBy() != null) {
                PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
            } else {
                PageHelper.startPage(page.getPageNum(), page.getPageSize());
            }
            String categoryId = getCategoryId(categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            List<SystemDictItem> results = getMapper().selectByExample(example);
            PageInfo<SystemDictItem> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    @Override
    public ServiceResultT<SystemDictItem> getDictItemByCategoryId(String categoryId, String code) {

        SafeExecuteUtil<SystemDictItem> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            if (StringUtil.isNullOrWhiteSpace(code))
                throw new BusinessException("字典项代码不能为空!");
            Example example = new Example(SystemDictItem.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
            criteria.andLike("code", code);
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResultT<SystemDictItem> getDictItemByCategoryCode(String categoryCode, String code) {
        SafeExecuteUtil<SystemDictItem> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            String categoryId = getCategoryId(categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            if (StringUtil.isNullOrWhiteSpace(code))
                throw new BusinessException("字典项代码不能为空!");
            Example example = new Example(SystemDictItem.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
            criteria.andLike("code", code);
            return getMapper().selectByExample(example);
        });
    }

    @Override
    public ServiceResultT<List<SystemDictItem>> getDictItemsByCategoryCode(String categoryCode) {
        SafeExecuteUtil<List<SystemDictItem>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            String categoryId = getCategoryId(categoryCode);
            if (StringUtil.isNullOrWhiteSpace(categoryId))
                throw new BusinessException("字典分类({0})已经被删除!", categoryCode);
            Example example = new Example(SystemDictItem.class);
            example.createCriteria().andEqualTo("fkSystemDictCategoryId", categoryId);
            return getMapper().selectByExample(example);
        });
    }

    private String getCategoryId(String categoryCode) {
        ServiceResultT sr = service.getDictCategory(categoryCode);
        if (sr.getIsSuccess() && sr.getResult() != null)
            return ((SystemDictCategory) sr.getResult()).getId();
        return null;
    }
}