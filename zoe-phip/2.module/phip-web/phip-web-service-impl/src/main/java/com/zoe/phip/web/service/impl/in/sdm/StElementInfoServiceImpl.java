/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sdm.IStElementInfoMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import com.zoe.phip.web.model.sm.SystemDictCategory;
import com.zoe.phip.web.service.sdm.IStElementInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stElementInfoService")
@Service(interfaceClass = IStElementInfoService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "数据元标识({0})已经存在!")
public class StElementInfoServiceImpl extends BaseInServiceImpl<StElementInfo, IStElementInfoMapper> implements IStElementInfoMapper {


    @Override
    public int add(StElementInfo entity) throws Exception {
        Map<String,Object> map = MapUtil.createMap(m->{
            m.put("code",entity.getCode());
        });
        if (getSingle(map) > 0)
            throw  new BusinessException("001",entity.getCode());
        map.clear();
        map = null;
        return super.add(entity);
    }

    @Override
    public int update(StElementInfo entity) throws Exception {
        Map<String,Object> map = MapUtil.createMap(m->{
            m.put("code",entity.getCode());
            m.put("id",entity.getId());
        });
        if (getSingle(map) > 0)
            throw  new BusinessException("001",entity.getCode());
        map.clear();
        map = null;
        return super.update(entity);
    }



    public PageList<StElementInfo> getDataPageList(String key, QueryPage queryPage) {

        PageList<StElementInfo> pageList = new PageList<>();
        Example example = new Example(StElementInfo.class);
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", key);
        List<StElementInfo> results = getMapper().getDataPageList(map);
        PageInfo<StElementInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<StElementInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }
}