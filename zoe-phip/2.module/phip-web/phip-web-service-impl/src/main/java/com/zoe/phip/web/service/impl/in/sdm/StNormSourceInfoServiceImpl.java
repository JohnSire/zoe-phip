/*
 * Powered By zoe
 * Since 2008 - 2016
*/
package com.zoe.phip.web.service.impl.in.sdm;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sdm.IStNormSourceInfoMapper;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import com.zoe.phip.web.model.sm.MenuData;
import com.zoe.phip.web.service.sdm.IStNormSourceInfoService;
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
@Repository("stNormSourceInfoService")
@Service(interfaceClass = IStNormSourceInfoService.class, proxy = "sdpf", dynamic = true)
public class StNormSourceInfoServiceImpl extends BaseInServiceImpl<StNormSourceInfo, IStNormSourceInfoMapper> implements IStNormSourceInfoMapper {

    public PageList<StNormSourceInfo> getDataPageList(String key, QueryPage queryPage) {
        PageList<StNormSourceInfo> pageList = new PageList<>();
        Example example = new Example(StNormSourceInfo.class);
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key))
            map.put("key", key);
        List<StNormSourceInfo> results = getMapper().getDataPageList(map);
        PageInfo<StNormSourceInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    public List<StNormSourceInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }
}