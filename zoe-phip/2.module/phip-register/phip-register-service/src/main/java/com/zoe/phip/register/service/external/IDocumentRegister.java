package com.zoe.phip.register.service.external;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Created by zengjiyang on 2016/5/5.
 */
@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface IDocumentRegister {

    /**
     * 档案提交服务
     * @param message
     * @return
     */
    @WebMethod(action = "provideAndRegisterDocumentSet")
    String provideAndRegisterDocumentSet(@WebParam(name="message")String message);

    /**
     * 档案预判服务
     * @param message
     * @return
     */
    @WebMethod(action = "documentExistence")
    String documentExistence (@WebParam(name="message")String message);

    /**
     *档案检索服务
     * @param message
     * @return
     */
    @WebMethod(action = "getDocumentSetRetrieveInfo")
    String getDocumentSetRetrieveInfo(@WebParam(name="message")String message);

    /**
     * 档案调阅服务
     * @param message
     * @return
     */
    @WebMethod(action = "retrieveDocumentSet")
    String retrieveDocumentSet (@WebParam(name="message")String message);
}
