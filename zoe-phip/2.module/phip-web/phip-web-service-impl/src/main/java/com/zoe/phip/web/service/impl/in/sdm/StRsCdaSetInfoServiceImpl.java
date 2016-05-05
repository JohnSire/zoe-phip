/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;

import com.zoe.phip.web.dao.sdm.IStRsCdaSetInfoMapper;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;
import com.zoe.phip.web.service.sdm.IStRsCdaSetInfoService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-05-05
 */
@Repository("stRsCdaSetInfoService")
@Service(interfaceClass = IStRsCdaSetInfoService.class, proxy = "sdpf", dynamic = true)
public class StRsCdaSetInfoServiceImpl extends BaseInServiceImpl<StRsCdaSetInfo, IStRsCdaSetInfoMapper> implements IStRsCdaSetInfoMapper {

    public int updateByCdaId(String fkCdaId, List<StRsCdaSetInfo> infoList){
return 0;
    }

}