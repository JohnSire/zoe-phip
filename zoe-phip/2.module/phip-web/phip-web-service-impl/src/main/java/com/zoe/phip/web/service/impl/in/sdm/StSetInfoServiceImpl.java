/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStRsSetElementInfoMapper;
import com.zoe.phip.web.dao.sdm.IStSetInfoMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.service.sdm.IStSetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stSetInfoService")
@Service(interfaceClass = IStSetInfoService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "数据集标识({0})已经存在,新增失败!")
@ErrorMessage(code = "002", message = "数据集标识({0})已经存在,更新失败!")
public class StSetInfoServiceImpl extends BaseInServiceImpl<StSetInfo, IStSetInfoMapper> implements IStSetInfoMapper {

    @Autowired
    private StRsSetElementInfoServiceImpl rsSetElementInfoService;

    public List<StSetInfo> getByCdaId(String fkCdaId, String key) {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("fkCdaId", fkCdaId);
            if (!StringUtil.isNullOrWhiteSpace(key)) m.put("key", key);
        });
        return getMapper().getByCdaId(map);
    }

    @Override
    public PageList<StSetInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public List<StSetInfo> getByCdaId(Map<String, Object> map) {
        return getMapper().getByCdaId(map);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }

    @Override
    public List<StSetInfo> getByPid(String pid) {
        return getMapper().getByPid(pid);
    }

    @Override
    public int add(StSetInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
        });
        if (getSingle(map) > 0) throw new BusinessException("001", entity.getCode());
        return super.add(entity);
    }

    @Override
    public int update(StSetInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
            m.put("id", entity.getId());
        });
        if (getSingle(map) > 0) throw new BusinessException("002", entity.getCode());
        return super.update(entity);
    }

    public int rsSetElementAdd(StRsSetElementInfo info) throws Exception {
        return rsSetElementInfoService.add(info);
    }

    public int rsSetElementUpdate(StRsSetElementInfo info) throws Exception {
        return rsSetElementInfoService.update(info);
    }

    public int rsSetElementDel(String id) throws Exception {

        return rsSetElementInfoService.deleteById(id);
    }
}