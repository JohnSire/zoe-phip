package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.model.base.ReceiverSender;
import com.zoe.phip.register.service.IOrganizationRegister;
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

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("OrganizationRegister")
@Service(interfaceClass = IOrganizationRegister.class, proxy = "sdpf", dynamic = true)
public class OrganizationRegisterImpl implements IOrganizationRegister {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRegisterImpl.class);

    @Autowired
    private Parser parser;

    @Autowired
    private IOrgDeptInfoMapper baseInfoMapper;
/*
    @Autowired
    private BaseInServiceImpl baseInServiceImpl;*/

    @Override
    /**
     * 新增医疗卫生机构注册
     */
    public String addOrganization(String message) {
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
        OrgDeptInfo baseInfo = null;

        try {
            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            String filePath = "/template/org/input/adapter/OrganizationRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, parserDoc);
            baseInfo.setId(StringUtil.getUUID());
            //ReceiverSender
       /*     String rsXmlPath = "/template/base/ReceiverSenderAdapter.xml";
            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
            ReceiverSender sr = XmlBeanUtil.toBean(document, ReceiverSender.class, rsParDoc);
            baseInfo.setReceiverSender(sr);*/
            //xml 验证错误  todo 打开验证功能
            /*if(strResult.contains("error:数据集内容验证错误")){
                return registerFailed(baseInfo,strResult);
            }*/
            //数据是否存在判断
            Example example = new Example(OrgDeptInfo.class);
            example.createCriteria().andEqualTo("code", baseInfo.getCode());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count > 0) {
                return registerFailed(baseInfo, "由于内容重复注册，注册失败");
            }

            baseInfoMapper.defaultAdd(baseInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            /*
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Model", baseInfo);
            String result = parser.parseByResource("template/patient/output/个人信息注册服务响应信息-正向.tbl", map);
            */
            return RegisterUtil.registerMessage(RegisterType.ORG_ADD_SUCCESS, baseInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return registerFailed(baseInfo, ex.getMessage());
        }

    }

    @Override
    public String updateOrganization(String message) {
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
        OrgDeptInfo baseInfo = null;
        try {
            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            String filePath = "/template/org/input/adapter/OrganizationRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, parserDoc);
            //ReceiverSender
            String rsXmlPath = "/template/base/ReceiverSenderAdapter.xml";
            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
            ReceiverSender sr = XmlBeanUtil.toBean(document, ReceiverSender.class, rsParDoc);
            baseInfo.setReceiverSender(sr);
            //xml 验证错误  todo 打开验证功能
            /*if(strResult.contains("error:数据集内容验证错误")){
                return registerFailed(baseInfo,strResult);
            }*/
            //数据是否存在判断
            Example example = new Example(OrgDeptInfo.class);
            example.createCriteria().andEqualTo("code", baseInfo.getCode());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count == 0) {
                return updateFailed(baseInfo, "由于更新内容不存在，更新失败");
            }
            //保存到数据库
            baseInfoMapper.defaultUpdate(baseInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            baseInfo.setAcknowledgement(acknowledgement);
            //String result = parser.parseByResource("template/patient/output/个人信息更新服务响应信息-正向.tbl", map);
            return RegisterUtil.registerMessage(RegisterType.ORG_UPDATE_SUCCESS, baseInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return updateFailed(baseInfo, ex.getMessage());
        }
    }

    @Override
    public String organizationDetailQuery(String message) {
        return null;
    }


    public OrgDeptInfo getOrgDeptInfo(String deptCode,String deptName){
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptCode", deptCode).andEqualTo("deptName",deptName);
        //return (OrgDeptInfo)baseInServiceImpl.getMapper().selectByExample(example).get(0);
        // TODO: 2016/4/18
        return null;
    }


    /**
     * 注册失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String registerFailed(OrgDeptInfo baseInfo, String errorMsg) {
        return responseFailed(baseInfo, errorMsg, RegisterType.ORG_ADD_ERROR);
    }

    /**
     * 更新失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String updateFailed(OrgDeptInfo baseInfo, String errorMsg) {
        return responseFailed(baseInfo, errorMsg, RegisterType.ORG_UPDATE_ERROR);
    }

    private String responseFailed(OrgDeptInfo baseInfo, String errorMsg, String path) {
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setTypeCode("AE");
        acknowledgement.setText(errorMsg);
        baseInfo.setAcknowledgement(acknowledgement);
        return RegisterUtil.registerMessage(path, baseInfo);
    }
}
