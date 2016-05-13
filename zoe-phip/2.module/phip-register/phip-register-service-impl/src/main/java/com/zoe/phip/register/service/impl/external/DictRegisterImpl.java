package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.service.external.IDictRegister;
import com.zoe.phip.register.service.impl.internal.DictRegisterInImpl;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("DictRegister")
@Service(interfaceClass = IDictRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class DictRegisterImpl implements IDictRegister {

    private static final Logger logger = LoggerFactory.getLogger(DictRegisterImpl.class);
    private final String catalogAdapterPath = "/template/dict/input/adapter/DictCatalogAdapter.xml";
    private final String itemAdapterPath = "/template/dict/input/adapter/DictItemAdapter.xml";

    @Autowired
    private DictRegisterInImpl dictRegisterIn;

    @Override
    public String addDictCatalogRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictCatalog catalog = null;
        try {
            catalog = XmlBeanUtil.toBean(document, DictCatalog.class, ProcessXmlUtil.getAdapterDom(catalogAdapterPath));
            DictCatalog result = dictRegisterIn.addDictCatalogRequest(catalog);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_CATALOG_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(catalog, errorMsg, RegisterType.DICT_CATALOG_ADD_ERROR);
    }

    @Override
    public String updateDictCatalogRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictCatalog catalog = null;
        try {
            catalog = XmlBeanUtil.toBean(document, DictCatalog.class, ProcessXmlUtil.getAdapterDom(catalogAdapterPath));
            DictCatalog result = dictRegisterIn.updateDictCatalogRequest(catalog);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_CATALOG_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(catalog, errorMsg, RegisterType.DICT_CATALOG_ADD_ERROR);
    }

    @Override
    public String dictCatalogDetailQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictCatalog result = null;
        try {
            String id = document.selectSingleNode("//Id/@value").getText().trim();//字典分类ID
            result = dictRegisterIn.dictCatalogDetailQueryById(id);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_CATALOG_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(result, errorMsg, RegisterType.DICT_CATALOG_ADD_ERROR);
    }

    @Override
    public String dictCatalogDetailDelete(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictCatalog result = null;
        try {
            String id = document.selectSingleNode("//Id/@value").getText().trim();//字典分类ID
            result.setId(id);
            dictRegisterIn.dictCatalogDetailDelete(id);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("删除成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_CATALOG_DELETE_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(result, errorMsg, RegisterType.DICT_CATALOG_ADD_ERROR);
    }

    @Override
    public String addDictItemRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictItem item = null;
        try {
            item = XmlBeanUtil.toBean(document, DictItem.class, ProcessXmlUtil.getAdapterDom(itemAdapterPath));
            DictItem result = dictRegisterIn.addDictItemRequest(item);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_ITEM_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(item, errorMsg, RegisterType.DICT_ITEM_ADD_ERROR);
    }

    @Override
    public String updateDictItemRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictItem item = null;
        try {
            item = XmlBeanUtil.toBean(document, DictItem.class, ProcessXmlUtil.getAdapterDom(itemAdapterPath));
            DictItem result = dictRegisterIn.updateDictItemRequest(item);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_ITEM_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(item, errorMsg, RegisterType.DICT_ITEM_ADD_ERROR);
    }

    @Override
    public String dictItemDetailQuery(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictItem result = null;
        try {
            String id = document.selectSingleNode("//Id/@value").getText().trim();//字典项ID
            result = dictRegisterIn.dictItemDetailQueryById(id);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_ITEM_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(result, errorMsg, RegisterType.DICT_ITEM_ADD_ERROR);
    }

    @Override
    public String dictItemDetailDelete(String message) {
        Document document = ProcessXmlUtil.load(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        DictItem result = null;
        try {
            String id = document.selectSingleNode("//Id/@value").getText().trim();//字典项ID
            result.setId(id);
            dictRegisterIn.dictItemDetailDelete(id);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("删除成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DICT_ITEM_DELETE_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, dictRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error", ex);
            errorMsg = ex.getMessage();
        }
        return RegisterUtil.responseFailed(result, errorMsg, RegisterType.DICT_CATALOG_ADD_ERROR);
    }
}
