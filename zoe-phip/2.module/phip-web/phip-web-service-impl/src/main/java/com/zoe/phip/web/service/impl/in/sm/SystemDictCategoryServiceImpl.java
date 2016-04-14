/*
 * Powered By zoe
 * Since 2008 - 2016
 */


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
import com.zoe.phip.web.dao.sm.ISystemDictCategoryMapper;
import com.zoe.phip.web.model.sm.SystemDictCategory;
import com.zoe.phip.web.service.sm.ISystemDictCategoryService;
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

    /**
     * 新增系统字典分类信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @ErrorMessage(code = "001", message = "该字典类({0})已经存在!")
    public int add(SystemDictCategory entity) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", entity.getCode());
        List<SystemDictCategory> list = getMapper().selectByExample(example);
        if (list != null && list.size() > 0) {
            throw new BusinessException("001", entity.getCode());
        } else
            return getMapper().insertSelective(entity);

    }

    /**
     * 批量新增系统字典分类信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @ErrorMessage(code = "002", message = "字典类别({0})已经存在!")
    public int addList(List<SystemDictCategory> entities) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        entities.forEach(v -> {
            Example example = new Example(SystemDictCategory.class);
            example.createCriteria().andEqualTo("code", v.getCode());
            int count = getMapper().selectCountByExample(example);
            if (count > 0) {
                stringBuffer.append("[" + v.getCode() + "]");
            }
        });
        if (stringBuffer.length() <= 0)
            return getMapper().addList(entities);
        else {
            throw new BusinessException("002", stringBuffer.toString());
        }
    }

    /**
     * 更新系统字典分类信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @ErrorMessage(code = "003", message = "该字典类({0})已经存在!")
    public int update(SystemDictCategory entity) throws Exception {
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", entity.getCode())
                .andNotEqualTo("id", entity.getId());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("003", entity.getCode());
        } else
            return getMapper().updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据关键字查询系统分类列表
     *
     * @param key       关键字
     * @param queryPage 分页参数
     * @return
     * @throws Exception
     */
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
        map.clear();
        map = null;
        return pageList;
    }

    /**
     * 根据字典分类编码获取系统字典分类信息
     *
     * @param code 编码
     * @return
     * @throws Exception
     */
    @ErrorMessage(code = "004", message = "字典分类编码不能为空!")
    @ErrorMessage(code = "005", message = "该字典类({0})已经存在!")
    public SystemDictCategory getDictCategory(String code) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(code))
            throw new BusinessException("004");
        Example example = new Example(SystemDictCategory.class);
        example.createCriteria().andEqualTo("code", code);
        SystemDictCategory model = getMapper().getDictCategory(MapUtil.createMap(
                m -> m.put("code", code)));
        if (model == null) {
            throw new BusinessException("005", code);
        } else
            return model;
    }

    /**
     * 声明
     *
     * @param map
     * @return
     */
    @Override
    public List<SystemDictCategory> getDictCategories(Map map) {
        return getMapper().getDictCategories(map);
    }

    /**
     * 声明
     *
     * @param map
     * @return
     */
    @Override
    public SystemDictCategory getDictCategory(Map map) {
        return getMapper().getDictCategory(map);
    }
}