package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.register.service.external.IAreaRegister;
import com.zoe.phip.register.service.impl.internal.AreaRegisterInImpl;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.module.service.util.RegisterUtil;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("AreaRegister")
@Service(interfaceClass = IAreaRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class AreaRegisterImpl implements IAreaRegister {

    private static final Logger logger = LoggerFactory.getLogger(AreaRegisterImpl.class);

    @Autowired
    private AreaRegisterInImpl areaRegisterIn;


    @Override
    public String addAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo = null;
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, null);
            if (ifCodeExist(areaBaseInfo.getCode())) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于注册内容编码已存在，注册失败");
                areaBaseInfo.setAcknowledgement(acknowledgement);
                return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
            }
            areaRegisterIn.add(areaBaseInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            areaBaseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_SUCCESS, areaBaseInfo);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, areaRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    @Override
    public String updateAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo = null;
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, null);
            if (ifCodeExist(areaBaseInfo.getCode())) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于更新内容编码已存在，更新失败!");
                areaBaseInfo.setAcknowledgement(acknowledgement);
                return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
            }
            areaRegisterIn.update(areaBaseInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            areaBaseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_SUCCESS, areaBaseInfo);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, areaRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    @Override
    public String areaDetailQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        AreaBaseInfo areaBaseInfo = null;
        String errorMsg = "";
        try {
            String id = document.selectSingleNode("//Id/@value").getText();
            areaBaseInfo = areaRegisterIn.getAreaBaseInfo(id);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            areaBaseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_SUCCESS, areaBaseInfo);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, areaRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    @Override
    public String areaChildrenRegistryQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo = null;
        String errorMsg = "";
        try {
            String id = document.selectSingleNode("//Id/@value").getText();
            Map<String, Object> map = new TreeMap<>();
            map.put("id", id);
            List<AreaBaseInfo> baseInfoList = areaRegisterIn.getChildren(map);
            map.clear();
            map = null;
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_CHILDREN, baseInfoList);
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    @Override
    public String areaHistoryRegistryQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        AreaBaseInfo areaBaseInfo = null;
        String errorMsg = "";
        try {
            String historyId = document.selectSingleNode("//Id/@value").getText();
            areaBaseInfo = areaRegisterIn.areaHistoryRegistryQuery(historyId);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            areaBaseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_SUCCESS, areaBaseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    private boolean ifCodeExist(String Code) {
        Example example = new Example(AreaBaseInfo.class);
        example.createCriteria().andEqualTo("Code", Code);
        int count = areaRegisterIn.selectCountByExample(example);
        return count > 0;
    }
}
