package com.zoe.phip.register.service.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 病人注册服务
 * Created by zengjiyang on 2016/4/11.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IPatientRegister {

    /**
     * 个人信息新增
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addPatientRegistry")
    String addPatientRegistry(@WebParam(name="message")String message);

    /**
     * 个人信息更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updatePatientRegistry")
    String updatePatientRegistry(@WebParam(name="message")String message);

    /**
     * 个人身份合并
     *
     * @param message
     * @return
     */
    @WebMethod(action = "mergePatientRegistry")
    String mergePatientRegistry(@WebParam(name="message")String message);

    /**
     * 个人基本信息查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "patientRegistryQuery")
    String patientRegistryQuery(@WebParam(name="message")String message);

}
