/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IMedicalStaffInfoMapper extends IServiceMapper<MedicalStaffInfo> {
    MedicalStaffInfo getStaff(Map<String, Object> map);

    /**
     * 医护人员注册
     *
     * @param medicalStaffInfo
     * @return
     */
    MedicalStaffInfo addProvider(MedicalStaffInfo medicalStaffInfo) throws Exception;

    /**
     * 医护人员信息更新
     *
     * @param medicalStaffInfo
     * @return
     */
    MedicalStaffInfo updateProvider(MedicalStaffInfo medicalStaffInfo) throws Exception;

    /**
     * 医护人员查询
     *
     * @param id
     * @return
     */
    MedicalStaffInfo providerDetailsQuery(String id) throws Exception;

    /**
     * 医护人员删除
     *
     * @param staffInfo
     * @return
     */
    boolean providerDelete(MedicalStaffInfo staffInfo) throws Exception;

    /**
     * 查询医疗卫生人员列表
     *
     * @param key
     * @return
     */
    PageList<MedicalStaffInfo> providerListQuery(QueryPage page, String key) throws Exception;


    /**
     * 根据ID或者EXTENSION_ID
     * 获取人员信息
     * @param id
     * @return
     */
    MedicalStaffInfo getProvider(String id);


    List<MedicalStaffInfo> getProviderList(Map<String, Object> map);
}