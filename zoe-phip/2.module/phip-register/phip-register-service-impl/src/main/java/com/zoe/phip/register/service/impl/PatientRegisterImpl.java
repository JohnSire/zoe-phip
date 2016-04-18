package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IXmanBaseInfoMapper;
import com.zoe.phip.register.dao.IXmanCardMapper;
import com.zoe.phip.register.model.EhrDataInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.model.base.ReceiverSender;
import com.zoe.phip.register.service.IPatientRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {

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
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return parser.parseByResource("template/响应消息结果.tbl", MapUtil.createMap(m -> {
                m.put("Model", acknowledgement);
            }));
        }
        Document document = ProcessXmlUtil.load(message);
        try {
            SAXReader reader = new SAXReader();
            String rsXmlPath="/template/base/ReceiverSenderAdapter.xml";
            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
            ReceiverSender sr=XmlBeanUtil.toBean(document,ReceiverSender.class,rsParDoc);

            String filePath = "/template/Patient/In/Adapter/PatientRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            XmanBaseInfo baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class,parserDoc);
            baseInfo.setId(StringUtil.getUUID());
            String cardXmlPath = "/template/Patient/In/Adapter/XmanCardAdapter.xml";
            Document cardParDoc = reader.read(this.getClass().getResourceAsStream(cardXmlPath));
            XmanCard xmanCard = XmlBeanUtil.toBean(document, XmanCard.class,cardParDoc);

            //保存到数据库
            baseInfoMapper.insertSelective(baseInfo);
            xmanCard.setXcXmanId(baseInfo.getId());
            cardMapper.insertSelective(xmanCard);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            baseInfo.setReceiverSender(sr);

            String result=
              parser.parseByResource("template/patient/out/个人信息注册服务响应信息-正向.tbl", MapUtil.createMap(m -> {
                m.put("Model", baseInfo);}));
            return result;
        } catch (Exception ex) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(ex.getMessage());
            ex.printStackTrace();
        }
        //todo 反向
        return "";
    }

    @Override
    public String updatePatientRegistry(String message) {
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
}
