/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStCdaInfoMapper;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.service.sdm.IStCdaInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stCdaInfoService")
@Service(interfaceClass = IStCdaInfoService.class, proxy = "sdpf", dynamic = true)
public class StCdaInfoServiceImpl extends BaseInServiceImpl<StCdaInfo, IStCdaInfoMapper> implements IStCdaInfoMapper {

}