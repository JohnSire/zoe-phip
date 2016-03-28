/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.sm.SystemDictCategoryMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.impl.util.SqlHelper;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */


@Repository("SystemDictCategoryService")
@Service(interfaceClass = SystemDictCategoryService.class, proxy = "sdpf", dynamic = true)
public final class SystemDictCategoryServiceImpl extends BaseInServiceImpl<SystemDictCategory, SystemDictCategoryMapper> implements SystemDictCategoryMapper {

    @Override
    public int add(SystemDictCategory entity) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", entity.getCode());
        List<SystemDictCategory> list = getMapper().selectByExample(example);
        if (list != null && list.size() > 0) {
            throw new BusinessException("该字典类({0})已经存在", entity.getCode());
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
                stringBuffer.append("字典类别(" + v.getCode() + ")已经存在" + "\r\n");
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
        Example example = new Example(SystemDictCategory.class);
        SqlHelper.startPage(queryPage);
        if (!StringUtil.isNullOrWhiteSpace(key)) {

            example.createCriteria().andLike("code", "%" + key + "%");
            example.or(example.createCriteria().andLike("name", "%" + key + "%"));
        }

        List<SystemDictCategory> results = getMapper().selectByExample(example);
        PageInfo<SystemDictCategory> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    public SystemDictCategory getDictCategory(String code) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", code);
        List<SystemDictCategory> list = getMapper().selectByExample(example);
        if (list != null && list.size() <= 0) {
            throw new BusinessException("该字典类{0})不存", code);
        } else
            return list.get(0);
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