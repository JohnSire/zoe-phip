/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.module.service.mapper.IServiceMapper;
import com.zoe.phip.register.model.XmanCard;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IXmanCardMapper extends IServiceMapper<XmanCard> {

    /**
     * 根据病人信息获取卡
     *
     * @param baseInfoId
     * @return
     */
    XmanCard getXmanCard(String baseInfoId);

    /**
     * 通过xmanId删除卡信息
     * @param xmanId
     * @return
     */
    int deleteByXmanId(String xmanId);

}