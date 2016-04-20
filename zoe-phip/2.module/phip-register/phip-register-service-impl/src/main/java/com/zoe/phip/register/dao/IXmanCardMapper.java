/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.XmanCard;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IXmanCardMapper extends IServiceMapper<XmanCard> {

    /**
     * 根据病人信息获取卡
     * @param baseInfoId
     * @return
     */
    XmanCard getXmanCard(String baseInfoId);

}