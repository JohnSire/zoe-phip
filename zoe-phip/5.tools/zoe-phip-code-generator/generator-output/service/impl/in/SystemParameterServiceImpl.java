/*
 * Powered By zoe
 * Since 2008 - 2016
 */

        package com.zoe.phip.service.impl.in;
        import com.zoe.phip.model.SystemParameter;
        import com.zoe.phip.service.impl.in.BaseInServiceImpl;
        import com.zoe.phip.service.in.SystemParameterService;
        import org.springframework.stereotype.Repository;
        import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-04-01
 */
@Repository("systemParameterService")
@Service(interfaceClass = SystemParameterService.class, proxy = "sdpf", dynamic = true)
public class SystemParameterServiceImpl extends BaseInServiceImpl<SystemParameter,ISystemParameterMapper> implements ISystemParameterMapper{

        }