package com.zoe.phip.register.service.external;

/**
 * 字典术语注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IDictRegister {

    /**
     * 1)	新增字典类别
     *
     * @param message
     * @return
     */
    String addDictCatalogRequest(String message);

    /**
     * 2)	字典类别更新
     *
     * @param message
     * @return
     */
    String updateDictCatalogRequest(String message);

    /**
     * 3)	字典类别查询
     *
     * @param message
     * @return
     */
    String dictCatalogDetailQuery(String message);

    /**
     * 4)	字典类别删除
     *
     * @param message
     * @return
     */
    String dictCatalogDetailDelete(String message);

    /**
     * 5)	新增字典项
     *
     * @param message
     * @return
     */
    String addDictItemRequest(String message);

    /**
     * 6)	字典项更新
     *
     * @param message
     * @return
     */
    String updateDictItemRequest(String message);

    /**
     * 7)	字典项查询
     *
     * @param message
     * @return
     */
    String dictItemDetailQuery(String message);

    /**
     * 8)	字典项删除
     *
     * @param message
     * @return
     */
    String dictItemDetailDelete(String message);
}
