package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IAreaRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AreaRegisterInImpl implements IAreaRegisterIn {
    @Autowired
    private IAreaBaseInfoMapper baseInfoMapper;

    @Override
    public ServiceResultT<AreaBaseInfo> addAreaRequest(AreaBaseInfo areaBaseInfo) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {

            String id = StringUtil.isNullOrWhiteSpace(areaBaseInfo.getId()) ? UUID.randomUUID().toString() : areaBaseInfo.getId();
            if (ifCodeExist(id, areaBaseInfo.getCode(), 1)) {
                throw new BusinessException("001");
            }
            areaBaseInfo.setId(id);
            baseInfoMapper.defaultAdd(areaBaseInfo);
            return areaBaseInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<AreaBaseInfo> updateAreaRequest(AreaBaseInfo areaBaseInfo) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {

            //String id = StringUtil.isNullOrWhiteSpace(areaBaseInfo.getId()) ? UUID.randomUUID().toString() : areaBaseInfo.getId();
            if (ifCodeExist(areaBaseInfo.getId(), areaBaseInfo.getCode(), 1)) {
                throw new BusinessException("001");
            }
            //areaBaseInfo.setId(id);
            baseInfoMapper.defaultUpdate(areaBaseInfo);
            return areaBaseInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<AreaBaseInfo> areaDetailQuery(String id) {

        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {
            //todo 字典赋值
            AreaBaseInfo baseInfo = baseInfoMapper.selectByPrimaryKey(id);
            if (baseInfo == null) {
                throw new BusinessException("003");
            }
            return baseInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<List<AreaBaseInfo>> areaChildrenRegistryQuery(String code) {

        return SafeExecuteUtil.execute0(()->{
            Map<String, Object> map = new TreeMap<>();
            map.put("code", code);
            List<AreaBaseInfo> result = baseInfoMapper.getChildren(map);
            map.clear();
            map = null;
            return result;
        },null);
    }

    @Override
    public ServiceResultT<AreaBaseInfo> areaHistoryRegistryQuery(String code) {
        return SafeExecuteUtil.execute0(()->{
            Map<String, Object> map = new TreeMap<>();
            map.put("historyId", code);
            AreaBaseInfo baseInfo = baseInfoMapper.getAreaBaseInfo(map);
            map.clear();
            map = null;
            return baseInfo;
        },null);
    }

    private boolean ifCodeExist(String id, String Code, int type) {
        Example example = new Example(AreaBaseInfo.class);
        example.createCriteria().andEqualTo("Code", Code);
        if (type != 0) example.createCriteria().andNotEqualTo("id", id);
        int count = baseInfoMapper.selectCountByExample(example);
        return count > 0;
    }
}
