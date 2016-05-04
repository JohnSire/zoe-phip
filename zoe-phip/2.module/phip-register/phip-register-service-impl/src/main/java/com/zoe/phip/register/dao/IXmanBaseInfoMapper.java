/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.model.XmanCard;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IXmanBaseInfoMapper extends IServiceMapper<XmanBaseInfo> {

    /**
     * 根据patient_id或health_record_no
     * 获取病人信息
     *
     * @param id
     * @return
     */
    XmanBaseInfo getPatient(String id);

    /**
     * 根据主键获取病人信息
     * @param id
     * @return
     */
    XmanBaseInfo getPatientByPrimaryKey(String id);

    XmanBaseInfo addPatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard) throws Exception;


    XmanBaseInfo updatePatientRegistry(XmanBaseInfo xmanBaseInfo, XmanCard xmanCard) throws Exception;

    XmanBaseInfo mergePatientRegistry(String newPatientId, String oldPatientId) throws Exception;

    XmanBaseInfo patientRegistryQuery(String patientId) throws Exception;

    List<XmanBaseInfo> getPatientList(Map<String, Object> args);

    PageList<XmanBaseInfo> patientRegistryListQuery(String key, QueryPage page);



}