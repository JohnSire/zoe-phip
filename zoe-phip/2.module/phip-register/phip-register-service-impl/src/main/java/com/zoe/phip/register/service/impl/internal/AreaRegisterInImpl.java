package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.internal.IAreaRegisterIn;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("AreaRegisterIn")
@Service(interfaceClass = IAreaRegisterIn.class, proxy = "sdpf",protocol = {"dubbo"},dynamic = true)
public class AreaRegisterInImpl implements IAreaRegisterIn {
    @Override
    public ServiceResultT<AreaBaseInfo> addAreaRequest(AreaBaseInfo areaBaseInfo) {
        return null;
    }

    @Override
    public ServiceResultT<AreaBaseInfo> updateAreaRequest(AreaBaseInfo areaBaseInfo) {
        return null;
    }

    @Override
    public ServiceResultT<AreaBaseInfo> areaDetailQuery(String id) {
        return null;
    }

    @Override
    public String areaChildrenRegistryQuery(String message) {
        return null;
    }

    @Override
    public String areaHistoryRegistryQuery(String message) {
        return null;
    }
}
