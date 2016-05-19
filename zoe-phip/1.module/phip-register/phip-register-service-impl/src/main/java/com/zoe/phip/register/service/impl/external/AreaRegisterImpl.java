package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.config.PropertyPlaceholder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.module.service.util.RegisterUtil;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.external.IAreaRegister;
import com.zoe.phip.register.service.impl.internal.AreaRegisterInImpl;
import com.zoe.phip.register.util.RegisterType;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    private final String adapterPath = "/template/area/input/adapter/AreaRegisterAdapter.xml";

    @Autowired
    private AreaRegisterInImpl areaRegisterIn;


    @Override
    public String addAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo = new AreaBaseInfo();
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, ProcessXmlUtil.getAdapterDom(adapterPath));
            if (StringUtil.isNullOrWhiteSpace(areaBaseInfo.getCode()) || StringUtil.isNullOrWhiteSpace(areaBaseInfo.getName())) {
                errorMsg = "必填字段值为空，注册失败";
                return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
            }
            if (ifCodeExist(areaBaseInfo.getCode())) {
                errorMsg = "由于注册内容编码已存在，注册失败";
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
        AreaBaseInfo areaBaseInfo = new AreaBaseInfo();
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, ProcessXmlUtil.getAdapterDom(adapterPath));
            areaBaseInfo.setId(document.selectSingleNode(PropertyPlaceholder.getProperty("queryArea.areaId")).getText());
            if (StringUtil.isNullOrWhiteSpace(areaBaseInfo.getCode()) || StringUtil.isNullOrWhiteSpace(areaBaseInfo.getName())) {
                errorMsg = "必填字段值为空，更新失败";
                return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
            }
            if (ifCodeExist(areaBaseInfo.getCode())) {
                errorMsg = "由于更新内容编码已存在，更新失败!";
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
        AreaBaseInfo areaBaseInfo = new AreaBaseInfo();
        String errorMsg = "";
        try {
            String id = document.selectSingleNode(PropertyPlaceholder.getProperty("queryArea.areaId")).getText();
            areaBaseInfo.setId(id);
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
        AreaBaseInfo areaBaseInfo = new AreaBaseInfo();
        List<AreaBaseInfo> baseInfoList = new ArrayList<>();
        String errorMsg = "";
        String id = "";
        try {
            id = document.selectSingleNode(PropertyPlaceholder.getProperty("queryAreaChildren.areaId")).getText();
            Map<String, Object> map = new TreeMap<>();
            map.put("id", id);
            baseInfoList = areaRegisterIn.getChildren(map);
            map.clear();
            map = null;
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_CHILDREN, baseInfoList);
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
            if (baseInfoList == null) {
                areaBaseInfo.setId(id);
                errorMsg = "由于查询内容不存在，查询失败";
            }
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    @Override
    public String areaHistoryRegistryQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        AreaBaseInfo areaBaseInfo = new AreaBaseInfo();
        String errorMsg = "";
        String historyId = "";
        try {
            historyId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryAreaHistory.areaId")).getText();
            areaBaseInfo.setId(historyId);
            areaBaseInfo = areaRegisterIn.areaHistoryRegistryQuery(historyId);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            areaBaseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_SUCCESS, areaBaseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
            if (areaBaseInfo == null) {
                errorMsg = "由于查询内容不存在，查询失败";
                areaBaseInfo = new AreaBaseInfo();
                areaBaseInfo.setId(historyId);
            }
        }
        return RegisterUtil.responseFailed(areaBaseInfo, errorMsg, RegisterType.AREA_QUERY_ERROR);
    }

    private boolean ifCodeExist(String Code) {
        Example example = new Example(AreaBaseInfo.class);
        example.createCriteria().andEqualTo("code", Code);
        int count = areaRegisterIn.selectCountByExample(example);
        return count > 0;
    }
}
