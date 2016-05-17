package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.config.PropertyPlaceholder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;
import com.zoe.phip.register.service.external.IPatientRegister;
import com.zoe.phip.register.service.impl.internal.AreaRegisterInImpl;
import com.zoe.phip.register.service.impl.internal.PatientRegisterInImpl;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.module.service.util.RegisterUtil;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {

    private static final Logger logger = LoggerFactory.getLogger(PatientRegisterImpl.class);

    private final String adapterPath = "/template/patient/input/adapter/PatientRegisterAdapter.xml";

    @Autowired
    private PatientRegisterInImpl patientRegisterIn;

    @Autowired
    private AreaRegisterInImpl areaRegisterIn;

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
            XmanBaseInfo xmanBaseInfo=new XmanBaseInfo();
            xmanBaseInfo.setMsgId(StringUtil.getUUID());
            xmanBaseInfo.setCreateTime(new Date());
            return registerFailed(xmanBaseInfo, strResult);
        }

        Document document = ProcessXmlUtil.load(message);
        String errorMsg = "";
        XmanBaseInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, ProcessXmlUtil.getAdapterDom(adapterPath));
            if(baseInfo!=null&& !StringUtil.isNullOrWhiteSpace(baseInfo.getValidateMessage())){
                throw new Exception(baseInfo.getValidateMessage());
            }
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(baseInfo, strResult);
            }
            XmanCard xmanCard = new XmanCard();
            xmanCard.setXcCardCode(baseInfo.getCardCode());
            xmanCard.setXcOrgCode(baseInfo.getXcOrgCode());
            xmanCard.setHealthRecordNo(baseInfo.getHealthRecordNo());
            //将地址转换为对应code
            baseInfoSetCode(baseInfo);

            XmanBaseInfo result = patientRegisterIn.addPatientRegistry(baseInfo, xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        return registerFailed(baseInfo, errorMsg);

    }

    @Override
    public String updatePatientRegistry(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            XmanBaseInfo xmanBaseInfo=new XmanBaseInfo();
            xmanBaseInfo.setMsgId(StringUtil.getUUID());
            xmanBaseInfo.setCreateTime(new Date());
            return updateFailed(xmanBaseInfo, strResult);
        }
        Document document = ProcessXmlUtil.load(message);
        XmanBaseInfo baseInfo = null;
        String errorMsg = "";
        try {

            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, ProcessXmlUtil.getAdapterDom(adapterPath));
            if(baseInfo!=null&& !StringUtil.isNullOrWhiteSpace(baseInfo.getValidateMessage())){
                throw new Exception(baseInfo.getValidateMessage());
            }
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return updateFailed(baseInfo, strResult);
            }
            //将地址转换为对应code
            baseInfoSetCode(baseInfo);

            XmanCard xmanCard = new XmanCard();
            xmanCard.setXcCardCode(baseInfo.getCardCode());
            xmanCard.setXcOrgCode(baseInfo.getXcOrgCode());
            xmanCard.setHealthRecordNo(baseInfo.getHealthRecordNo());
            XmanBaseInfo result = patientRegisterIn.updatePatientRegistry(baseInfo, xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UPDATE_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        return updateFailed(baseInfo, errorMsg);
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
        String errorMsg = "";
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            acknowledgement.setMsgId(StringUtil.getUUID());
            acknowledgement.setCreateTime(DateUtil.dateTimeToString(new Date(),"yyyyMMddHHmmss"));
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
        }
        try {
            Document document = ProcessXmlUtil.load(message);
            String oldPatientId = document.selectSingleNode(PropertyPlaceholder.getProperty("mergePatient.oldPatientId")).getText();
            String newPatientId = document.selectSingleNode(PropertyPlaceholder.getProperty("mergePatient.newPatientId")).getText();
            String msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("mergePatient.msgId")).getText();
            acknowledgement.setMsgId(msgId);
            String createTime = document.selectSingleNode(PropertyPlaceholder.getProperty("mergePatient.createTime")).getText();
            acknowledgement.setCreateTime(createTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText(strResult + ",合并失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
            }
            XmanBaseInfo result = patientRegisterIn.mergePatientRegistry(newPatientId, oldPatientId);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("合并成功");
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_SUCCESS, acknowledgement);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
        } catch (Exception ex) {
            acknowledgement.setTypeCode("AE");
            errorMsg = ex.getMessage();
        }
        acknowledgement.setText(errorMsg);
        return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
    }

    @Override
    public String patientRegistryQuery(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            acknowledgement.setMsgId(StringUtil.getUUID());
            acknowledgement.setCreateTime(DateUtil.dateTimeToString(new Date(),"yyyyMMddHHmmss"));
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
        }
        try {
            Document document = ProcessXmlUtil.load(message);
            String patientId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryPatient.patientId")).getText();
            String msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryPatient.msgId")).getText();
            acknowledgement.setMsgId(msgId);
            String createTime = document.selectSingleNode(PropertyPlaceholder.getProperty("queryPatient.createTime")).getText();
            acknowledgement.setCreateTime(createTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText(strResult + ",查询失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
            }

            XmanBaseInfo result = patientRegisterIn.patientRegistryQuery(patientId);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
        } catch (Exception ex) {
            errorMsg = ex.getMessage();
            logger.error("error:", ex);
        }
        acknowledgement.setText(errorMsg);
        return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
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

    private void baseInfoSetCode(XmanBaseInfo baseInfo){
        baseInfo.setCityCode(getAreaByName(baseInfo.getCityCodeName()));
        baseInfo.setProvinceCode(getAreaByName(baseInfo.getProvinceCodeName()));
        baseInfo.setCountyCode(getAreaByName(baseInfo.getCountyCodeName()));
        baseInfo.setNeighborhoodCode(getAreaByName(baseInfo.getNeighborhoodCodeName()));
        baseInfo.setStreetCode(getAreaByName(baseInfo.getStreetCodeName()));
    }

    private String getAreaByName(String name){
        AreaBaseInfo areaBaseInfo= areaRegisterIn.getAreaByName(name);
        if(areaBaseInfo!=null)
            return areaBaseInfo.getCode();
        else
            return null;
    }
}
