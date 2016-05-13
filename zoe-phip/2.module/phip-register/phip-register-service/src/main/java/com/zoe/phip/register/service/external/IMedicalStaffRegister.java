package com.zoe.phip.register.service.external;

import com.zoe.phip.register.model.MedicalStaffInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 医护人员注册服务
 * Created by zengjiyang on 2016/4/11.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IMedicalStaffRegister {
    /**
     * 医护人员注册
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addProvider")
    String addProvider(@WebParam(name="message")String message);

    /**
     * 医护人员信息更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updateProvider")
    String updateProvider(@WebParam(name="message")String message);

    /**
     * 医护人员查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "providerDetailsQuery")
    String providerDetailsQuery(@WebParam(name="message")String message);


}
