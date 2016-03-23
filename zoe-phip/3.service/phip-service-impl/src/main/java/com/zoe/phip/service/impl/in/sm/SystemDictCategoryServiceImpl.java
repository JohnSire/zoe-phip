/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */


@Component("SystemDictCategoryService")
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
public class SystemDictCategoryServiceImpl extends BaseInServiceImpl<SystemDictCategory> implements SystemDictCategoryService {

    @Override
    public ServiceResult add(SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode());
                    List<SystemDictCategory> list = getMapper().selectByExample(example);
                    if (list != null && list.size() > 0) {
                        throw new BusinessException("该字典类�%s)已经存在", entity.getCode());
                    } else
                        return getMapper().insertSelective(entity);
                });

    }

    @Override
    public ServiceResult addList(List<SystemDictCategory> entities) {
        StringBuffer stringBuffer = new StringBuffer();
        return SafeExecuteUtil.execute(
                () -> {
                    entities.forEach(v -> {
                        Example example = new Example(SystemDictCategory.class);
                        example.createCriteria().andEqualTo("code", v.getCode());
                        List<SystemDictCategory> list = getMapper().selectByExample(example);
                        if (list != null && list.size() > 0) {
                            stringBuffer.append("字典类别(" + v.getCode() + ")已经存在" + "\r\n");
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
    public ServiceResult update(SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode())
                            .andNotEqualTo("id", entity.getId());
                    List<SystemDictCategory> list = getMapper().selectByExample(example);
                    if (list != null && list.size() > 0) {
                        throw new BusinessException("该字典类�{0})已经存在", entity.getCode());
                    } else
                        return getMapper().updateByPrimaryKeySelective(entity);
                });
    }

    public ServiceResultT<PageList<SystemDictCategory>> getDictCategories(String key, QueryPage queryPage) {
        SafeExecuteUtil<PageList<SystemDictCategory>> safeExecute = new SafeExecuteUtil<>();
        return safeExecute.executeT(() ->
        {
            PageList<SystemDictCategory> pageList = new PageList<>();
            Example example = new Example(SystemDictCategory.class);
            if (queryPage.getOrderBy() != null) {
                PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), queryPage.getOrderBy());
            } else {
                PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
            }
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                example.createCriteria().andLike("code", key);
                example.or(example.createCriteria().andLike("name", key));
            }

            List<SystemDictCategory> results = getMapper().selectByExample(example);
            PageInfo<SystemDictCategory> pageInfo = new PageInfo<>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }

    public ServiceResultT<SystemDictCategory> getDictCategory(String code) {

        SafeExecuteUtil<SystemDictCategory> sr = new SafeExecuteUtil<>();
        return sr.executeT(() -> {
            Example example = new Example(SystemDictCategory.class);
            example.createCriteria().andEqualTo("code", code);
            List<SystemDictCategory> list = getMapper().selectByExample(example);
            if (list != null && list.size() <= 0) {
                throw new BusinessException("该字典类�{0})不存�", code);
            } else
                return list.get(0);
        });
    }
}