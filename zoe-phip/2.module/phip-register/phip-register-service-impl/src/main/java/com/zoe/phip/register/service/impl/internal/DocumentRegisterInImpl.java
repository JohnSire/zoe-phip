package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IEhrDataInfoMapper;
import com.zoe.phip.register.model.EhrDataInfo;
import com.zoe.phip.register.service.internal.IDocumentRegisterIn;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/5/5.
 */
@Repository("DocumentRegisterIn")
@Service(interfaceClass = IDocumentRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class DocumentRegisterInImpl extends BaseInServiceImpl<EhrDataInfo, IEhrDataInfoMapper> implements IEhrDataInfoMapper{



    @Override
    public int defaultUpdate(EhrDataInfo ehrDataInfo) {
        return getMapper().defaultUpdate(ehrDataInfo);
    }
}
