/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVerRsCdaMapper;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StandardVerRsCda;
import com.zoe.phip.web.service.sdm.IStandardVerRsCdaService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-05
 */
@Repository("standardVerRsCdaService")
@Service(interfaceClass = IStandardVerRsCdaService.class,protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "关系已经存在!")
public class StandardVerRsCdaServiceImpl extends BaseInServiceImpl<StandardVerRsCda, IStandardVerRsCdaMapper> implements IStandardVerRsCdaMapper {

    public boolean versionStandardStruct(String fkVersionId,List<StandardVerRsCda> fieldList) throws Exception {
        List<StandardVerRsCda> info = getCdaRsByFkVersionId(fkVersionId);
        if (info.size() == 0) {
            Example cda = new Example(StandardVerRsCda.class);
            cda.createCriteria().andEqualTo("fkVersionId", fkVersionId);
            int i = super.addList(fieldList);
            return i > 0;
        } else {
            Example cda = new Example(StandardVerRsCda.class);
            cda.createCriteria().andEqualTo("fkVersionId", fkVersionId);
            boolean b = super.updateList(fieldList);
            return b;
        }
    }

    public List<StandardVerRsCda> getVerRsCda(String fkVersionId) throws Exception {
        Example cda = new Example(StandardVerRsCda.class);
        Example.Criteria criteria = cda.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        cda.or(criteria);
        return getMapper().selectByExample(cda);
    }

    public List<StandardVerRsCda> getCdaRsByFkVersionId(String fkVersionId) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkVersionId", fkVersionId);
        });
        return getCdaRsByFkVersionId(map);
    }

    public List<StCdaInfo> getVerRsCdaInfo(String fkVersionId) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkVersionId", fkVersionId);
        });
        return getCdaByFkVersionId(map);
    }

    public int deleteByVersionId(String fkVersionId) {
        Example example = new Example(StandardVerRsCda.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        return super.deleteByExample(example);
    }

    @Override
    public int add(StandardVerRsCda entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkCdaId", entity.getFkCdaId());
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
    public int update(StandardVerRsCda entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkCdaId", entity.getFkCdaId());
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

    @Override
    public List<StCdaInfo> getCdaByFkVersionId(Map<String, Object> map) {
        return getMapper().getCdaByFkVersionId(map);
    }

    @Override
    public List<StandardVerRsCda> getCdaRsByFkVersionId(Map<String, Object> map) {
        return getMapper().getCdaRsByFkVersionId(map);
    }
}