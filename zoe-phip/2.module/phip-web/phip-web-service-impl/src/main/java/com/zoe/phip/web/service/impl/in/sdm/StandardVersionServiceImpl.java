/*
 * Powered By zoe
 * Since 2008 - 2016
 */

        package com.zoe.phip.web.service.impl.in.sdm;

        import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
        import com.zoe.phip.web.dao.sdm.IStandardVersionMapper;
        import com.zoe.phip.web.model.sdm.StandardVersion;
        import com.zoe.phip.web.service.sdm.IStandardVersionService;
        import org.springframework.stereotype.Repository;
        import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("standardVersionService")
@Service(interfaceClass = IStandardVersionService.class, proxy = "sdpf", dynamic = true)
public class StandardVersionServiceImpl extends BaseInServiceImpl<StandardVersion,IStandardVersionMapper> implements IStandardVersionMapper{

        }