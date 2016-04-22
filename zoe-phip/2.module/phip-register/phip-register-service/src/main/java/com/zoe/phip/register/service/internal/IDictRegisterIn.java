package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;

/**
 * 字典术语注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IDictRegisterIn {

    /**
     *1)	新增字典类别
     * @param message
     * @return
     */
    ServiceResultT<DictCatalog> addDictCatalogRequest(DictCatalog message);

    /**
     *2)	字典类别更新
     * @param message
     * @return
     */
    ServiceResultT<DictCatalog> updateDictCatalogRequest(DictCatalog message);

    /**
     *3)	字典类别查询
     * @param message
     * @return
     */
    ServiceResultT<DictCatalog> dictCatalogDetailQuery(DictCatalog message);

    /**
     *4)	字典类别删除
     * @param catalogId
     * @return
     */
    ServiceResult dictCatalogDetailDelete(String catalogId);

    /**
     *5)	新增字典项
     * @param message
     * @return
     */
    ServiceResultT<DictItem> addDictItemRequest(DictItem message);

    /**
     *6)	字典项更新
     * @param message
     * @return
     */
    ServiceResultT<DictItem> updateDictItemRequest(DictItem message);

    /**
     *7)	字典项查询
     * @param message
     * @return
     */
    ServiceResultT<DictItem> dictItemDetailQuery(DictItem message);

    /**
     *8)	字典项删除
     * @param dictItemId
     * @return
     */
    ServiceResult dictItemDetailDelete(String dictItemId);
}
