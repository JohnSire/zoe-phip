package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.UtilString;
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
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败！")
@ErrorMessage(code = "004", message = "由于删除字典分类（字典）存在下级分类（字典项），删除失败！")
@ErrorMessage(code = "005", message = "由于删除内容不存在，删除失败！")
@ErrorMessage(code = "006", message = "由于更新内容的编码已存在，更新失败！")
public class DictRegisterInImpl extends BaseInServiceImpl<DictCatalog, IDictCatalogMapper> implements IDictCatalogMapper {

    @Autowired
    private IDictItemMapper dictItemMapper;

    /**********字典分类（字典）-- 开始**********/
    @Override
    public DictCatalog addDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("code", dictCatalog.getCode());
        List<DictCatalog> catalogs = getMapper().selectByExample(example);
        if (catalogs.size() > 0) {
            for (DictCatalog catalog:catalogs) {
                if(catalog.getType() == dictCatalog.getType()) {
                    throw new BusinessException("001");
                }
            }
        }
        //保存到数据库
        dictCatalog.setId(StringUtil.getUUID());
        super.add(dictCatalog);
        return dictCatalog;
    }

    @Override
    public DictCatalog updateDictCatalogRequest(DictCatalog dictCatalog) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictCatalog.class);
        example.createCriteria().andEqualTo("id", dictCatalog.getId());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //编码重复判断
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id", dictCatalog.getId());
        paras.put("code", dictCatalog.getCode());
        int cn = getMapper().dictCatalogExist(paras);
        if (cn > 0) {
            throw new BusinessException("006");
        }
        //保存到数据库
        super.update(dictCatalog);
        return dictCatalog;
    }

    @Override
    public int dictCatalogExist(Map<String, Object> args) {
        return getMapper().dictCatalogExist(args);
    }

    @Override
    public int defaultUpdate(DictCatalog dictCatalog) {
        return getMapper().defaultUpdate(dictCatalog);
    }

    @Override
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
    public DictCatalog dictCatalogDetailQueryById(String dictCatalogId) throws Exception {
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id", dictCatalogId);
        DictCatalog catalog = getMapper().getDictCatalogById(paras);
        if (catalog == null) {
            throw new BusinessException("003");
        }
        return catalog;
    }

    @Override
    public DictCatalog getDictCatalogById(Map<String, Object> args) {
        return getMapper().getDictCatalogById(args);
    }

    @Override
    public boolean dictCatalogDetailDelete(String catalogId) throws Exception {
        //判断删除项是否存在
        if(getMapper().selectByPrimaryKey(catalogId) == null){
            throw new BusinessException("005");
        }
        //判断是否存在字典项或下级
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id",catalogId);

        int count = getMapper().selectChildCountById(paras);
        if (count > 0) {
            throw new BusinessException("004");
        }
        return getMapper().deleteByPrimaryKey(catalogId) > 0;
    }

    @Override
    public int selectChildCountById(Map<String, Object> args) {
        return getMapper().selectChildCountById(args);
    }

    @Override
    public PageList<DictCatalog> dictCatalogTreeQuery() {
        PageList<DictCatalog> pageList = new PageList<DictCatalog>();
        List<DictCatalog> results = getMapper().getDictCatalogTree();
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> getDictCatalogTree() {
        return getMapper().getDictCatalogTree();
    }

    @Override
    public PageList<DictCatalog> dictCatalogListQueryPage(QueryPage queryPage, String key) {
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
        paras.put("type","0");
        List<DictCatalog> results = getMapper().getDictCatalogListPage(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public PageList<DictCatalog> dictListQueryPage(QueryPage queryPage, String key) {
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
        paras.put("type","1");
        List<DictCatalog> results = getMapper().getDictCatalogListPage(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> getDictCatalogListPage(Map<String, Object> args) {
        return getMapper().getDictCatalogListPage(args);
    }

    @Override
    public PageList<DictCatalog> dictCatalogListQueryByPIdPage(String pId, QueryPage queryPage) {
        PageList<DictCatalog> pageList = new PageList<DictCatalog>();
        if(StringUtil.isNullOrWhiteSpace(queryPage.getOrderBy())){
            queryPage.setOrderBy(" pdc.CREATE_AT ");
        }
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("pid",pId);
        List<DictCatalog> results = getMapper().getDictCatalogListByPIdPage(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> getDictCatalogListByPIdPage(Map<String, Object> args) {
        return getMapper().getDictCatalogListByPIdPage(args);
    }

    @Override
    public PageList<DictCatalog> getDictCatalogAndItemListByCode(String catalogCode) {
        PageList<DictCatalog> pageList = new PageList<DictCatalog>();
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("code",catalogCode);
        List<DictCatalog> results = getMapper().getDictCatalogAndItemListByCode(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> getDictCatalogAndItemListByCode(Map<String, Object> args) {
        return getMapper().getDictCatalogAndItemListByCode(args);
    }

    @Override
    public PageList<DictCatalog> dictListWithoutFkCatalog(QueryPage queryPage, String key) {
        PageList<DictCatalog> pageList = new PageList<DictCatalog>();
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("key",key);
        List<DictCatalog> results = getMapper().dictListWithoutFkCatalog(paras);
        PageInfo<DictCatalog> pageInfo = new PageInfo<DictCatalog>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public List<DictCatalog> dictListWithoutFkCatalog(Map<String, Object> args) {
        return getMapper().dictListWithoutFkCatalog(args);
    }

    @Override
    public int updateDictWithFkCatalog(String pId, String catalogIds) {
        String[] ids = UtilString.commaDelimitedListToStringArray(catalogIds);
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("catalogIds",ids);
        paras.put("pId",pId);
        return getMapper().updateDictWithFkCatalog(paras);
    }

    @Override
    public int updateDictWithFkCatalog(Map<String, Object> args) {
        return getMapper().updateDictWithFkCatalog(args);
    }
    /**********字典分类（字典）-- 结束**********/

    /**********字典项 -- 开始 **********/
    @Override
    public DictItem addDictItemRequest(DictItem dictItem) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("code", dictItem.getCode());
        List<DictItem> dictItems = dictItemMapper.selectByExample(example);
        if (dictItems.size() > 0) {
            for (DictItem di:dictItems) {
                if(di.getFkCatalogId().equals(dictItem.getFkCatalogId())) {
                    throw new BusinessException("001");
                }
            }
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
    public DictItem updateDictItemRequest(DictItem dictItem) throws Exception {
        //数据是否存在判断
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("id", dictItem.getId());
        int count = dictItemMapper.selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //编码重复判断
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id", dictItem.getId());
        paras.put("code", dictItem.getCode());
        int cn = dictItemMapper.dictItemExist(paras);
        if (cn > 0) {
            throw new BusinessException("006");
        }
        //保存到数据库
        dictItem.setModifyAt(new Date());
        dictItemMapper.defaultUpdate(dictItem);
        return dictItem;
    }

    @Override
    public DictItem dictItemDetailQuery(String dictItemCode) throws Exception {
        Example example = new Example(DictItem.class);
        example.createCriteria().andEqualTo("code", dictItemCode);
        DictItem dictItem = dictItemMapper.selectByExample(example).get(0);
        if (dictItem == null) {
            throw new BusinessException("003");
        }
        return dictItem;
    }

    @Override
    public DictItem dictItemDetailQueryById(String dictItemId) throws Exception {
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id", dictItemId);
        DictItem dictItem = dictItemMapper.getDictItemById(paras);
        if (dictItem == null) {
            throw new BusinessException("003");
        }
        return dictItem;
    }

    @Override
    public boolean dictItemDetailDelete(String dictItemId) throws Exception {
        if(dictItemMapper.selectByPrimaryKey(dictItemId) == null){
            throw new BusinessException("005");
        }
        return dictItemMapper.deleteByPrimaryKey(dictItemId) > 0;
    }

    @Override
    public boolean dictItemListDelete(String dictItemIds) throws Exception {
        String[] ids = UtilString.commaDelimitedListToStringArray(dictItemIds);
        boolean result = false;
        try {
            result = dictItemMapper.deleteByIds(ids) > 0;
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public PageList<DictItem> dictItemListQueryByCatalogCode(String catalogCode, QueryPage queryPage, String key) {
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
        List<DictItem> results = dictItemMapper.getDictItemListByCatalogCode(paras);
        PageInfo<DictItem> pageInfo = new PageInfo<DictItem>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public PageList<DictItem> dictItemListQueryByCatalogId(String catalogId, QueryPage queryPage, String key) {
        PageList<DictItem> pageList = new PageList<DictItem>();
        if(StringUtil.isNullOrWhiteSpace(queryPage.getOrderBy())){
            queryPage.setOrderBy(" pdi.CREATE_AT ");
        }
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id",catalogId);
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        List<DictItem> results = dictItemMapper.getDictItemListByCatalogId(paras);
        PageInfo<DictItem> pageInfo = new PageInfo<DictItem>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    @Override
    public DictCatalog getDictCategoryOrg(Map<String, Object> args) {
        return getMapper().getDictCategoryOrg(args);
    }
    /**********字典项 -- 结束 **********/
}
