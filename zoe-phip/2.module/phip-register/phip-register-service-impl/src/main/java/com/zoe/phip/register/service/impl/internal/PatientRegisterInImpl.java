package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IXmanBaseInfoMapper;
import com.zoe.phip.register.dao.IXmanCardMapper;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;
import com.zoe.phip.register.service.internal.IPatientRegisterIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("PatientRegisterIn")
@Service(interfaceClass = IPatientRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class PatientRegisterInImpl extends BaseInServiceImpl<XmanBaseInfo, IXmanBaseInfoMapper> implements IXmanBaseInfoMapper {

    @Autowired
    private IXmanCardMapper cardMapper;


    @ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
    public XmanBaseInfo addPatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard) throws Exception {
        //数据是否存在判断
        Example example = new Example(XmanBaseInfo.class);
        example.createCriteria().andEqualTo("healthRecordNo", xmanBaseInfo.getHealthRecordNo());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        xmanBaseInfo.setId(StringUtil.getUUID());
        super.add(xmanBaseInfo);
        xmanCard.setXcXmanId(xmanBaseInfo.getId());
        xmanCard.setCreateAt(new Date());
        xmanCard.setModifyAt(new Date());
        xmanCard.setState(1);
        cardMapper.insertSelective(xmanCard);
        return xmanBaseInfo;
    }


    @ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
    public XmanBaseInfo updatePatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard) throws Exception {
        //数据是否存在判断
        Example example = new Example(XmanBaseInfo.class);
        example.createCriteria().andEqualTo("healthRecordNo", xmanBaseInfo.getHealthRecordNo());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        super.update(xmanBaseInfo);
        xmanCard.setModifyAt(new Date());
        cardMapper.updateByPrimaryKeySelective(xmanCard);
        return xmanBaseInfo;
    }


    @ErrorMessage(code = "003", message = "由于合并内容不存在，合并失败")
    public XmanBaseInfo mergePatientRegistry(String newPatientId, String oldPatientId) throws Exception {
        XmanBaseInfo oldPatient = getMapper().getPatient(oldPatientId);
        XmanBaseInfo newPatient = getMapper().getPatient(newPatientId);
        if (oldPatient == null || newPatient == null || StringUtil.isNullOrWhiteSpace(oldPatient.getHealthRecordNo())
                || StringUtil.isNullOrWhiteSpace(newPatient.getHealthRecordNo())) {
            throw new BusinessException("003");
        }
        //赋值
        copyValue(newPatient, oldPatient);
        //保存到数据库
        super.update(newPatient);
        getMapper().delete(oldPatient);
        return newPatient;
    }


    @ErrorMessage(code = "004", message = "由于查询内容不存在，查询失败")
    public XmanBaseInfo patientRegistryQuery(String patientId) throws Exception {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        //todo 字典赋值
        XmanBaseInfo baseInfo = getMapper().getPatient(patientId);
        if (baseInfo == null) {
            throw new BusinessException("004");
        }
        XmanCard xmanCard = cardMapper.getXmanCard(baseInfo.getId());
        return baseInfo;
    }


    /**
     * 将旧实体的值，赋到新实体上
     *
     * @param newBaseInfo
     * @param oldBaseInfo
     */
    private void copyValue(XmanBaseInfo newBaseInfo, XmanBaseInfo oldBaseInfo) {
        Field[] fields = XmanBaseInfo.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = XmanBaseInfo.class.getMethod("get" + fieldName);
                Object oldValue = getMethod.invoke(oldBaseInfo);
                Object newValue = getMethod.invoke(newBaseInfo);
                //int类型 新值为0，旧值不为0  或者 新值为空，旧值不为空
                if ((field.getType() == int.class && Integer.parseInt(oldValue.toString()) > 0 &&
                        Integer.parseInt(newValue.toString()) == 0) ||
                        ((newValue == null && oldValue != null))) {
                    Method setMethod = XmanBaseInfo.class.getMethod("set" + fieldName, field.getType());
                    setMethod.invoke(newBaseInfo, oldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public XmanBaseInfo getPatient(String id) {
        return getMapper().getPatient(id);
    }
}
