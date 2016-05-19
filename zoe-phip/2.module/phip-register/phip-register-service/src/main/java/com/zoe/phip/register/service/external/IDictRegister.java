package com.zoe.phip.register.service.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 字典术语注册服务
 * Created by zengjiyang on 2016/4/11.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IDictRegister {

    /**
     * 1)	新增字典类别
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addDictCatalogRequest")
    String addDictCatalogRequest(@WebParam(name="message")String message);

    /**
     * 2)	字典类别更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updateDictCatalogRequest")
    String updateDictCatalogRequest(@WebParam(name="message")String message);

    /**
     * 3)	字典类别查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addAreaRequest")
    String dictCatalogDetailQuery(@WebParam(name="message")String message);

    /**
     * 4)	字典类别删除
     *
     * @param message
     * @return
     */
    @WebMethod(action = "dictCatalogDetailDelete")
    String dictCatalogDetailDelete(@WebParam(name="message")String message);

    /**
     * 5)	新增字典项
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addDictItemRequest")
    String addDictItemRequest(@WebParam(name="message")String message);

    /**
     * 6)	字典项更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updateDictItemRequest")
    String updateDictItemRequest(@WebParam(name="message")String message);

    /**
     * 7)	字典项查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "dictItemDetailQuery")
    String dictItemDetailQuery(@WebParam(name="message")String message);

    /**
     * 8)	字典项删除
     *
     * @param message
     * @return
     */
    @WebMethod(action = "dictItemDetailDelete")
    String dictItemDetailDelete(@WebParam(name="message")String message);
}
