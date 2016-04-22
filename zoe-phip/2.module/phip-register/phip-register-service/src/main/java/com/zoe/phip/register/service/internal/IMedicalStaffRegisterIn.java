package com.zoe.phip.register.service.internal;

/**医护人员注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IMedicalStaffRegisterIn {
    /**
     * 医护人员注册
     * @param message
     * @return
     */
    String addProvider(String message);

    /**
     * 	医护人员信息更新
     * @param message
     * @return
     */
    String updateProvider(String message);

    /**
     * 医护人员查询
     * @param message
     * @return
     */
    String providerDetailsQuery(String message);
}
