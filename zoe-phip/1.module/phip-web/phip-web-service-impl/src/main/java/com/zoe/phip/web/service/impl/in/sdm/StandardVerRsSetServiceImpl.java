/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
;
import com.zoe.phip.web.dao.sdm.IStandardVerRsSetMapper;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.model.sdm.StandardVerRsSet;
import com.zoe.phip.web.service.sdm.IStandardVerRsSetService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
@Repository("standardVerRsSetService")
@Service(interfaceClass = IStandardVerRsSetService.class, protocol = {"dubbo"},proxy = "sdpf", dynamic = true)
public class StandardVerRsSetServiceImpl extends BaseInServiceImpl<StandardVerRsSet, IStandardVerRsSetMapper> implements IStandardVerRsSetMapper {

    public int versionStandardStruct(String fkVersionId, List<StandardVerRsSet> setList) throws Exception {
        deleteByVersionId(fkVersionId);
        int i = super.addList(setList);
        return i;
    }

    public List<StSetInfo> getVerRsSetInfo(String fkVersionId,String fkCdaId) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkVersionId", fkVersionId);
            map1.put("fkCdaId", fkCdaId);
        });
        return getSetByFkVersionId(map);
    }

    public int deleteByVersionId(String fkVersionId) {
        Example example = new Example(StandardVerRsSet.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        return super.deleteByExample(example);
    }

    @Override
    public int add(StandardVerRsSet entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkVersionId", entity.getFkVersionId());
            map1.put("fkCdaId", entity.getFkCdaId());
            map1.put("fkSetId", entity.getFkSetId());
        });
        if (getSingle(map) > 0) {
            dispose(map);
            throw new BusinessException("001");
        }
        dispose(map);
        return super.add(entity);
    }

    @Override
    public int update(StandardVerRsSet entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkVersionId", entity.getFkVersionId());
            map1.put("fkCdaId", entity.getFkCdaId());
            map1.put("fkSetId", entity.getFkSetId());
            map1.put("id", entity.getId());
        });
        if (getSingle(map) > 0) {
            dispose(map);
            throw new BusinessException("001");
        }
        dispose(map);
        return super.update(entity);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }

    @Override
    public List<StSetInfo> getSetByFkVersionId(Map<String, Object> map) {
        return getMapper().getSetByFkVersionId(map);
    }

}