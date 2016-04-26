/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.service.impl.internal;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.annotation.ErrorMessages;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.INationalStandardsMapper;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.register.service.internal.INationalStandardsService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Repository("nationalStandardsService")
@Service(interfaceClass = INationalStandardsService.class, protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)

public class NationalStandardsServiceImpl extends BaseInServiceImpl<NationalStandards, INationalStandardsMapper> implements INationalStandardsMapper {

    public PageList<NationalStandards> getDataListByPage(String key, QueryPage queryPage) {
        PageList<NationalStandards> pageList = new PageList<>();
        //分页
        SqlHelper.startPage(queryPage);
        key = StringUtil.isNullOrWhiteSpace(key) ? "" : key.toUpperCase();
        Map<String, Object> params = new HashMap<>();
        params.put("key", SqlHelper.getLikeStr(key));
        List<NationalStandards> results = getMapper().getDataListByPage(params);
        PageInfo<NationalStandards> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    @ErrorMessage(code = "001", message = "国家标准{0}已存在!")
    public int add(NationalStandards entity) throws Exception {
        Map<String, Object> map = new TreeMap<>();
        map.put("code", entity.getCode());
        if (getNationalStandard(map) > 0) {
            map.clear();
            map = null;
            throw new BusinessException("001", entity.getCode());
        }
        return super.add(entity);
    }

    @Override
    @ErrorMessage(code = "001", message = "国家标准{0}已存在!")
    public int update(NationalStandards entity) throws Exception {
        Map<String, Object> map = new TreeMap<>();
        map.put("code", entity.getCode());
        map.put("id", entity.getId());
        if (getNationalStandard(map) > 0) {
            map.clear();
            map = null;
            throw new BusinessException("001", entity.getCode());
        }
        return super.update(entity);
    }

    @Override
    public List<NationalStandards> getDataListByPage(Map<String, Object> map) {
        return getMapper().getDataListByPage(map);
    }

    @Override
    public int getNationalStandard(Map<String, Object> map) {
        return getMapper().getNationalStandard(map);
    }

}