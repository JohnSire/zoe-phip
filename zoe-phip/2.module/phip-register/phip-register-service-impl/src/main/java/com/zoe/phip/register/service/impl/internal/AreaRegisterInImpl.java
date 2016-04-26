package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.internal.IAreaRegisterIn;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("AreaRegisterIn")
@Service(interfaceClass = IAreaRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败")
public class AreaRegisterInImpl extends BaseInServiceImpl<AreaBaseInfo, IAreaBaseInfoMapper> implements IAreaBaseInfoMapper {


    public AreaBaseInfo addAreaRequest(AreaBaseInfo areaBaseInfo) throws Exception {
        String id = StringUtil.isNullOrWhiteSpace(areaBaseInfo.getId()) ? UUID.randomUUID().toString() : areaBaseInfo.getId();
        if (ifCodeExist(id, areaBaseInfo.getCode(), 1)) {
            throw new BusinessException("001");
        }
        areaBaseInfo.setId(id);
        super.add(areaBaseInfo);
        return areaBaseInfo;

    }


    public AreaBaseInfo updateAreaRequest(AreaBaseInfo areaBaseInfo) throws Exception {
        //String id = StringUtil.isNullOrWhiteSpace(areaBaseInfo.getId()) ? UUID.randomUUID().toString() : areaBaseInfo.getId();
        if (ifCodeExist(areaBaseInfo.getId(), areaBaseInfo.getCode(), 1)) {
            throw new BusinessException("002");
        }
        //areaBaseInfo.setId(id);
        super.update(areaBaseInfo);
        return areaBaseInfo;
    }


    public AreaBaseInfo areaDetailQuery(String id) throws Exception {
        //todo 字典赋值
        AreaBaseInfo baseInfo = getMapper().selectByPrimaryKey(id);
        if (baseInfo == null) {
            throw new BusinessException("003");
        }
        return baseInfo;
    }

    public List<AreaBaseInfo> areaChildrenRegistryQuery(String code) {
        Map<String, Object> map = new TreeMap<>();
        map.put("code", code);
        List<AreaBaseInfo> result = getMapper().getChildren(map);
        map.clear();
        map = null;
        return result;
    }

    public AreaBaseInfo areaHistoryRegistryQuery(String code) {
        Map<String, Object> map = new TreeMap<>();
        map.put("historyId", code);
        AreaBaseInfo baseInfo = getMapper().getAreaBaseInfo(map);
        map.clear();
        map = null;
        return baseInfo;

    }

    private boolean ifCodeExist(String id, String Code, int type) {
        Example example = new Example(AreaBaseInfo.class);
        example.createCriteria().andEqualTo("Code", Code);
        if (type != 0) example.createCriteria().andNotEqualTo("id", id);
        int count = getMapper().selectCountByExample(example);
        return count > 0;
    }

    @Override
    public AreaBaseInfo getAreaBaseInfo(Map<String, Object> map) {
        return getMapper().getAreaBaseInfo(map);
    }

    @Override
    public List<AreaBaseInfo> getChildren(Map<String, Object> map) {
        return getMapper().getChildren(map);
    }
}
