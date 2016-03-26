/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.SystemDictCategoryMapper;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.Page;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
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


@Repository("SystemDictCategoryService")
@Service()
public final class SystemDictCategoryServiceImpl extends BaseInServiceImpl<SystemDictCategory> implements SystemDictCategoryService {
    @Override
    public ServiceResult add(SystemData systemData, SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode());
                    int count = getMapper().selectCountByExample(example);
                    if (count > 0) {
                        throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
                    } else
                        return getMapper().insertSelective(entity);
                });

    }

    @Override
    public ServiceResult addList(SystemData systemData, List<SystemDictCategory> entities) {
        StringBuffer stringBuffer = new StringBuffer();
        return SafeExecuteUtil.execute(
                () -> {
                    entities.forEach(v -> {
                        Example example = new Example(SystemDictCategory.class);
                        example.createCriteria().andEqualTo("code", v.getCode());
                        int count = getMapper().selectCountByExample(example);
                        if (count > 0) {
                            stringBuffer.append("字典类别(" + v.getCode() + ")已经存在!" + "\r\n");
                        }
                    });
                    if (stringBuffer.length() <= 0)
                        return getMapper().addList(entities);
                    else {
                        throw new BusinessException(stringBuffer.toString());
                    }
                });
    }

    @Override
    public ServiceResult update(SystemData systemData, SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode())
                            .andNotEqualTo("id", entity.getId());
                    int count = getMapper().selectCountByExample(example);
                    if (count > 0) {
                        throw new BusinessException("该字典类{0})已经存在!", entity.getCode());
                    } else
                        return getMapper().updateByPrimaryKeySelective(entity);
                });
    }

    public ServiceResultT<PageList<SystemDictCategory>> getDictCategories(SystemData systemData, String key, QueryPage queryPage) {
        SafeExecuteUtil<PageList<SystemDictCategory>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictCategory> pageList = new PageList<>();
            Page.startPage(queryPage);
            Map<String, Object> map = new HashMap<>();
            if (!StringUtil.isNullOrWhiteSpace(key))
                map.put("key", key);
            List<SystemDictCategory> results = ((SystemDictCategoryMapper) getMapper()).getDictCategories(map);
            PageInfo<SystemDictCategory> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    public ServiceResultT<SystemDictCategory> getDictCategory(SystemData systemData, String code) {
        SafeExecuteUtil<SystemDictCategory> sr = new SafeExecuteUtil<>();
        return sr.executeT(() -> {

            if (StringUtil.isNullOrWhiteSpace(code)) return null;
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            SystemDictCategory systemDictCategory = ((SystemDictCategoryMapper) getMapper()).getDictCategory(map);
            return systemDictCategory;
        });
    }
}