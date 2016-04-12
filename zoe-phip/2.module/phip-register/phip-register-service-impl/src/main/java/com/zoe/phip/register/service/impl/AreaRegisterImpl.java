package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.service.IAreaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("AreaRegister")
public class AreaRegisterImpl implements IAreaRegister {

    @Override
    public String addAreaRequest(String message) {
        return "123456";
    }

    @Override
    public String updateAreaRequest(String message) {
        return null;
    }

    @Override
    public String areaDetailQuery(String message) {
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
