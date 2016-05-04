/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStSetInfoMapper;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.service.sdm.IStSetInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stSetInfoService")
@Service(interfaceClass = IStSetInfoService.class, proxy = "sdpf", dynamic = true)
public class StSetInfoServiceImpl extends BaseInServiceImpl<StSetInfo, IStSetInfoMapper> implements IStSetInfoMapper {

}