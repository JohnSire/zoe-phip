/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVerRsDictMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsDict;
import com.zoe.phip.web.model.sdm.StandardVerRsField;
import com.zoe.phip.web.service.sdm.IStandardVerRsDictService;
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
@Repository("standardVerRsDictService")
@Service(interfaceClass = IStandardVerRsDictService.class,protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
public class StandardVerRsDictServiceImpl extends BaseInServiceImpl<StandardVerRsDict, IStandardVerRsDictMapper> implements IStandardVerRsDictMapper {


    public int versionDictUpdate(String fkVersionId, List<StandardVerRsDict> infoList) throws Exception {
        Example example = new Example(StandardVerRsDict.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        int i = super.addList(infoList);
        return i;
    }


    public int deleteByVersionId(String fkVersionId) {
        Example example = new Example(StandardVerRsDict.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        return super.deleteByExample(example);
    }

    @Override
    public int add(StandardVerRsDict entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkDictId", entity.getFkDictId());
            map1.put("fkVersionId", entity.getFkVersionId());
        });
        if (getSingle(map) > 0) {
            dispose(map);
            throw new BusinessException("001");
        }
        dispose(map);
        return super.add(entity);
    }

    @Override
    public int update(StandardVerRsDict entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkDictId", entity.getFkDictId());
            map1.put("fkVersionId", entity.getFkVersionId());
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
}