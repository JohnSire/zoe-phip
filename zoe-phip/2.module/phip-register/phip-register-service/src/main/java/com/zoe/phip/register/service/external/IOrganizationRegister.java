package com.zoe.phip.register.service.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 医疗机构注册服务
 * Created by zengjiyang on 2016/4/11.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IOrganizationRegister {

    /**
     * 新增医疗卫生机构注册
     *
     * @param message
     * @return
     */
    @WebMethod(action = "addOrganization")
    String addOrganization(@WebParam(name="message")String message);

    /**
     * 医疗卫生机构信息更新
     *
     * @param message
     * @return
     */
    @WebMethod(action = "updateOrganization")
    String updateOrganization(@WebParam(name="message")String message);

    /**
     * 医疗卫生机构信息查询
     *
     * @param message
     * @return
     */
    @WebMethod(action = "organizationDetailQuery")
    String organizationDetailQuery(@WebParam(name="message")String message);


}
