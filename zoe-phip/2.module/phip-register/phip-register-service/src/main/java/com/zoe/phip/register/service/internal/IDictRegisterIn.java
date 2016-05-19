package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;

import java.util.List;

/**
 * 字典术语注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IDictRegisterIn extends IBaseInService<DictCatalog> {

    /**
     * 1)	新增字典类别
     *
     * @param dictCatalog
     * @return
     */
    ServiceResultT<DictCatalog> addDictCatalogRequest(SystemData systemData, DictCatalog dictCatalog);

    /**
     * 2)	字典类别更新
     *
     * @param dictCatalog
     * @return
     */
    ServiceResultT<DictCatalog> updateDictCatalogRequest(SystemData systemData, DictCatalog dictCatalog);

    /**
     * 3)	根据字典类别id查询
     *
     * @param dictCatalogId
     * @return
     */
    ServiceResultT<DictCatalog> dictCatalogDetailQueryById(SystemData systemData, String dictCatalogId);

    /**
     * 4)	根据字典类别code查询
     *
     * @param dictCatalogCode
     * @return
     */
    ServiceResultT<DictCatalog> dictCatalogDetailQuery(SystemData systemData, String dictCatalogCode);

    /**
     * 5)	字典类别删除
     *
     * @param catalogId
     * @return
     */
    ServiceResult dictCatalogDetailDelete(SystemData systemData, String catalogId);

    /**
     * 6)	字典分类（字典）列表查询（用于构造树）
     *
     * @param systemData
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictCatalogTreeQuery(SystemData systemData, String codes, String key);

    /**
     * 7)	字典分类列表查询(不包含字典)
     *
     * @param systemData
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictCatalogListQueryPage(SystemData systemData, QueryPage queryPage, String key);

    /**
     * 8)	根据PID逐级获取字典分类列表（包括字典）
     *
     * @param systemData
     * @param pId
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictCatalogListQueryByPIdPage(SystemData systemData, String pId, QueryPage queryPage);


    /**
     * 9)	新增字典项
     *
     * @param dictItem
     * @return
     */
    ServiceResultT<DictItem> addDictItemRequest(SystemData systemData, DictItem dictItem);

    /**
     * 10)	字典项更新
     *
     * @param dictItem
     * @return
     */
    ServiceResultT<DictItem> updateDictItemRequest(SystemData systemData, DictItem dictItem);

    /**
     * 11)	根据字典项id查询
     *
     * @param dictItemId
     * @return
     */
    ServiceResultT<DictItem> dictItemDetailQueryById(SystemData systemData, String dictItemId);

    /**
     * 12)	根据字典项code查询
     *
     * @param dictItemCode
     * @return
     */
    ServiceResultT<DictItem> dictItemDetailQuery(SystemData systemData, String dictItemCode);

    /**
     * 13)	字典项删除
     *
     * @param dictItemId
     * @return
     */
    ServiceResult dictItemDetailDelete(SystemData systemData, String dictItemId);

    /**
     * 14)	字典项批量删除
     *
     * @param dictItemIds
     * @return
     */
    ServiceResult dictItemListDelete(SystemData systemData, String dictItemIds);

    /**
     * 15)	根据字典Code获取字典项列表
     *
     * @param systemData
     * @param catalogCode
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictItem>> dictItemListQueryByCatalogCode(SystemData systemData, String catalogCode, QueryPage queryPage, String key);

    /**
     * 16)	根据字典ID获取字典项列表
     *
     * @param systemData
     * @param catalogId
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictItem>> dictItemListQueryByCatalogId(SystemData systemData, String catalogId, QueryPage queryPage, String key);

    /**
     * 17)	根据字典分类（字典）Code获取所有子级字典分类（字典）和字典项列表
     *
     * @param systemData
     * @param catalogCode
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictCatalogAndItemListByCode(SystemData systemData, String catalogCode);

    /**
     * 18)	获取字典列表（不包含分类）
     *
     * @param systemData
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictListQueryPage(SystemData systemData, QueryPage queryPage, String key);

    /**
     * 19)	获取字典列表（不包含分类且字典不属于任何分类）
     *
     * @param systemData
     * @param queryPage
     * @param key
     * @return
     */
    ServiceResultT<PageList<DictCatalog>> dictListWithoutFkCatalog(SystemData systemData, QueryPage queryPage, String key);

    /**
     * 20)	批量更新字典所属分类
     *
     * @param systemData
     * @param pId
     * @param catalogIds
     * @return
     */
    ServiceResult updateDictWithFkCatalog(SystemData systemData, String pId, String catalogIds);

}
