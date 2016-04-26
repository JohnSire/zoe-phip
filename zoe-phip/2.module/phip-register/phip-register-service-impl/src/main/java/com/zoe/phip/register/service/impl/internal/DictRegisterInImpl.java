package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.dao.IDictItemMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IDictRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("DictRegisterIn")
@Service(interfaceClass = IDictRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class DictRegisterInImpl extends BaseInServiceImpl<DictCatalog, IDictCatalogMapper> implements IDictCatalogMapper {


    @Autowired
    private IDictItemMapper dictItemMapper;

    @Override
    public DictCatalog addDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        super.add(dictCatalog);
        return dictCatalog;
    }

    @Override
    public DictCatalog updateDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        super.update(dictCatalog);
        return dictCatalog;
    }

    @Override
    public DictCatalog dictCatalogDetailQuery(String dictCatalogCode) {
        Example example = new Example(XmanBaseInfo.class);
        example.createCriteria().andEqualTo("code", dictCatalogCode);
        DictCatalog catalog = getMapper().selectByExample(example).get(0);
        return catalog;
    }

    @Override
    public int dictCatalogDetailDelete(String catalogId) {
        return getMapper().deleteByPrimaryKey(catalogId);
    }

    @Override
    public DictItem addDictItemRequest(DictItem dictItem) {
        dictItem.setCreateAt(new Date());
        dictItem.setModifyAt(new Date());
        dictItem.setState(1);//状态设置为1
        dictItemMapper.insertSelective(dictItem);
        return dictItem;
    }

    @Override
    public DictItem updateDictItemRequest(DictItem dictItem) {
        dictItem.setModifyAt(new Date());
        dictItemMapper.updateByPrimaryKey(dictItem);
        return dictItem;
    }

    @Override
    public DictItem dictItemDetailQuery(String dictItemCode) {
        Example example = new Example(XmanBaseInfo.class);
        example.createCriteria().andEqualTo("code", dictItemCode);
        DictItem catalog = dictItemMapper.selectByExample(example).get(0);
        return catalog;
    }
    @Override
    public int dictItemDetailDelete(String dictItemId) {
        return dictItemMapper.deleteByPrimaryKey(dictItemId);
    }


}
