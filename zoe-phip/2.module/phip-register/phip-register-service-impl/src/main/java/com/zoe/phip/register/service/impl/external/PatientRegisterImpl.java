package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.service.external.IPatientRegister;
import com.zoe.phip.register.service.impl.internal.PatientRegisterInImpl;
import com.zoe.phip.register.service.internal.IPatientRegisterIn;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {

    private static final Logger logger = LoggerFactory.getLogger(PatientRegisterImpl.class);

    private final String adapterPath = "/template/patient/input/adapter/PatientRegisterAdapter.xml";
    private final String cardAdapterPath = "/template/patient/input/adapter/XmanCardAdapter.xml";

    @Autowired
    private PatientRegisterInImpl patientRegisterIn;


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
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(adapterPath));
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, parserDoc);

            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(baseInfo, strResult);
            }
            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardAdapterPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class, cardParDoc);

            XmanBaseInfo result = patientRegisterIn.addPatientRegistry(baseInfo, xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
            return registerFailed(baseInfo, msg);
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
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(adapterPath));
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class, parserDoc);
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return updateFailed(baseInfo, strResult);
            }

            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardAdapterPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class, cardParDoc);

            XmanBaseInfo result = patientRegisterIn.updatePatientRegistry(baseInfo, xmanCard);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UPDATE_SUCCESS, result);
        } catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
            return updateFailed(baseInfo, msg);
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
            XmanBaseInfo result = patientRegisterIn.mergePatientRegistry(newPatientId, oldPatientId);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("合并成功");
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_SUCCESS, acknowledgement);
        } catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
            acknowledgement.setText(msg);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_UNION_ERROR, acknowledgement);
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
            String msgId = document.selectSingleNode("//id/@extension").getText();
            acknowledgement.setMsgId(msgId);
            String createTime = document.selectSingleNode("//creationTime/@value").getText();
            acknowledgement.setCreateTime(createTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText(strResult + ",查询失败");
                return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
            }

            XmanBaseInfo result = patientRegisterIn.patientRegistryQuery(patientId);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_SUCCESS, result);
        }
        catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, patientRegisterIn.getClass());
            acknowledgement.setText(msg);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
        }
        catch (Exception ex) {
            acknowledgement.setText(ex.getMessage());
            logger.error("error:", ex);
            return RegisterUtil.registerMessage(RegisterType.PATIENT_QUERY_ERROR, acknowledgement);
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
