package com.zoe.phip.repository.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.UtilString;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.repository.dao.IXmanEhrMapper;
import com.zoe.phip.repository.dao.IXmanEhrContentMapper;
import com.zoe.phip.repository.dao.IXmanIndexMapper;
import com.zoe.phip.repository.model.XmanEhr;
import com.zoe.phip.repository.model.XmanEhrContent;
import com.zoe.phip.repository.model.XmanIndex;
import com.zoe.phip.repository.service.internal.IDocumentRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zengjiyang on 2016/5/5.
 */
@Repository("DocumentRegisterIn")
@Service(interfaceClass = IDocumentRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于注册档案的患者基本信息不存在，注册失败")
@ErrorMessage(code = "002", message = "由于注册档案内容已存在，注册失败")
@ErrorMessage(code = "003", message = "由于调阅档案不存在，预判失败")
public class DocumentRegisterInImpl extends BaseInServiceImpl<XmanEhr, IXmanEhrMapper> implements IXmanEhrMapper {

    @Autowired
    private IXmanIndexMapper xmanIndexMapper;
    @Autowired
    private IXmanEhrContentMapper xmanEhrContentMapper;

    @Override
    public int defaultUpdate(XmanEhr ehrDataInfo) {
        return getMapper().defaultUpdate(ehrDataInfo);
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
        //档案Id不为空时判断档案是否已存在
        if(!UtilString.isEmptyOrNullWildcard(xmanEhr.getId())) {
            Example example = new Example(XmanEhr.class);
            example.createCriteria().andEqualTo("id", xmanEhr.getId());
            int count = getMapper().selectCountByExample(example);
            if (count > 0) {
                throw new BusinessException("002");
            }
        }
        xmanIndex.setId(StringUtil.getUUID());
        xmanIndex.setXmanId(xmanId);
        xmanIndex.setCreateAt(new Date());
        xmanIndex.setModifyAt(new Date());
        xmanIndexMapper.insertSelective(xmanIndex);

        if(UtilString.isEmptyOrNullWildcard(xmanEhr.getId())) {
            xmanEhr.setId(StringUtil.getUUID());
        }
        xmanEhr.setIndexId(xmanIndex.getId());
        xmanEhr.setXmanId(xmanId);
        xmanEhr.setCreateAt(new Date());
        xmanEhr.setModifyAt(new Date());
        super.add(xmanEhr);

        xmanEhrContent.setId(xmanEhr.getId());
        xmanEhrContent.setIndexId(xmanIndex.getId());
        xmanEhrContent.setXmanId(xmanId);
        xmanEhrContent.setCreateAt(new Date());
        xmanEhrContent.setModifyAt(new Date());
        xmanEhrContentMapper.insertSelective(xmanEhrContent);

        XmanIndex result = new XmanIndex();
        result.setId(xmanIndex.getId());
        result.setEhrId(xmanEhr.getId());
        result.setMsgId(xmanIndex.getMsgId());
        return result;
    }

    public XmanIndex documentRegistryQuery(String healthCardId, String identityId, String documentTitle) throws Exception {
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("healthCardId", healthCardId);
        paras.put("identityId", identityId);
        paras.put("documentTitle", documentTitle);
        XmanIndex xmanIndex = xmanIndexMapper.documentRegistryQuery(paras);
        if(xmanIndex == null){
            throw new BusinessException("003");
        }
        return xmanIndex;
    }

    public XmanIndex getDocumentInfo(String repositoryUniqueId,String documentUniqueId) throws Exception{
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("repositoryUniqueId", repositoryUniqueId);
        paras.put("documentUniqueId", documentUniqueId);
        XmanIndex xmanIndex = xmanIndexMapper.getDocumentInfo(paras);
        if(xmanIndex == null){
            throw new BusinessException("003");
        }
        return xmanIndex;
    }
}
