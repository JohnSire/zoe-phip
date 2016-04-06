/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.ISystemDictCategoryMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.SqlHelper;
import com.zoe.phip.service.in.sm.ISystemDictCategoryService;
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
@Service(interfaceClass = ISystemDictCategoryService.class, proxy = "sdpf", dynamic = true)
public final class SystemDictCategoryServiceImpl extends BaseInServiceImpl<SystemDictCategory, ISystemDictCategoryMapper> implements ISystemDictCategoryMapper {

    @Override
    public int add(SystemDictCategory entity) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", entity.getCode());
        List<SystemDictCategory> list = getMapper().selectByExample(example);
        if (list != null && list.size() > 0) {
            throw new BusinessException("该字典类({0})已经存在!", entity.getCode());
        } else
            return getMapper().insertSelective(entity);

    }

    @Override
    public int addList(List<SystemDictCategory> entities) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
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
    }

    @Override
    public int update(SystemDictCategory entity) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", entity.getCode())
                .andNotEqualTo("id", entity.getId());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("该字典类{0})已经存在", entity.getCode());
        } else
            return getMapper().updateByPrimaryKeySelective(entity);
    }

    public PageList<SystemDictCategory> getDictCategories(String key, QueryPage queryPage) throws Exception {
        PageList<SystemDictCategory> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        Map map = new HashMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key))
            map.put("key", key);
        List<SystemDictCategory> results = getMapper().getDictCategories(map);
        PageInfo<SystemDictCategory> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    public SystemDictCategory getDictCategory(String code) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(code))
            throw new BusinessException("字典项编码不能为空!");
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", code);
        SystemDictCategory model = getMapper().getDictCategory(MapUtil.createMap(m -> m.put("code", code)));
        if (model == null) {
            throw new BusinessException("该字典类{0})不存在!", code);
        } else
            return model;
    }

    @Override
    public List<SystemDictCategory> getDictCategories(Map map) {
        return getMapper().getDictCategories(map);
    }

    @Override
    public SystemDictCategory getDictCategory(Map map) {
        return getMapper().getDictCategory(map);
    }
}