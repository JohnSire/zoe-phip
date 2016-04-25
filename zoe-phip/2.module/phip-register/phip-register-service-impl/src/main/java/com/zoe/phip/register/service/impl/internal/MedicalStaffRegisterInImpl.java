package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.register.dao.IMedicalStaffInfoMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.internal.IMedicalStaffRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("MedicalStaffRegisterIn")
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败")
@Service(interfaceClass = IMedicalStaffRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class MedicalStaffRegisterInImpl implements IMedicalStaffRegisterIn {

    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterInImpl.class);

    @Autowired
    private IMedicalStaffInfoMapper staffInfoMapper;

    @Override
    public ServiceResultT<MedicalStaffInfo> addProvider(MedicalStaffInfo medicalStaffInfo) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {
            Example example = new Example(MedicalStaffInfo.class);
            example.createCriteria().andEqualTo("staffId", medicalStaffInfo.getStaffId());
            //数据是否存在判断
            int count = staffInfoMapper.selectCountByExample(example);
            if (count > 0) {
                throw new BusinessException("001");
            }
            //保存到数据库
            medicalStaffInfo.setId(StringUtil.getUUID());
            staffInfoMapper.defaultAdd(medicalStaffInfo);
            return medicalStaffInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> updateProvider(MedicalStaffInfo medicalStaffInfo) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {
            Example example = new Example(MedicalStaffInfo.class);
            example.createCriteria().andEqualTo("staffId", medicalStaffInfo.getStaffId());
            //数据是否存在判断
            int count = staffInfoMapper.selectCountByExample(example);
            if (count == 0) {
                throw new BusinessException("002");
            }
            //保存到数据库
            medicalStaffInfo.setId(StringUtil.getUUID());
            staffInfoMapper.defaultAdd(medicalStaffInfo);
            return medicalStaffInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<MedicalStaffInfo> providerDetailsQuery(Map<String,Object> map) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {
            //todo 字典赋值
            MedicalStaffInfo staffInfo = staffInfoMapper.getStaff(map);
            if (staffInfo == null) {
                throw new BusinessException("003");
            }
            return staffInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResult providerDelete(MedicalStaffInfo staffInfo) {
        return null;
    }

    @Override
    public ServiceResultT<List<MedicalStaffInfo>> providerListQuery(Map<String, Object> map) {
        return null;
    }
}
