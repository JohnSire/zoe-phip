/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SortOrder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sdm.IStRsSetElementInfoMapper;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.service.sdm.IStRsSetElementInfoService;
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
@Repository("stRsSetElementInfoService")
@Service(interfaceClass = IStRsSetElementInfoService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "数据列({0})已经存在,新增失败!")
@ErrorMessage(code = "002", message = "数据列({0})已经存在,更新失败!")
public class StRsSetElementInfoServiceImpl extends BaseInServiceImpl<StRsSetElementInfo, IStRsSetElementInfoMapper> implements IStRsSetElementInfoMapper {



    public int deleteBySetId(String fkSetId) {
        Example example = new Example(StRsSetElementInfo.class);
        example.createCriteria().andEqualTo("fkSetId", fkSetId);
        return super.deleteByExample(example);
    }

    @Override
    public int add(StRsSetElementInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkSetId", entity.getFkSetId());
            map1.put("elementCode", entity.getElementCode());
        });
        if (getSingle(map) > 0) {
            super.dispose(map);
            throw new BusinessException("001", entity.getElementCode());
        }
        super.dispose(map);
        return super.add(entity);
    }

    @Override
    public int update(StRsSetElementInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkSetId", entity.getFkSetId());
            map1.put("elementCode", entity.getElementCode());
            map1.put("id", entity.getId());
        });
        if (getSingle(map) > 0) {
            super.dispose(map);
            throw new BusinessException("002", entity.getElementCode());
        }
        super.dispose(map);
        return super.update(entity);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }

    /**
     * 根据数据集代码和数据列字段查询关系表数据
     *
     * @param setCode
     * @param elementCode
     * @return
     */
    public StRsSetElementInfo getBySetCode(String setCode, String elementCode) {
        Map<String, Object> map = new TreeMap<>();
        map.put("setCode", setCode);
        map.put("elementCode", elementCode);
        StRsSetElementInfo info = getMapper().getBySetCode(map);
        super.dispose(map);
        return info;
    }

    public PageList<StRsSetElementInfo> getDataPageList(String fkSetId, String key, QueryPage queryPage) {
        PageList<StRsSetElementInfo> pageList = new PageList<>();
        queryPage.setOrderBy("PSRSEI.ELEMENT_CODE");
        queryPage.setSortOrder(SortOrder.ASC);
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        map.put("fkSetId", fkSetId);
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", key);
        List<StRsSetElementInfo> results = getMapper().getDataPageList(map);
        PageInfo<StRsSetElementInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        dispose(map);
        return pageList;
    }

    @Override
    public List<StRsSetElementInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public StRsSetElementInfo getBySetCode(Map<String, Object> map) {
        return getMapper().getBySetCode(map);
    }
}