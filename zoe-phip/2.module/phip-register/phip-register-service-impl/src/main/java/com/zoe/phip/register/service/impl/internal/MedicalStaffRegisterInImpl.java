package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IMedicalStaffInfoMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.internal.IMedicalStaffRegisterIn;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("MedicalStaffRegisterIn")
@Service(interfaceClass = IMedicalStaffRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class MedicalStaffRegisterInImpl extends BaseInServiceImpl<MedicalStaffInfo, IMedicalStaffInfoMapper> implements IMedicalStaffInfoMapper {

    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterInImpl.class);


    @ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
    public MedicalStaffInfo addProvider(MedicalStaffInfo medicalStaffInfo) throws Exception {
        Example example = new Example(MedicalStaffInfo.class);
        example.createCriteria().andEqualTo("staffId", medicalStaffInfo.getStaffId());
        //数据是否存在判断
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        medicalStaffInfo.setId(StringUtil.getUUID());
        super.add(medicalStaffInfo);
        return medicalStaffInfo;
    }


    @ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
    public MedicalStaffInfo updateProvider(MedicalStaffInfo medicalStaffInfo) throws Exception {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        Example example = new Example(MedicalStaffInfo.class);
        example.createCriteria().andEqualTo("staffId", medicalStaffInfo.getStaffId());
        //数据是否存在判断
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        medicalStaffInfo.setId(StringUtil.getUUID());
        super.add(medicalStaffInfo);
        return medicalStaffInfo;
    }


    @ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败")
    public MedicalStaffInfo providerDetailsQuery(Map<String, Object> map) throws Exception {
        //todo 字典赋值
        MedicalStaffInfo staffInfo = getMapper().getStaff(map);
        if (staffInfo == null) {
            throw new BusinessException("003");
        }
        return staffInfo;
    }


    public int providerDelete(MedicalStaffInfo staffInfo) {
        return getMapper().deleteByPrimaryKey(staffInfo.getId());
    }


    public List<MedicalStaffInfo> providerListQuery(Map<String, Object> map) {
        return null;
    }

    @Override
    public MedicalStaffInfo getStaff(Map<String, Object> map) {
        return getMapper().getStaff(map);
    }
}
