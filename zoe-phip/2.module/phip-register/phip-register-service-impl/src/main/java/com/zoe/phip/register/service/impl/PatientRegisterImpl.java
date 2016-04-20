package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IXmanBaseInfoMapper;
import com.zoe.phip.register.dao.IXmanCardMapper;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.model.base.ReceiverSender;
import com.zoe.phip.register.service.IPatientRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {

    private static final Logger logger = LoggerFactory.getLogger(PatientRegisterImpl.class);

    @Autowired
    private IXmanBaseInfoMapper baseInfoMapper;

    @Autowired
    private IXmanCardMapper cardMapper;

    /**
     * 新增个人信息注册
     * 1.验证参数合法
     * 2.通过适配xml转成指定实体
     * 3.补全数据库实体
     * 4.执行数据库
     * 5.操作实体 AE AA
     *
     * @param message
     * @return
     */
    @Override
    public String addPatientRegistry(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }

        Document document = ProcessXmlUtil.load(message);
        XmanBaseInfo baseInfo = null;
        try {
            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            String filePath = "/template/patient/input/Adapter/PatientRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, parserDoc);
            baseInfo.setId(StringUtil.getUUID());

            //xml 验证错误
            if(strResult.contains("error:数据集内容验证错误")){
                return registerFailed(baseInfo,strResult);
            }
            //数据是否存在判断
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("healthRecordNo", baseInfo.getHealthRecordNo());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count > 0) {
                return registerFailed(baseInfo, "由于内容重复注册，注册失败");
            }

            String cardXmlPath = "/template/patient/input/Adapter/XmanCardAdapter.xml";
            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardXmlPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class, cardParDoc);

            //保存到数据库
            baseInfoMapper.defaultAdd(baseInfo);
            xmanCard.setXcXmanId(baseInfo.getId());
            cardMapper.defaultAdd(xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_ADD_SUCUESS, baseInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return registerFailed(baseInfo, ex.getMessage());
        }
    }

    @Override
    public String updatePatientRegistry(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        XmanBaseInfo baseInfo = null;
        try {
            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            String filePath = "/template/patient/input/Adapter/PatientRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, parserDoc);
            //xml 验证错误
            if(strResult.contains("error:数据集内容验证错误")){
                return registerFailed(baseInfo,strResult);
            }
            //数据是否存在判断
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("healthRecordNo", baseInfo.getHealthRecordNo());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count == 0) {
                return updateFailed(baseInfo, "由于更新内容不存在，更新失败");
            }

            String cardXmlPath = "/template/patient/input/Adapter/XmanCardAdapter.xml";
            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardXmlPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class, cardParDoc);

            //保存到数据库
            baseInfoMapper.defaultUpdate(baseInfo);
            cardMapper.defaultUpdate(xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UPDATE_SUCUESS, baseInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return updateFailed(baseInfo, ex.getMessage());
        }

    }

    /**
     * 合并病人信息
     * 1.找到new和old两个病人信息
     * 2.如果new中病人信息为空，而old中不为空，则将old值赋到new中
     * 3.更新new 删除old
     *
     * @param message
     * @return
     */
    @Override
    public String mergePatientRegistry(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        try {
            Document document = ProcessXmlUtil.load(message);
            String oldPatientId = document.selectSingleNode("//controlActProcess/subject/registrationEvent/replacementOf/priorRegistration/subject1/priorRegisteredRole/id/@extension").getText();
            String newPatientId = document.selectSingleNode("//controlActProcess/subject/registrationEvent/subject1/patient/id/@extension").getText();
            String msgId = document.selectSingleNode("//id/@extension").getText();
            acknowledgement.setMsgId(msgId);
            String createTime = document.selectSingleNode("//creationTime/@value").getText();
            acknowledgement.setCreateTime(createTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText(strResult + ",合并失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
            }
            XmanBaseInfo oldPatient = baseInfoMapper.getPatient(oldPatientId);
            XmanBaseInfo newPatient = baseInfoMapper.getPatient(newPatientId);
            if (oldPatient == null || newPatient == null || StringUtil.isNullOrWhiteSpace(oldPatient.getHealthRecordNo())
                    || StringUtil.isNullOrWhiteSpace(newPatient.getHealthRecordNo())) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于合并内容不存在，合并失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
            }
            //赋值
            copyValue(newPatient, oldPatient);
            //保存到数据库
            baseInfoMapper.defaultUpdate(newPatient);
            baseInfoMapper.delete(oldPatient);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("合并成功");
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_SUCUESS, acknowledgement);
        } catch (Exception ex) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(ex.getMessage());
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
        }
    }

    @Override
    public String patientRegistryQuery(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        try {
            Document document = ProcessXmlUtil.load(message);
            String patientId = document.selectSingleNode("//controlActProcess/queryByParameter/parameterList/livingSubjectId/value/@extension").getText();
            String msgId=document.selectSingleNode("//id/@extension").getText();
            acknowledgement.setMsgId(msgId);
            String createTime = document.selectSingleNode("//creationTime/@value").getText();
            acknowledgement.setCreateTime(createTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText(strResult + ",查询失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
            }
            XmanBaseInfo baseInfo=baseInfoMapper.getPatient(patientId);
            if(baseInfo==null){
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于查询内容不存在，查询失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
            }
            XmanCard xmanCard=cardMapper.getXmanCard(baseInfo.getId());
            //todo 字典赋值
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_SUCUESS, baseInfo);

        }catch (Exception ex){
            logger.error("error:",ex);
        }
        return null;
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


    /**
     * 注册失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String registerFailed(XmanBaseInfo baseInfo, String errorMsg) {
        return RegisterUtil.responseFailed(baseInfo, errorMsg, RegisterType.PATIENT_ADD_ERROR);
    }

    /**
     * 更新失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String updateFailed(XmanBaseInfo baseInfo, String errorMsg) {
        return RegisterUtil.responseFailed(baseInfo, errorMsg, RegisterType.PATIENT_UPDATE_ERROR);
    }

}
