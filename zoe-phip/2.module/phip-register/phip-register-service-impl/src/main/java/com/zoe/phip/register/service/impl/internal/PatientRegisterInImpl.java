package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("PatientRegisterIn")
@Service(interfaceClass = IPatientRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于合并内容不存在，合并失败")
@ErrorMessage(code = "004", message = "由于查询内容不存在，查询失败")
public class PatientRegisterInImpl extends BaseInServiceImpl<XmanBaseInfo, IXmanBaseInfoMapper> implements IXmanBaseInfoMapper {
    @Autowired
    private IXmanCardMapper cardMapper;

    @Override
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
        xmanBaseInfo.setXmanCard(xmanCard);
        return xmanBaseInfo;
    }

    public XmanBaseInfo addPatientRegistry(XmanBaseInfo xmanBaseInfo) throws Exception {
        XmanCard xmanCard = new XmanCard();
        xmanCard.setXcOrgCode(xmanBaseInfo.getOrgCode());
        xmanCard.setXcCardCode(xmanBaseInfo.getCardCode());
        xmanCard.setHealthRecordNo(xmanBaseInfo.getHealthRecordNo());
        return addPatientRegistry(xmanBaseInfo, xmanCard);
    }

    @Override
    public XmanBaseInfo updatePatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(xmanBaseInfo.getId())) {
            //数据是否存在判断
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("healthRecordNo", xmanBaseInfo.getHealthRecordNo());
            int count = getMapper().selectCountByExample(example);
            if (count == 0) {
                throw new BusinessException("002");
            }
            //保存到数据库
            getMapper().defaultUpdate(xmanBaseInfo);
            cardMapper.defaultUpdate(xmanCard);
            xmanBaseInfo.setXmanCard(xmanCard);
        } else {
            super.update(xmanBaseInfo);
            XmanCard card = cardMapper.getXmanCard(xmanBaseInfo.getId());
            if (card != null) {
                card.setXcCardCode(xmanCard.getXcCardCode());
                card.setHealthRecordNo(xmanCard.getHealthRecordNo());
                cardMapper.updateByPrimaryKeySelective(card);
            }
        }
        return xmanBaseInfo;
    }

    public XmanBaseInfo updatePatientRegistry(XmanBaseInfo xmanBaseInfo) throws Exception {
        XmanCard xmanCard = new XmanCard();
        xmanCard.setXcCardCode(xmanBaseInfo.getCardCode());
        xmanCard.setHealthRecordNo(xmanBaseInfo.getHealthRecordNo());
        return updatePatientRegistry(xmanBaseInfo, xmanCard);
    }

    @Override
    public XmanBaseInfo mergePatientRegistry(String newPatientId, String oldPatientId) throws Exception {
        XmanBaseInfo oldPatient = getMapper().getPatient(oldPatientId);
        XmanBaseInfo newPatient = getMapper().getPatient(newPatientId);
        if (oldPatient == null || newPatient == null || StringUtil.isNullOrWhiteSpace(oldPatient.getHealthRecordNo())
                || StringUtil.isNullOrWhiteSpace(newPatient.getHealthRecordNo())) {
            throw new BusinessException("003");
        }
        //赋值
        copyValue(XmanBaseInfo.class, newPatient, oldPatient);
        //
        //保存到数据库
        getMapper().defaultUpdate(newPatient);
        getMapper().delete(oldPatient);
        //卡的赋值
        XmanCard newCard = cardMapper.getXmanCard(newPatient.getId());
        XmanCard oldCard = cardMapper.getXmanCard(oldPatient.getId());
        if (newCard != null && oldCard != null) {
            copyValue(XmanCard.class, newCard, oldCard);
            newPatient.setXmanCard(newCard);
        }
        return newPatient;
    }

    @Override
    public XmanBaseInfo patientRegistryQuery(String patientId) throws Exception {
        //todo 字典赋值
        XmanBaseInfo baseInfo = getMapper().getPatient(patientId);
        if (baseInfo == null) {
            throw new BusinessException("004");
        }
        XmanCard xmanCard = cardMapper.getXmanCard(baseInfo.getId());
        baseInfo.setXmanCard(xmanCard);
        return baseInfo;
    }

    @Override
    public PageList<XmanBaseInfo> patientRegistryListQuery(String key, QueryPage page) {
        PageList<XmanBaseInfo> pageList = new PageList<XmanBaseInfo>();
        if (StringUtil.isNullOrWhiteSpace(page.getOrderBy())) {
            page.setOrderBy(" B.CREATE_AT ");
        }
        //分页
        SqlHelper.startPage(page);
        Map<String, Object> paras = new HashMap<String, Object>();
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }

        List<XmanBaseInfo> results = getMapper().getPatientList(paras);
        PageInfo<XmanBaseInfo> pageInfo = new PageInfo<XmanBaseInfo>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    /**
     * 将旧实体的值，赋到新实体上
     *
     * @param cl
     * @param newObj
     * @param oldObj
     */
    private void copyValue(Class<?> cl, Object newObj, Object oldObj) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = cl.getMethod("get" + fieldName);
                Object oldValue = getMethod.invoke(oldObj);
                Object newValue = getMethod.invoke(newObj);
                //int类型 新值为0，旧值不为0  或者 新值为空，旧值不为空
                if ((field.getType() == int.class && Integer.parseInt(oldValue.toString()) > 0 &&
                        Integer.parseInt(newValue.toString()) == 0) ||
                        ((newValue == null && oldValue != null))) {
                    Method setMethod = cl.getMethod("set" + fieldName, field.getType());
                    setMethod.invoke(newObj, oldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public XmanBaseInfo getById(String id) throws Exception {
        return getPatientByPrimaryKey(id);
    }

    //region 接口转接
    @Override
    public XmanBaseInfo getPatient(String id) {
        return getMapper().getPatient(id);
    }

    @Override
    public List<XmanBaseInfo> getPatientList(Map<String, Object> args) {
        return getMapper().getPatientList(args);
    }

    @Override
    public int defaultUpdate(XmanBaseInfo t) {
        return getMapper().defaultUpdate(t);
    }

    @Override
    public XmanBaseInfo getPatientByPrimaryKey(String id) {
        return getMapper().getPatientByPrimaryKey(id);
    }
    //endregion
}
