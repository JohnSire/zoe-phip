package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.XmanBaseInfo;

/**
 * 病人注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IPatientRegisterIn {

    /**
     * 个人信息新增
     * @param message
     * @return
     */
    ServiceResultT<XmanBaseInfo> addPatientRegistry(XmanBaseInfo message);

    /**
     * 个人信息更新
     * @param message
     * @return
     */
    ServiceResultT<XmanBaseInfo> updatePatientRegistry(XmanBaseInfo message);

    /**
     * 个人身份合并
     * @param newPatientId
     * @return
     */
    ServiceResultT<XmanBaseInfo> mergePatientRegistry(String newPatientId,String oldPatientId);

    /**
     * 个人基本信息查询
     * @param message
     * @return
     */
    ServiceResult patientRegistryQuery(String message);

}
