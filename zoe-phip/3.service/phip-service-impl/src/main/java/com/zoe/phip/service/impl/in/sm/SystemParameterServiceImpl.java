/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.dao.sm.SystemParameterMapper;
import com.zoe.phip.model.sm.SystemParameter;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.SystemParameterService;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Repository("systemParameterService")
@Service(interfaceClass = SystemParameterService.class, proxy = "sdpf", dynamic = true)
public class SystemParameterServiceImpl extends BaseInServiceImpl<SystemParameter, SystemParameterMapper> implements SystemParameterMapper {

}