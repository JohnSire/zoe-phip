package com.zoe.phip.register.service.external;

/**
 * 病人注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IPatientRegister {

    /**
     * 个人信息新增
     *
     * @param message
     * @return
     */
    String addPatientRegistry(String message);

    /**
     * 个人信息更新
     *
     * @param message
     * @return
     */
    String updatePatientRegistry(String message);

    /**
     * 个人身份合并
     *
     * @param message
     * @return
     */
    String mergePatientRegistry(String message);

    /**
     * 个人基本信息查询
     *
     * @param message
     * @return
     */
    String patientRegistryQuery(String message);

}
