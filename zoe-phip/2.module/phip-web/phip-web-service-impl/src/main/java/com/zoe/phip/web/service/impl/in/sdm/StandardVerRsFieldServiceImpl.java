/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;

import com.zoe.phip.web.dao.sdm.IStandardVerRsFieldMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsField;
import com.zoe.phip.web.service.sdm.IStandardVerRsFieldService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
@Repository("standardVerRsFieldService")
@Service(interfaceClass = IStandardVerRsFieldService.class, proxy = "sdpf", dynamic = true)
public class StandardVerRsFieldServiceImpl extends BaseInServiceImpl<StandardVerRsField, IStandardVerRsFieldMapper> implements IStandardVerRsFieldMapper {

}