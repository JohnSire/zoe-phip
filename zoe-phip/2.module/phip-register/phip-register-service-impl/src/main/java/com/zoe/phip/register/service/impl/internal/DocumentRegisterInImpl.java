package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.UtilString;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IEhrDataInfoMapper;
import com.zoe.phip.register.dao.IXmanEhrContentMapper;
import com.zoe.phip.register.dao.IXmanIndexMapper;
import com.zoe.phip.register.model.XmanEhr;
import com.zoe.phip.register.model.XmanEhrContent;
import com.zoe.phip.register.model.XmanIndex;
import com.zoe.phip.register.service.internal.IDocumentRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zengjiyang on 2016/5/5.
 */
@Repository("DocumentRegisterIn")
@Service(interfaceClass = IDocumentRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于注册档案的患者基本信息不存在，注册失败")
public class DocumentRegisterInImpl extends BaseInServiceImpl<XmanEhr, IEhrDataInfoMapper> implements IEhrDataInfoMapper{

    @Autowired
    private IXmanIndexMapper xmanIndexMapper;
    @Autowired
    private IXmanEhrContentMapper xmanEhrContentMapper;

    @Override
    public int defaultUpdate(XmanEhr ehrDataInfo) {
        return getMapper().defaultUpdate(ehrDataInfo);
    }


    public String getXmanId(Map<String, Object> args) {
        return xmanIndexMapper.getXmanId(args);
    }

    public XmanIndex addDocumentRegistry(XmanIndex xmanIndex, XmanEhr xmanEhr, XmanEhrContent xmanEhrContent) throws Exception {
        //根据身份证号和患者ID获取xmanId
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("idNo",xmanIndex.getIdNo());
        paras.put("patientId",xmanEhr.getPatientId());
        String xmanId = xmanIndexMapper.getXmanId(paras);
        if(UtilString.isEmptyOrNullWildcard(xmanId)){
            throw new BusinessException("001");
        }
        String indexId = StringUtil.getUUID();
        xmanIndex.setId(indexId);
        xmanIndex.setXmanId(xmanId);
        xmanIndexMapper.insertSelective(xmanIndex);

        String ehrId = StringUtil.getUUID();
        xmanEhr.setId(ehrId);
        xmanEhr.setIndexId(xmanIndex.getId());
        xmanEhr.setXmanId(xmanId);
        xmanEhr.setCreateAt(new Date());
        xmanEhr.setModifyAt(new Date());
        super.add(xmanEhr);

        xmanEhrContent.setId(ehrId);
        xmanEhrContent.setIndexId(xmanIndex.getId());
        xmanEhrContent.setXmanId(xmanId);
        xmanEhrContent.setCreateAt(new Date());
        xmanEhrContent.setModifyAt(new Date());
        xmanEhrContentMapper.insertSelective(xmanEhrContent);

        xmanIndex.setEhrId(xmanEhr.getId());
        return xmanIndex;
    }
}
