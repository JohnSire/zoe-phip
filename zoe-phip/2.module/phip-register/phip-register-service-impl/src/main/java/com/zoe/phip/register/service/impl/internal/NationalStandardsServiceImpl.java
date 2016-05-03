/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.INationalStandardsMapper;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.register.service.internal.INationalStandardsService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Repository("nationalStandardsService")
@Service(interfaceClass = INationalStandardsService.class, protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "国家标准{0}已存在!")
public class NationalStandardsServiceImpl extends BaseInServiceImpl<NationalStandards, INationalStandardsMapper> implements INationalStandardsMapper {

    public PageList<NationalStandards> getDataPageList(String key, QueryPage queryPage) {
        PageList<NationalStandards> pageList = new PageList<>();
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> params = new HashMap<>();
        params.put("key", SqlHelper.getLikeStr(key));
        List<NationalStandards> results = getMapper().getDataPageList(params);
        PageInfo<NationalStandards> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Autowired
    private SqlSession sqlSession;

    @Override

    public int add(NationalStandards entity) throws Exception {
        Collection<String> cols= sqlSession.getConfiguration().getMappedStatementNames();
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
    public List<NationalStandards> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public int getNationalStandard(Map<String, Object> map) {
        return getMapper().getNationalStandard(map);
    }

    @Override
    public NationalStandards getNationalStandardDescr(Map<String, Object> map) {
        return getMapper().getNationalStandardDescr(map);
    }

    @Override
    public int defaultUpdate(NationalStandards t) {
        return getMapper().defaultUpdate(t);
    }
}