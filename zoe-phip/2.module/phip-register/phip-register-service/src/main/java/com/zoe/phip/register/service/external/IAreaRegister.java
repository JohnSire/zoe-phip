package com.zoe.phip.register.service.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 行政区域注册服务
 * Created by zengjiyang on 2016/4/11.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IAreaRegister {

    /**
     * 新增行政区域注册
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addAreaRequest")
    String addAreaRequest(@WebParam(name="message") String message);


    /**
     * 行政区域更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updateAreaRequest")
    String updateAreaRequest(@WebParam(name="message")String message);

    /**
     * 行政区域基本信息查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "areaDetailQuery")
    String areaDetailQuery(@WebParam(name="message")String message);

    /**
     * 所辖行政区域信息查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "areaChildrenRegistryQuery")
    String areaChildrenRegistryQuery(@WebParam(name="message")String message);

    /**
     * 历史行政区域信息查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "areaHistoryRegistryQuery")
    String areaHistoryRegistryQuery(@WebParam(name="message")String message);
}
