package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.internal.IAreaRegisterIn;

/**
 * Created by zengjiyang on 2016/4/22.
 */
public class AreaRegisterInImpl implements IAreaRegisterIn {
    @Override
    public ServiceResultT<AreaBaseInfo> addAreaRequest(AreaBaseInfo message) {
        return null;
    }

    @Override
    public ServiceResultT<AreaBaseInfo> updateAreaRequest(AreaBaseInfo message) {
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
