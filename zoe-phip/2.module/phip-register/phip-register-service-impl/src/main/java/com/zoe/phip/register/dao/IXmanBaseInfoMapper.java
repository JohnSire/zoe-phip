/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.XmanBaseInfo;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IXmanBaseInfoMapper extends IServiceMapper<XmanBaseInfo> {

    /**根据patient_id或health_record_no
     * 获取病人信息
     * @param id
     * @return
     */
    XmanBaseInfo getPatient(String id);
}