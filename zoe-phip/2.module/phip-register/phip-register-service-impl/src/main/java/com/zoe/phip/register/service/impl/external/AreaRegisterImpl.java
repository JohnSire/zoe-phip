package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.external.IAreaRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("AreaRegister")
@Service(interfaceClass = IAreaRegister.class, proxy = "sdpf", dynamic = true)
public class AreaRegisterImpl implements IAreaRegister {

    private static final Logger logger = LoggerFactory.getLogger(AreaRegisterImpl.class);

    @Autowired
    private IAreaBaseInfoMapper areaBaseInfoMapper;

    @Override
    public String addAreaRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        AreaBaseInfo areaBaseInfo;
        try {
            areaBaseInfo = XmlBeanUtil.toBean(document, AreaBaseInfo.class,null);
            areaBaseInfoMapper.insertSelective(areaBaseInfo);
        } catch (Exception ex) {
            logger.error("error",ex);
        }
        return "success";
    }

    @Override
    public String updateAreaRequest(String message) {
        return null;
    }

    @Override
    public String areaDetailQuery(String message) {
        return null;
    }

    @Override
    public String areaChildrenRegistryQuery(String message) {
        return null;
    }

    @Override
    public String areaHistoryRegistryQuery(String message) {
        return null;
    }
}
