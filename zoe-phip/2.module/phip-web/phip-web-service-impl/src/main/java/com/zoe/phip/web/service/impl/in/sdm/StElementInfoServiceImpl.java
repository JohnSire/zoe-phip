/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStElementInfoMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.service.sdm.IStElementInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stElementInfoService")
@Service(interfaceClass = IStElementInfoService.class, proxy = "sdpf", dynamic = true)
public class StElementInfoServiceImpl extends BaseInServiceImpl<StElementInfo, IStElementInfoMapper> implements IStElementInfoMapper {

}