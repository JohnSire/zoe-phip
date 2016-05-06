/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStRsSetElementInfoMapper;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.service.sdm.IStRsSetElementInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

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

   public int deleteBySetId(String fkSetId){
       Example example = new Example(StRsSetElementInfo.class);
       example.createCriteria().andEqualTo("fkSetId",fkSetId);
       return super.deleteByExample(example);
   }

    @Override
    public int add(StRsSetElementInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(map1 -> {
            map1.put("fkSetId", entity.getFkSetId());
            map1.put("elementCode", entity.getElementCode());
        });
        if (getSingle(map) > 0) {
            map.clear();
            map = null;
            throw new BusinessException("001", entity.getElementCode());
        }
        map.clear();
        map = null;
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
            map.clear();
            map = null;
            throw new BusinessException("002", entity.getElementCode());
        }
        map.clear();
        map = null;
        return super.update(entity);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }
}