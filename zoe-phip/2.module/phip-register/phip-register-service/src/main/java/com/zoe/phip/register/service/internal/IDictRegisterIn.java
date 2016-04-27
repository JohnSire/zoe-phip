package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;

/**
 * 字典术语注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IDictRegisterIn extends IBaseInService<DictCatalog> {

    /**
     *1)	新增字典类别
     * @param dictCatalog
     * @return
     */
    ServiceResultT<DictCatalog> addDictCatalogRequest(DictCatalog dictCatalog);

    /**
     *2)	字典类别更新
     * @param dictCatalog
     * @return
     */
    ServiceResultT<DictCatalog> updateDictCatalogRequest(DictCatalog dictCatalog);

    /**
     *3)	字典类别查询
     * @param dictCatalogCode
     * @return
     */
    ServiceResultT<DictCatalog> dictCatalogDetailQuery(String dictCatalogCode);

    /**
     *4)	字典类别删除
     * @param catalogId
     * @return
     */
    ServiceResult dictCatalogDetailDelete(SystemData systemData, String catalogId);

    /**
     *5)	新增字典项
     * @param dictItem
     * @return
     */
    ServiceResultT<DictItem> addDictItemRequest(DictItem dictItem);

    /**
     *6)	字典项更新
     * @param dictItem
     * @return
     */
    ServiceResultT<DictItem> updateDictItemRequest(DictItem dictItem);

    /**
     *7)	字典项查询
     * @param dictItemCode
     * @return
     */
    ServiceResultT<DictItem> dictItemDetailQuery(String dictItemCode);

    /**
     *8)	字典项删除
     * @param dictItemId
     * @return
     */
    ServiceResult dictItemDetailDelete(SystemData systemData, String dictItemId);

    /**
     *9)	字典分类（字典）列表查询
     * @param systemData
     * @param pId
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictCatalogListQuery(SystemData systemData, String pId, QueryPage queryPage, String key);

    /**
     *10)	字典项列表查询
     * @param systemData
     * @param catalogCode
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictItem>> dictItemListQuery(SystemData systemData, String catalogCode, QueryPage queryPage, String key);
}
