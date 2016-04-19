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
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

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
    private Parser parser;
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
     * @param message
     * @return
     */
    @Override
    public String addPatientRegistry(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return parser.parseByResource("template/响应消息结果.tbl", MapUtil.createMap(m -> {
                m.put("Model", acknowledgement);
            }));
        }

        Document document = ProcessXmlUtil.load(message);
        XmanBaseInfo baseInfo=null;
        try {
            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            String filePath = "/template/Patient/In/Adapter/PatientRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class,parserDoc);
            baseInfo.setId(StringUtil.getUUID());
            //ReceiverSender
            String rsXmlPath="/template/base/ReceiverSenderAdapter.xml";
            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
            ReceiverSender sr=XmlBeanUtil.toBean(document,ReceiverSender.class,rsParDoc);
            baseInfo.setReceiverSender(sr);
            //xml 验证错误  todo 打开验证功能
            /*if(strResult.contains("error:数据集内容验证错误")){
                return registerFailed(baseInfo,strResult);
            }*/
            //数据是否存在判断
            Example example=new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("healthRecordNo",baseInfo.getHealthRecordNo());
            int count= baseInfoMapper.selectCountByExample(example);
            if(count>0){
                return registerFailed(baseInfo,"由于内容重复注册，注册失败");
            }

            String cardXmlPath = "/template/Patient/In/Adapter/XmanCardAdapter.xml";
            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardXmlPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class,cardParDoc);

            //保存到数据库
            baseInfoMapper.defaultAdd(baseInfo);
            xmanCard.setXcXmanId(baseInfo.getId());
            cardMapper.defaultAdd(xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("Model",baseInfo);
            String result=
              parser.parseByResource("template/patient/out/个人信息注册服务响应信息-正向.tbl", map);
            return result;
        } catch (Exception ex) {
            logger.error("error:",ex);
            return registerFailed(baseInfo,ex.getMessage());
        }
    }

    @Override
    public String updatePatientRegistry(String message) {
        String validateResult=validate(message);
        if(!StringUtil.isNullOrWhiteSpace(validateResult)){
            return validateResult;
        }
        //验证 判断是否存在 更新
        return null;
    }

    @Override
    public String mergePatientRegistry(String message) {
        return null;
    }

    @Override
    public String patientRegistryQuery(String message) {

        return null;
    }


    /**
     * 注册失败返回值
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String registerFailed(XmanBaseInfo baseInfo, String errorMsg){
        Acknowledgement acknowledgement=new Acknowledgement();
        acknowledgement.setTypeCode("AE");
        acknowledgement.setText(errorMsg);
        baseInfo.setAcknowledgement(acknowledgement);
        return parser.parseByResource("template/patient/out/个人信息注册服务响应信息-反向.tbl", MapUtil.createMap(m -> {
            m.put("Model", baseInfo);}));
    }

    private String validate(String xmlInput){
        String strResult = ProcessXmlUtil.verifyMessage(xmlInput);
        return "";
    }



}
