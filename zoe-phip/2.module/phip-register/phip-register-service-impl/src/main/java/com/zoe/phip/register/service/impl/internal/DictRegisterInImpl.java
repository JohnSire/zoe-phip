package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.dao.IDictItemMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.service.internal.IDictRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("DictRegisterIn")
@Service(interfaceClass = IDictRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class DictRegisterInImpl extends BaseInServiceImpl<DictCatalog, IDictCatalogMapper> implements IDictCatalogMapper {

    @Autowired
    private IDictItemMapper dictItemMapper;

    @Override
    @ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
    public DictCatalog addDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("code", dictCatalog.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        dictCatalog.setId(StringUtil.getUUID());
        super.add(dictCatalog);
        return dictCatalog;
    }

    @Override
    @ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
    public DictCatalog updateDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("code", dictCatalog.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        super.update(dictCatalog);
        return dictCatalog;
    }

    @Override
    @ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败！")
    public DictCatalog dictCatalogDetailQuery(String dictCatalogCode) throws Exception {
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("code", dictCatalogCode);
        DictCatalog catalog = getMapper().selectByExample(example).get(0);
        if (catalog == null) {
            throw new BusinessException("003");
        }
        return catalog;
    }

    @Override
    @ErrorMessage(code = "007", message = "由于删除字典分类（字典）存在下级分类（字典项），删除失败！")
    public boolean dictCatalogDetailDelete(String catalogId) throws Exception {
        //判断时候存在字典项或下级
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("Id",catalogId);

        int count = getMapper().selectChildCountById(paras);
        if (count > 0) {
            throw new BusinessException("007");
        }
        return getMapper().deleteByPrimaryKey(catalogId) > 0;
    }

    @Override
    public PageList<DictCatalog> dictCatalogListQuery(QueryPage queryPage, String key) {
        PageList<DictCatalog> pageList = new PageList<DictCatalog>();
        if(StringUtil.isNullOrWhiteSpace(queryPage.getOrderBy())){
            queryPage.setOrderBy(" pdc.CREATE_AT ");
        }
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        List<DictCatalog> results = getMapper().getDictCatalogList(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> getDictCatalogList(Map<String, Object> args) {
        return getMapper().getDictCatalogList(args);
    }

    @Override
    public int selectChildCountById(Map<String, Object> args) {
        return getMapper().selectChildCountById(args);
    }

    @Override
    public DictCatalog dictCatalogDetailQueryById(String dictCatalogId) throws Exception {
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("id", dictCatalogId);
        DictCatalog catalog = getMapper().selectByExample(example).get(0);
        if (catalog == null) {
            throw new BusinessException("003");
        }
        return catalog;
    }

    @Override
    @ErrorMessage(code = "004", message = "由于内容重复注册，注册失败")
    public DictItem addDictItemRequest(DictItem dictItem) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("id", dictItem.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("004");
        }
        //保存到数据库
        dictItem.setId(StringUtil.getUUID());
        dictItem.setCreateAt(new Date());
        dictItem.setModifyAt(new Date());
        dictItem.setState(1);//状态设置为1
        dictItemMapper.insertSelective(dictItem);
        return dictItem;
    }

    @Override
    @ErrorMessage(code = "005", message = "由于更新内容不存在，更新失败")
    public DictItem updateDictItemRequest(DictItem dictItem) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("code", dictItem.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("005");
        }
        //保存到数据库
        dictItem.setModifyAt(new Date());
        dictItemMapper.updateByPrimaryKey(dictItem);
        return dictItem;
    }

    @Override
    @ErrorMessage(code = "006", message = "由于查询内容不存在，查询失败！")
    public DictItem dictItemDetailQuery(String dictItemCode) throws Exception {
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("code", dictItemCode);
        DictItem catalog = dictItemMapper.selectByExample(example).get(0);
        if (catalog == null) {
            throw new BusinessException("006");
        }
        return catalog;
    }

    @Override
    public boolean dictItemDetailDelete(String dictItemId) {
        return dictItemMapper.deleteByPrimaryKey(dictItemId) > 0;
    }

    @Override
    public PageList<DictItem> dictItemListQuery(String catalogCode, QueryPage queryPage, String key) {
        PageList<DictItem> pageList = new PageList<DictItem>();
        if(StringUtil.isNullOrWhiteSpace(queryPage.getOrderBy())){
            queryPage.setOrderBy(" pdi.CREATE_AT ");
        }
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("fkCatalogCode",catalogCode);
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        List<DictItem> results = dictItemMapper.getDictItemList(paras);
        PageInfo<DictItem> pageInfo = new PageInfo<DictItem>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public DictItem dictItemDetailQueryById(String dictItemId) throws Exception {
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("id", dictItemId);
        DictItem catalog = dictItemMapper.selectByExample(example).get(0);
        if (catalog == null) {
            throw new BusinessException("006");
        }
        return catalog;
    }

    @Override
    public int defaultUpdate(DictCatalog t) {
        return getMapper().defaultUpdate(t);
    }
}
