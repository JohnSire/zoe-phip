package com.zoe.phip.register.service.external;

/**
 * Created by zengjiyang on 2016/5/5.
 */
public interface IDocumentRegister {

    /**
     * 档案提交服务
     * @param message
     * @return
     */
    String provideAndRegisterDocumentSet(String message);

    /**
     * 档案预判服务
     * @param message
     * @return
     */
    String documentExistence (String message);

    /**
     *档案检索服务
     * @param message
     * @return
     */
    String getDocumentSetRetrieveInfo(String message);

    /**
     * 档案调阅服务
     * @param message
     * @return
     */
    String retrieveDocumentSet (String message);
}
