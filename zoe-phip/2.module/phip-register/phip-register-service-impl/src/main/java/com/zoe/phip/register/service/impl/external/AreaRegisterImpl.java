package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.service.external.IAreaRegister;
import com.zoe.phip.register.service.impl.internal.AreaRegisterInImpl;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
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
@Service(interfaceClass = IAreaRegister.class, proxy = "sdpf",protocol = {"webservice"},dynamic = true)
public class AreaRegisterImpl implements IAreaRegister {

    private static final Logger logger = LoggerFactory.getLogger(AreaRegisterImpl.class);

    @Autowired
    private AreaRegisterInImpl areaRegisterIn;


    @Override
    public String addAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo;
        Acknowledgement acknowledgement = new Acknowledgement();
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, null);
            if (ifCodeExist(areaBaseInfo.getCode())) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于内容重复注册，注册失败");
                return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
            }
            areaRegisterIn.add(areaBaseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            return "false:" + ex.getMessage();
        }
        return "success:新增区域信息成功!";
    }

    @Override
    public String updateAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo;
        Acknowledgement acknowledgement = new Acknowledgement();
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class, null);
            if (ifCodeExist(areaBaseInfo.getCode())) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setText("由于更新内容不存在，更新失败!");
                return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
            }
            areaRegisterIn.update(areaBaseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            return "false:" + ex.getMessage();
        }
        return "success:更新区域信息成功!";
    }

    @Override
    public String areaDetailQuery(String message) {
        Document document = null;
        try {
            document = ProcessXmlUtil.load(message);
            String code = document.selectSingleNode("//code/@value").getText();
            Map<String, Object> map = new TreeMap<>();
            map.put("code", code);
            AreaBaseInfo baseInfo = areaRegisterIn.getAreaBaseInfo(map);
            map.clear();
            map = null;
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY,baseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            return "false:" + ex.getMessage();
        }
    }

    @Override
    public String areaChildrenRegistryQuery(String message) {
        Document document = null;
        try {
            document = ProcessXmlUtil.load(message);
            String code = document.selectSingleNode("//code/@value").getText();
            Map<String, Object> map = new TreeMap<>();
            map.put("code", code);
            List<AreaBaseInfo> baseInfoList = areaRegisterIn.getChildren(map);
            map.clear();
            map = null;
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY_CHILDREN,baseInfoList);
        } catch (Exception ex) {
            logger.error("error", ex);
            return "false:" + ex.getMessage();
        }
    }

    @Override
    public String areaHistoryRegistryQuery(String message) {
        Document document = null;
        try {
            document = ProcessXmlUtil.load(message);
            String historyId = document.selectSingleNode("//code/@value").getText();
            Map<String, Object> map = new TreeMap<>();
            map.put("historyId", historyId);
            AreaBaseInfo baseInfo = areaRegisterIn.getAreaBaseInfo(map);
            map.clear();
            map = null;
            return RegisterUtil.registerMessage(RegisterType.AREA_QUERY,baseInfo);
        } catch (Exception ex) {
            logger.error("error", ex);
            return "false:" + ex.getMessage();
        }
    }

    private boolean ifCodeExist(String Code) {
        Example example = new Example(AreaBaseInfo.class);
        example.createCriteria().andEqualTo("Code", Code);
        int count = areaRegisterIn.selectCountByExample(example);
        return count > 0;
    }
}
