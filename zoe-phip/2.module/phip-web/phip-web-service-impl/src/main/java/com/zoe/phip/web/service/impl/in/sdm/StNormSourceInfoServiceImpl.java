/*
 * Powered By zoe
 * Since 2008 - 2016
*/
package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStNormSourceInfoMapper;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import com.zoe.phip.web.service.sdm.IStNormSourceInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stNormSourceInfoService")
@Service(interfaceClass = IStNormSourceInfoService.class, proxy = "sdpf", dynamic = true)
public class StNormSourceInfoServiceImpl extends BaseInServiceImpl<StNormSourceInfo, IStNormSourceInfoMapper> implements IStNormSourceInfoMapper {

}