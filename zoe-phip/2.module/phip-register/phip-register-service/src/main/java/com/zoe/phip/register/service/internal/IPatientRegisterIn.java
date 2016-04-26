package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;

/**
 * 病人注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IPatientRegisterIn extends IBaseInService<XmanBaseInfo> {

    /**
     * 个人信息新增
     * @param xmanBaseInfo
     * @return
     */
    @Deprecated
    ServiceResultT<XmanBaseInfo> addPatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard);

    /**
     * 个人信息更新
     * @param xmanBaseInfo
     * @return
     */
    @Deprecated
    ServiceResultT<XmanBaseInfo> updatePatientRegistry(XmanBaseInfo xmanBaseInfo,XmanCard xmanCard);

    /**
     * 个人身份合并
     * @param newPatientId
     * @return
     */
    ServiceResultT<XmanBaseInfo> mergePatientRegistry(SystemData systemData, String newPatientId,String oldPatientId);

    /**
     * 个人基本信息查询
     * @param patientId
     * @return
     */
    @Deprecated
    ServiceResultT<XmanBaseInfo> patientRegistryQuery(String patientId);

    /**
     * 根据条件查询病人列表
     * @param systemData 验证信息
     * @param key 查询关键字
     * @param page 分页信息
     * @return
     */
    ServiceResultT<PageList<XmanBaseInfo>> patientRegistryListQuery(SystemData systemData, String key, QueryPage page);

}
