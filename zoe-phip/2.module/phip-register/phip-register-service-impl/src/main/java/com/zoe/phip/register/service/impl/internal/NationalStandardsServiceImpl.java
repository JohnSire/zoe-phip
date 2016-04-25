/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.INationalStandardsMapper;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.register.service.internal.INationalStandardsService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Repository("nationalStandardsService")
@Service(interfaceClass = INationalStandardsService.class, protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
public class NationalStandardsServiceImpl extends BaseInServiceImpl<NationalStandards, INationalStandardsMapper> implements INationalStandardsMapper {

}